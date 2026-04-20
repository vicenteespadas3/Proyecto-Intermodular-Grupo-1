/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ModeloG5.TareaEjecutada;
import PckConexionBD.ConexionBD;

/**
 *
 * @author Usuario
 */

public class TareaEjecutadaDAO {

    public List<TareaEjecutada> obtenerTodos() {
        List<TareaEjecutada> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas_ejecutadas";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TareaEjecutada tarea = new TareaEjecutada();
                
                tarea.setIdTareaEjecutada(rs.getInt("id_tarea_ejecutada"));
                tarea.setIdEjecucion(rs.getInt("id_ejecucion"));
                tarea.setIdTarea(rs.getInt("id_tarea"));
                tarea.setCompletada(rs.getBoolean("completada"));
                tarea.setObservaciones(rs.getString("observaciones"));
                tarea.setTiempoEmpleado((Integer) rs.getObject("tiempo_empleado"));

                lista.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar(TareaEjecutada tarea) {
        String sql = "INSERT INTO tareas_ejecutadas (id_ejecucion, id_tarea, completada, observaciones, tiempo_empleado) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, tarea.getIdEjecucion());
            ps.setInt(2, tarea.getIdTarea());
            ps.setBoolean(3, tarea.isCompletada());
            ps.setString(4, tarea.getObservaciones());
            
            if (tarea.getTiempoEmpleado() != null) {
                ps.setInt(5, tarea.getTiempoEmpleado());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}