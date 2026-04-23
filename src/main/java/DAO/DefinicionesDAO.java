package DAO;

import java.sql.*;
import java.util.ArrayList;
import Modelo.Definiciones;

/**
 * Clase DefinicionesDAO
 * Esta clase se hizo para poder guardar y leer las etiquetas y estados
 * de la base de datos. Así Miguel Ángel puede usarlas en sus pedidos.
 * * @see Modelo.Definiciones
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class DefinicionesDAO {

    private Connection con;

    public DefinicionesDAO(Connection con) {
        this.con = con;
    }

    /**
     * CREATE - Para meter una nueva definición o estado.
     */
    public void insertar(Definiciones d) {
        String sql = "INSERT INTO definiciones (nombre, categoria, codigo, descripcion, orden_visual, activo, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getCategoria());
            ps.setString(3, d.getCodigo());
            ps.setString(4, d.getDescripcion());
            ps.setInt(5, d.getOrdenVisual());
            ps.setBoolean(6, d.isActivo());
            ps.setString(7, d.getEstado());
            
            ps.executeUpdate();
            System.out.println("¡Definición guardada!");
        } catch (SQLException e) {
            System.out.println("Error al insertar definición: " + e.getMessage());
        }
    }

    /**
     * READ - Para sacar la lista de estados de una categoría (ej. "PEDIDOS").
     */
    public ArrayList<Definiciones> listarPorCategoria(String cat) {
        ArrayList<Definiciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM definiciones WHERE categoria = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cat);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Definiciones d = new Definiciones();
                d.setIdDefinicion(rs.getInt("idDefinicion"));
                d.setNombre(rs.getString("nombre"));
                d.setEstado(rs.getString("estado"));
                d.setActivo(rs.getBoolean("activo"));
                // ... llenar los demás campos si los necesitas
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer definiciones: " + e.getMessage());
        }
        return lista;
    }

    /**
     * DELETE - Por si queremos borrar algún estado viejo.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM definiciones WHERE idDefinicion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo borrar: " + e.getMessage());
        }
    }
}