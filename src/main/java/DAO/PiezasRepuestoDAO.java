package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.PiezasRepuesto;

/**
 * <h2>Clase PiezasRepuestoDAO</h2>
 * Clase encargada de ser la intermediaria entre el modelo(PiezasRepuesto) y la
 * base de datos.
 * Proporciona métodos para gestionar la persistencia de las piezas de repuesto.
 * 
 * @see PiezasRepuesto
 * @author Miguel Ángel Sánchez Martínez
 * @version 0.0.1
 */
public class PiezasRepuestoDAO {
    private Connection con;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * 
     * @param con Conexión activa.
     */
    public PiezasRepuestoDAO(Connection con) {
        this.con = con;
    }

    public PiezasRepuesto buscarPorId(int id) {
        PiezasRepuesto pieza = null;
        String sql = "SELECT * FROM piezas_repuesto WHERE id_pieza = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pieza = new PiezasRepuesto();
                    pieza.setIdPieza(rs.getInt("id_pieza"));
                    pieza.setNombre(rs.getString("nombre"));
                    // Añade aquí más campos si los tienes (marca, stock, etc.)
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar pieza: " + e.getMessage());
        }
        return pieza;
    }
}
