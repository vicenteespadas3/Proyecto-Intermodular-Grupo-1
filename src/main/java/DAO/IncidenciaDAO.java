/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Incidencia;
import Modelo.Maquina;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase IncidenciaDAO</h2>
 * Gestiona el acceso a datos (CRUD) de los reportes de fallos en el taller.
 * Conecta con la tabla 'incidencias' y soporta el manejo de valores nulos para fechas de resolución y técnicos.
 * Incluye un sistema de recuperación en RAM.
 * * @author Vicente
 * @version 0.0.1
 */
public class IncidenciaDAO {

    private Connection con;
    private List<Incidencia> papeleraCache;

    // --- 1. Constructores ---

    /**
     * Constructor por defecto del DAO.
     * Inicializa la conexión con la Base de Datos y la papelera de reciclaje en RAM.
     */
    public IncidenciaDAO() {
        this.con = ConexionBD.getConnection();
        this.papeleraCache = new ArrayList<>();
    }

    // --- 2. Métodos de Acceso a Datos (CRUD) ---

    /**
     * Inserta una nueva incidencia en la base de datos.
     * Maneja dinámicamente si hay técnico asignado o si la incidencia ya tiene fecha de resolución (soporte de nulos).
     * * @param i El objeto Incidencia a persistir en MySQL.
     * @return true si la inserción fue exitosa, false en caso de error SQL.
     */
    public boolean insertar(Incidencia i) {
        String sql = "INSERT INTO incidencias (id_maquina, id_usuario_reporta, fecha_reporte, descripcion, tipo, prioridad, estado, id_tecnico_asignado, fecha_resolucion, solucion_aplicada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, i.getMaquina().getIdMaquina());
            ps.setInt(2, i.getIdUsuarioReporta());
            ps.setTimestamp(3, new java.sql.Timestamp(i.getFechaReporte().getTime()));
            ps.setString(4, i.getDescripcion());
            ps.setString(5, i.getTipo());
            ps.setString(6, i.getPrioridad());
            ps.setString(7, i.getEstado());
            
            // Manejo de técnico asignado (si es 0, lo guardamos como NULL en BD)
            if (i.getIdTecnicoAsignado() > 0) {
                ps.setInt(8, i.getIdTecnicoAsignado());
            } else {
                ps.setNull(8, Types.INTEGER);
            }
            
            // Manejo de fecha de resolución nula (tickets abiertos)
            if (i.getFechaResolucion() != null) {
                ps.setTimestamp(9, new java.sql.Timestamp(i.getFechaResolucion().getTime()));
            } else {
                ps.setNull(9, Types.TIMESTAMP);
            }
            
            ps.setString(10, i.getSolucionAplicada());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar incidencia: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera el listado completo de todas las incidencias registradas.
     * Realiza un JOIN con la tabla de máquinas para recuperar el número de serie y el fabricante.
     * * @return Una lista de objetos Incidencia. Estará vacía si no hay registros.
     */
    public List<Incidencia> listarTodas() {
        List<Incidencia> lista = new ArrayList<>();
        // CORRECCIÓN AQUÍ: m.fabricante en lugar de m.marca
        String sql = "SELECT i.*, m.numero_serie, m.fabricante FROM incidencias i JOIN maquinas m ON i.id_maquina = m.id_maquina";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Maquina m = new Maquina();
                m.setIdMaquina(rs.getInt("id_maquina"));
                m.setNumeroSerie(rs.getString("numero_serie"));
                // CORRECCIÓN AQUÍ: setFabricante en lugar de setMarca
                m.setFabricante(rs.getString("fabricante"));

                lista.add(new Incidencia(
                        rs.getInt("id_incidencia"), 
                        m, 
                        rs.getInt("id_usuario_reporta"),
                        rs.getTimestamp("fecha_reporte"),
                        rs.getString("descripcion"), 
                        rs.getString("tipo"), 
                        rs.getString("prioridad"), 
                        rs.getString("estado"),
                        rs.getInt("id_tecnico_asignado"),
                        rs.getTimestamp("fecha_resolucion"),
                        rs.getString("solucion_aplicada")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar incidencias: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca una incidencia específica a través de su identificador único.
     * * @param id El identificador (Primary Key) de la incidencia a buscar.
     * @return El objeto Incidencia encontrado, o null si no existe en la base de datos.
     */
    public Incidencia buscarPorId(int id) {
        // CORRECCIÓN AQUÍ: m.fabricante en lugar de m.marca
        String sql = "SELECT i.*, m.numero_serie, m.fabricante FROM incidencias i JOIN maquinas m ON i.id_maquina = m.id_maquina WHERE i.id_incidencia = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Maquina m = new Maquina();
                    m.setIdMaquina(rs.getInt("id_maquina"));
                    m.setNumeroSerie(rs.getString("numero_serie"));
                    // CORRECCIÓN AQUÍ: setFabricante en lugar de setMarca
                    m.setFabricante(rs.getString("fabricante"));

                    return new Incidencia(
                            rs.getInt("id_incidencia"), 
                            m, 
                            rs.getInt("id_usuario_reporta"),
                            rs.getTimestamp("fecha_reporte"),
                            rs.getString("descripcion"), 
                            rs.getString("tipo"), 
                            rs.getString("prioridad"), 
                            rs.getString("estado"),
                            rs.getInt("id_tecnico_asignado"),
                            rs.getTimestamp("fecha_resolucion"),
                            rs.getString("solucion_aplicada")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar incidencia: " + e.getMessage());
        }
        return null;
    }

    // --- 3. Métodos del Sistema de Caché (Papelera) ---

    /**
     * Borra una incidencia de la base de datos pero la respalda previamente en la memoria RAM.
     * * @param id El identificador de la incidencia a eliminar.
     * @return true si se borró y respaldó correctamente, false en caso contrario.
     */
    public boolean eliminarConRespaldo(int id) {
        Incidencia i = buscarPorId(id);
        if (i != null) {
            String sql = "DELETE FROM incidencias WHERE id_incidencia = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    papeleraCache.add(i);
                    System.out.println("[INFO] Incidencia movida a la papelera temporal.");
                    return true;
                }
            } catch (SQLException e) {
                System.err.println("Error al borrar incidencia: " + e.getMessage());
            }
        } else {
            System.out.println("[AVISO] No se encontró ninguna incidencia con ID: " + id);
        }
        return false;
    }

    /**
     * Restaura la última incidencia borrada sacándola de la caché RAM e insertándola de nuevo en MySQL.
     * * @return true si se logró restaurar con éxito, false si la papelera estaba vacía o hubo error SQL.
     */
    public boolean restaurarUltimo() {
        if (papeleraCache.isEmpty()) {
            System.out.println("[AVISO] La papelera de incidencias está vacía.");
            return false;
        }
        Incidencia i = papeleraCache.remove(papeleraCache.size() - 1);
        boolean exito = insertar(i);
        
        if (exito) {
            System.out.println("[OK] Incidencia restaurada con éxito en la Base de Datos.");
        }
        return exito;
    }

    /**
     * Imprime por consola la cantidad de elementos que actualmente residen en la caché de recuperación.
     */
    public void verEstadoPapelera() {
        System.out.println("[Papelera Incidencias: " + papeleraCache.size() + " elementos]");
    }
}