/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.ArchivoAdjunto;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase ArchivoAdjuntoDAO</h2>
 * Gestiona la persistencia (CRUD) de la tabla polimórfica 'archivos_adjuntos'.
 * Implementa caché en memoria RAM para deshacer borrados accidentales.
 * * @author Equipo 1
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
     * Mantiene la atomicidad de transacciones si es llamado desde otros módulos.
     * @param con Conexión activa a MySQL.
     */
    public ArchivoAdjuntoDAO(Connection con) {
        this.con = con;
        this.papeleraCache = new ArrayList<>();
    }

    // --- 2. Métodos de Acceso a Datos (CRUD) ---

    /**
     * Inserta un nuevo registro de archivo adjunto.
     * * @param a Objeto ArchivoAdjunto con los datos.
     * @return true si la inserción fue exitosa, false si falló.
     */
    public boolean insertar(ArchivoAdjunto a) {
        String sql = "INSERT INTO archivos_adjuntos (tipo_referencia, id_referencia, nombre_archivo, ruta_archivo, fecha_subida, id_usuario_subida) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getTipoReferencia());
            ps.setInt(2, a.getIdReferencia());
            ps.setString(3, a.getNombreArchivo());
            ps.setString(4, a.getRutaArchivo());
            ps.setTimestamp(5, new java.sql.Timestamp(a.getFechaSubida().getTime()));
            ps.setInt(6, a.getIdUsuarioSubida());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera el listado completo de todos los documentos registrados.
     * * @return Lista de objetos ArchivoAdjunto.
     */
    public List<ArchivoAdjunto> listarTodos() {
        List<ArchivoAdjunto> lista = new ArrayList<>();
        // Consulta limpia a una sola tabla (sin JOINs que provoquen errores con columnas antiguas)
        String sql = "SELECT * FROM archivos_adjuntos";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ArchivoAdjunto(
                        rs.getInt("id_archivo"),
                        rs.getString("tipo_referencia"),
                        rs.getInt("id_referencia"),
                        rs.getString("nombre_archivo"),
                        rs.getString("ruta_archivo"),
                        rs.getTimestamp("fecha_subida"),
                        rs.getInt("id_usuario_subida")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar archivos: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca un documento específico utilizando su clave primaria (id_archivo).
     * * @param id Identificador numérico del archivo.
     * @return Objeto ArchivoAdjunto si se encuentra, o null en caso contrario.
     */
    public ArchivoAdjunto buscarPorId(int id) {
        String sql = "SELECT * FROM archivos_adjuntos WHERE id_archivo = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ArchivoAdjunto(
                            rs.getInt("id_archivo"),
                            rs.getString("tipo_referencia"),
                            rs.getInt("id_referencia"),
                            rs.getString("nombre_archivo"),
                            rs.getString("ruta_archivo"),
                            rs.getTimestamp("fecha_subida"),
                            rs.getInt("id_usuario_subida")
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
     * Borra un archivo lógicamente de MySQL y lo respalda en RAM.
     * * @param id Identificador del archivo a eliminar.
     * @return true si se borró y respaldó correctamente.
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
     * Restaura el último archivo eliminado de la papelera en RAM (LIFO).
     * * @return true si la recuperación en MySQL fue exitosa.
     */
    public boolean restaurarUltimo() {
        if (papeleraCache.isEmpty()) {
            System.out.println("[AVISO] La papelera de documentos está vacía.");
            return false;
        }
        ArchivoAdjunto a = papeleraCache.remove(papeleraCache.size() - 1);
        boolean exito = insertar(a);
        
        if (exito) {
            System.out.println("[OK] Documento restaurado con éxito en la Base de Datos.");
        }
        return exito;
    }

    /**
     * Imprime el estado actual de la papelera en memoria.
     */
    public void verEstadoPapelera() {
        System.out.println("[Papelera Archivos: " + papeleraCache.size() + " documentos]");
    }
}