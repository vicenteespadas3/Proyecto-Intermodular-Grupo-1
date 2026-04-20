/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloG5.GamaMantenimiento;
import PckConexionBD.ConexionBD;
/**
 *
 * @author Eric
 */
public class GamaMantenimientoDAO {

    // --- 1. LEER DATOS (SELECT) ---
    public List<GamaMantenimiento> obtenerTodos() {
        List<GamaMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM gamas_mantenimiento";

        //Abrir conexion con la BBDD usando tuberia
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GamaMantenimiento gama = new GamaMantenimiento();
                
                // Rellenamos el objeto directamente desde la BBDD
                gama.setIdGama(rs.getInt("id_gama"));
                gama.setNombre(rs.getString("nombre"));
                gama.setTipoMantenimiento(rs.getString("tipo_mantenimiento"));
                gama.setTipoGama(rs.getString("tipo_gama"));
                gama.setDescripcion(rs.getString("descripcion"));
                
                lista.add(gama);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las gamas: " + e.getMessage());
        }
        return lista;
    }

    // --- 2. GUARDAR DATOS (INSERT) ---
    public boolean insertar(GamaMantenimiento gama) {
        String sql = "INSERT INTO gamas_mantenimiento (nombre, tipo_mantenimiento, tipo_gama, descripcion) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, gama.getNombre());
            ps.setString(2, gama.getTipoMantenimiento());
            ps.setString(3, gama.getTipoGama());
            ps.setString(4, gama.getDescripcion());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar la gama: " + e.getMessage());
            return false;
        }
    }
    
    // --- 3. BORRAR DATOS (DELETE) ---
    public boolean eliminar(int idGama) {
        String sql = "DELETE FROM gamas_mantenimiento WHERE id_gama = ?";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
             ps.setInt(1, idGama);
             int filasAfectadas = ps.executeUpdate();
             return filasAfectadas > 0;
             
        } catch (SQLException e) {
            System.err.println("Error al eliminar la gama (Ojo, puede estar en uso por un Plan): " + e.getMessage());
            return false;
        }
    }
}
