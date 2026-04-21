/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD;
import ModeloG1.Maquina;

/**
 * Clase DAO para gestionar las máquinas.
 * @author Vicente
 */
public class MaquinaDAO {

    public boolean insertar(Maquina m) {
        String sql = "INSERT INTO maquinas (numero_serie, marca, modelo, id_estado, fecha_adquisicion) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, m.getNumeroSerie());
            pstmt.setString(2, m.getMarca());
            pstmt.setString(3, m.getModelo());
            pstmt.setInt(4, m.getIdEstado());
            pstmt.setDate(5, m.getFechaAdquisicion());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar máquina: " + e.getMessage());
            return false;
        }
    }

    public void vincularProveedor(int idMaquina, int idProveedor) {
        String sql = "INSERT INTO proveedores_maquinas (id_maquina, id_proveedor) VALUES (?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMaquina);
            pstmt.setInt(2, idProveedor);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al vincular proveedor: " + e.getMessage());
        }
    }

    public List<Maquina> listarTodas() {
        List<Maquina> maquinas = new ArrayList<>();
        String sql = "SELECT * FROM maquinas";
        
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Maquina m = new Maquina(
                    rs.getInt("id_maquina"),
                    rs.getString("numero_serie"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("id_estado"),
                    rs.getDate("fecha_adquisicion")
                );
                maquinas.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar máquinas: " + e.getMessage());
        }
        return maquinas;
    }
}