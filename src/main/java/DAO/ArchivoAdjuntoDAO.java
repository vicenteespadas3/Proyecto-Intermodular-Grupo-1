/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.ArchivoAdjunto;
import Modelo.Maquina;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase ArchivoAdjuntoDAO</h2>
 * Gestiona la persistencia (CRUD) de los documentos y manuales del taller.
 * Implementa un sistema de papelera en memoria RAM (Caché) para evitar 
 * la pérdida accidental de archivos importantes.
 * * @author Vicente
 * @version 0.0.1
 */
public class ArchivoAdjuntoDAO {

    private Connection con;
    private List<ArchivoAdjunto> papeleraCache;

    // --- 1. Constructores ---

    /**
     * Constructor por defecto.
     * Obtiene la conexión centralizada a la base de datos e inicializa la papelera en RAM.
     */
    public ArchivoAdjuntoDAO() {
        this.con = ConexionBD.getConnection();
        this.papeleraCache = new ArrayList<>();
    }

    /**
     * Constructor con inyección de dependencias.
     * Permite usar una conexión ya abierta por otro módulo para mantener la atomicidad de las transacciones.
     * @param con Conexión activa a la base de datos MySQL.
     */
    public ArchivoAdjuntoDAO(Connection con) {
        this.con = con;
        this.papeleraCache = new ArrayList<>();
    }

    // --- 2. Métodos de Acceso a Datos (CRUD) ---

    /**
     * Inserta el registro de un nuevo archivo adjunto en la base de datos.
     * * @param a Objeto ArchivoAdjunto con los datos del documento a guardar.
     * @return true si la inserción fue exitosa, false en caso de error SQL.
     */
    public boolean insertar(ArchivoAdjunto a) {
        String sql = "INSERT INTO archivos_adjuntos (id_maquina, nombre_archivo, extension, ruta_local, fecha_subida) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getMaquina().getIdMaquina());
            ps.setString(2, a.getNombreArchivo());
            ps.setString(3, a.getExtension());
            ps.setString(4, a.getRutaLocal());
            ps.setDate(5, new java.sql.Date(a.getFechaSubida().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera el listado completo de todos los documentos registrados.
     * Realiza un JOIN con la tabla de máquinas para obtener el número de serie al que pertenece cada archivo.
     * * @return Una lista de objetos ArchivoAdjunto. Devuelve lista vacía si no hay registros.
     */
    public List<ArchivoAdjunto> listarTodos() {
        List<ArchivoAdjunto> lista = new ArrayList<>();
        String sql = "SELECT a.*, m.numero_serie, m.marca FROM archivos_adjuntos a JOIN maquinas m ON a.id_maquina = m.id_maquina";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                // Instanciamos una máquina parcial para mantener la referencia cruzada
                Maquina m = new Maquina();
                m.setIdMaquina(rs.getInt("id_maquina"));
                m.setNumeroSerie(rs.getString("numero_serie"));
                m.setMarca(rs.getString("marca"));

                lista.add(new ArchivoAdjunto(
                        rs.getInt("id_archivo"), m, rs.getString("nombre_archivo"),
                        rs.getString("extension"), rs.getString("ruta_local"), rs.getDate("fecha_subida")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar archivos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca un documento específico utilizando su identificador único.
     * Útil para operaciones de descarga o visualización de un archivo concreto.
     * * @param id Identificador numérico del archivo adjunto.
     * @return El objeto ArchivoAdjunto si se encuentra, o null en caso contrario.
     */
    public ArchivoAdjunto buscarPorId(int id) {
        String sql = "SELECT a.*, m.numero_serie, m.marca FROM archivos_adjuntos a JOIN maquinas m ON a.id_maquina = m.id_maquina WHERE a.id_archivo = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Maquina m = new Maquina();
                    m.setIdMaquina(rs.getInt("id_maquina"));
                    m.setNumeroSerie(rs.getString("numero_serie"));
                    m.setMarca(rs.getString("marca"));

                    return new ArchivoAdjunto(
                            rs.getInt("id_archivo"), m, rs.getString("nombre_archivo"),
                            rs.getString("extension"), rs.getString("ruta_local"), rs.getDate("fecha_subida")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar archivo: " + e.getMessage());
        }
        return null;
    }

    // --- 3. Métodos del Sistema de Caché (Papelera) ---

    /**
     * Borra un archivo de la base de datos de forma lógica guardándolo en una lista de memoria RAM.
     * Permite deshacer el borrado si fue accidental.
     * * @param id El identificador del archivo a eliminar.
     * @return true si el archivo se eliminó de MySQL y se respaldó correctamente en RAM. false si falló.
     */
    public boolean eliminarConRespaldo(int id) {
        ArchivoAdjunto a = buscarPorId(id);
        if (a != null) {
            String sql = "DELETE FROM archivos_adjuntos WHERE id_archivo = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    papeleraCache.add(a);
                    System.out.println("[INFO] Documento movido a la papelera temporal.");
                    return true;
                }
            } catch (SQLException e) {
                System.err.println("Error al borrar documento: " + e.getMessage());
            }
        } else {
            System.out.println("[AVISO] No se encontró ningún documento con ID: " + id);
        }
        return false;
    }

    /**
     * Restaura el último archivo eliminado de la papelera en RAM (Sistema LIFO).
     * Vuelve a insertar los datos del documento en la base de datos.
     * * @return true si la recuperación en MySQL fue exitosa. false si la papelera estaba vacía.
     */
    public boolean restaurarUltimo() {
        if (papeleraCache.isEmpty()) {
            System.out.println("[AVISO] La papelera de documentos está vacía.");
            return false;
        }
        // Extraemos el último elemento borrado
        ArchivoAdjunto a = papeleraCache.remove(papeleraCache.size() - 1);
        boolean exito = insertar(a);
        
        if (exito) {
            System.out.println("[OK] Documento restaurado con éxito en la Base de Datos.");
        }
        return exito;
    }

    /**
     * Imprime por consola cuántos documentos se encuentran retenidos actualmente en la caché de recuperación.
     */
    public void verEstadoPapelera() {
        System.out.println("[Papelera Archivos: " + papeleraCache.size() + " documentos]");
    }
}