/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG5FueraDeUso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ModeloG5FueraDeUso.OrdenTrabajo;
import PckConexionBD.ConexionBD;

/**
 *
 * @author Usuario
 */

public class OrdenTrabajoDAO {
    
    //esta clase gestiona el ciclo de vida de las ordenes
    //(crearlas y listarlas)

    public boolean insertar(OrdenTrabajo ot) {
        //guarda una ficha nueva en la base de datos
        
        //recibe un objeto, lo trocea en datos sueltos
        //y los manda a los "?" de la tabla
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
        //trae toda la lista de ordenes que ya estan
        //guardadas para que se vean en la app
        
        //mira la tabla, lee los datos de las columnas
        //y los empaqueta en Objetos y los mete en una lista
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