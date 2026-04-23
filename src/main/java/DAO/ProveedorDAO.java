/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD;
import Modelo.Proveedor;

/**
 * <h2>Clase ProveedorDAO</h2>
 * Centraliza las operaciones de acceso a datos (CRUD) para la entidad Proveedor.
 * Adaptada a la estructura final de la tabla 'proveedores_maquinas'.
 * @see Modelo.Proveedor
 * @author Vicente
 * @version 0.0.1
 */
public class ProveedorDAO {
    private Connection con;

    public ProveedorDAO(Connection con) {
        this.con = con;
    }
    
    // Constructor vacío arreglado (sin excepciones)
    public ProveedorDAO() {
        this.con = ConexionBD.getConnection();
    }

    public boolean insertar(Proveedor p) {
        // Apuntamos a la tabla correcta y con los campos correctos de tu imagen
        String sql = "INSERT INTO proveedores_maquinas (nombre, contacto, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getContacto());
            pstmt.setString(3, p.getTelefono());
            pstmt.setString(4, p.getEmail());
            pstmt.setString(5, p.getDireccion());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }

    public List<Proveedor> listarTodos() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        // Apuntamos a la tabla correcta
        String sql = "SELECT * FROM proveedores_maquinas";

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { 

            while (rs.next()) {
                Proveedor p = new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                );
                listaProveedores.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }
        return listaProveedores;
    }

    public Proveedor buscarPorId(int id) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM proveedores_maquinas WHERE id_proveedor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor(
                            rs.getInt("id_proveedor"),
                            rs.getString("nombre"),
                            rs.getString("contacto"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar proveedor: " + e.getMessage());
        }
        return proveedor;
    }
}