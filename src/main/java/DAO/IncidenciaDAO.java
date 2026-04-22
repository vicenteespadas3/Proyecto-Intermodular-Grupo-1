/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD;
import Modelo.Incidencia;

/**
 * <h2>Clase IncidenciaDAO</h2>
 * Clase DAO para gestionar la persistencia de las incidencias o averias.
 * Proporciona metodos para registrar problemas y asignar tecnicos (usuarios) 
 * actualizando el estado en la base de datos.
 * * @see Modelo.Incidencia
 * @author Vicente
 * @version 0.0.1
 */
public class IncidenciaDAO {

    /**
     * Caso de Uso 1 (Paso 1): El Operario registra una nueva avería.
     * @param inc Objeto Incidencia con los datos iniciales.
     * @return true si se inserta correctamente.
     */
    public boolean insertar(Incidencia inc) {
        String sql = "INSERT INTO incidencias (id_maquina, descripcion, fecha_reporte, prioridad, estado) VALUES (?, ?, NOW(), ?, 'Abierta')";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, inc.getIdMaquina());
            pstmt.setString(2, inc.getDescripcion());
            pstmt.setString(3, inc.getPrioridad());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar la incidencia: " + e.getMessage());
            return false;
        }
    }

    /**
     * Caso de Uso 1 (Paso 2): El Jefe de Taller asigna un técnico a la incidencia.
     * @param idIncidencia El ID de la avería.
     * @param idTecnico El ID del usuario (Módulo de Guadalupe).
     * @return true si la actualización es exitosa.
     */
    public boolean asignarTecnico(int idIncidencia, int idTecnico) {
        String sql = "UPDATE incidencias SET id_tecnico_asignado = ?, estado = 'En curso' WHERE id_incidencia = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idTecnico);
            pstmt.setInt(2, idIncidencia);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al asignar técnico: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera todas las incidencias del taller para mostrarlas en la tabla gráfica.
     */
    public List<Incidencia> listarTodas() {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencias ORDER BY fecha_reporte DESC";
        
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Incidencia inc = new Incidencia();
                inc.setIdIncidencia(rs.getInt("id_incidencia"));
                inc.setIdMaquina(rs.getInt("id_maquina"));
                
                // Manejo seguro del nulo para el ID del técnico
                int idTecnico = rs.getInt("id_tecnico_asignado");
                if (!rs.wasNull()) {
                    inc.setIdTecnicoAsignado(idTecnico);
                }
                
                inc.setDescripcion(rs.getString("descripcion"));
                
                Timestamp ts = rs.getTimestamp("fecha_reporte");
                if (ts != null) inc.setFechaReporte(ts.toLocalDateTime());
                
                inc.setPrioridad(rs.getString("prioridad"));
                inc.setEstado(rs.getString("estado"));
                
                lista.add(inc);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar incidencias: " + e.getMessage());
        }
        return lista;
    }
}