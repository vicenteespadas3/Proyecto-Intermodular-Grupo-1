/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.OrdenTrabajo;
import PckConexionBD.ConexionBD;

/**
 *
 * @author Usuario
 */

public class OrdenTrabajoDAO {

    public boolean insertar(OrdenTrabajo ot) {
        String sql = "INSERT INTO ordenes_trabajo (id_maquina, id_plan, id_tipo, id_estado, id_prioridad, id_rol_asignado, fecha_programada, tiempo_estimado, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, ot.getIdMaquina());
            if (ot.getIdPlan() != null) ps.setInt(2, ot.getIdPlan()); else ps.setNull(2, Types.INTEGER);
            ps.setInt(3, ot.getIdTipo());
            ps.setInt(4, ot.getIdEstado());
            ps.setInt(5, ot.getIdPrioridad());
            if (ot.getIdRolAsignado() != null) ps.setInt(6, ot.getIdRolAsignado()); else ps.setNull(6, Types.INTEGER);
            ps.setObject(7, ot.getFechaProgramada());
            ps.setObject(8, ot.getTiempoEstimado());
            ps.setString(9, ot.getObservaciones());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para listar que solo rellena los IDs numéricos
    public List<OrdenTrabajo> obtenerTodas() {
        List<OrdenTrabajo> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordenes_trabajo";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                OrdenTrabajo ot = new OrdenTrabajo();
                ot.setIdOrden(rs.getInt("id_orden"));
                ot.setIdMaquina(rs.getInt("id_maquina"));
                ot.setIdEstado(rs.getInt("id_estado"));
                // ... rellenar el resto de campos igual que arriba
                lista.add(ot);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}