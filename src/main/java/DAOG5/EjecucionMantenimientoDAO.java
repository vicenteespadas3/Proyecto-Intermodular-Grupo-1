/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ModeloG5.EjecucionMantenimiento;
import PckConexionBD.ConexionBD;

/**
 *
 * @author Usuario
 */

public class EjecucionMantenimientoDAO {

    public List<EjecucionMantenimiento> obtenerTodos() {
        List<EjecucionMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM ejecuciones_mantenimiento";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EjecucionMantenimiento ejec = new EjecucionMantenimiento();
                
                ejec.setIdEjecucion(rs.getInt("id_ejecucion"));
                ejec.setIdOrden(rs.getInt("id_orden"));
                ejec.setIdTecnico(rs.getInt("id_tecnico")); // Guardamos el ID directamente
                
                Date fecha = rs.getDate("fecha_ejecucion");
                if (fecha != null) ejec.setFechaEjecucion(fecha.toLocalDate());

                Time inicio = rs.getTime("hora_inicio");
                if (inicio != null) ejec.setHoraInicio(inicio.toLocalTime());

                Time fin = rs.getTime("hora_fin");
                if (fin != null) ejec.setHoraFin(fin.toLocalTime());

                ejec.setObservaciones(rs.getString("observaciones"));
                ejec.setResultado(rs.getString("resultado"));

                lista.add(ejec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar(EjecucionMantenimiento ejec) {
        String sql = "INSERT INTO ejecuciones_mantenimiento (id_orden, fecha_ejecucion, hora_inicio, hora_fin, id_tecnico, observaciones, resultado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, ejec.getIdOrden());
            ps.setObject(2, ejec.getFechaEjecucion());
            ps.setObject(3, ejec.getHoraInicio());
            ps.setObject(4, ejec.getHoraFin());
            ps.setInt(5, ejec.getIdTecnico()); // Pasas el número de técnico a mano
            ps.setString(6, ejec.getObservaciones());
            ps.setString(7, ejec.getResultado());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}