/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD;
import ModeloG1.Proveedor;

/**
 * Clase DAO para gestionar los proveedores.
 * @author Vicente
 */
public class ProveedorDAO {

    public boolean insertar(Proveedor p) {
        String sql = "INSERT INTO proveedores (cif, nombre, telefono, email_contacto) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, p.getCif());
            pstmt.setString(2, p.getNombre());
            pstmt.setString(3, p.getTelefono());
            pstmt.setString(4, p.getEmailContacto());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }

    public List<Proveedor> listarTodos() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Proveedor p = new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("cif"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email_contacto")
                );
                listaProveedores.add(p);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }
        
        return listaProveedores;
    }
}