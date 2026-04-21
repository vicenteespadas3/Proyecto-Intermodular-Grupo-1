/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD; 
import ModeloG1.ArchivoAdjunto;

/**
 * Clase DAO para gestionar los archivos adjuntos de las máquinas.
 * @author Vicente
 */
public class ArchivoAdjuntoDAO {

    public boolean insertar(ArchivoAdjunto archivo) {
        String sql = "INSERT INTO archivos_adjuntos (nombre, ruta, tipo, id_maquina) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, archivo.getNombre());
            pstmt.setString(2, archivo.getRuta());
            pstmt.setString(3, archivo.getTipo());
            pstmt.setInt(4, archivo.getIdMaquina()); 
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar archivo adjunto: " + e.getMessage());
            return false;
        }
    }

    public List<ArchivoAdjunto> obtenerPorMaquina(int idMaquina) {
        List<ArchivoAdjunto> listaArchivos = new ArrayList<>();
        String sql = "SELECT * FROM archivos_adjuntos WHERE id_maquina = ?";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idMaquina);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ArchivoAdjunto archivo = new ArchivoAdjunto(
                        rs.getInt("id_archivo"),
                        rs.getString("nombre"),
                        rs.getString("ruta"),
                        rs.getString("tipo"),
                        rs.getInt("id_maquina")
                    );
                    listaArchivos.add(archivo);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener los archivos de la máquina: " + e.getMessage());
        }
        
        return listaArchivos;
    }
}