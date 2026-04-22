package DAO;

/**
 * @author Guadalupe M.G.M
 */

import java.sql.*;
import java.util.ArrayList;

import Modelo.Usuario;


/**
 * Clase DAO para gestionar la persistencia de los usuarios.
 * Proporciona métodos para realizar operaciones CRUD en la tabla 'usuarios'.
 */
public class UsuarioDAO {

    private Connection con;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * @param con Conexión activa.
     */
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // =========================
    // CREATE
    // =========================
    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param u Objeto Usuario con los datos a insertar.
     */
    public void insertar(Usuario u) {

        String sql = "INSERT INTO usuarios (nombre, email, rol) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getRol());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error INSERT usuario: " + e.getMessage());
        }
    }

    // =========================
    // READ
    // =========================
    /**
     * Recupera todos los usuarios registrados.
     * @return Lista de objetos Usuario.
     */
    public ArrayList<Usuario> listar() {

        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));

                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Error SELECT usuarios: " + e.getMessage());
        }

        return lista;
    }

    // =========================
    // UPDATE
    // =========================
    /**
     * Actualiza los datos de un usuario existente.
     * @param u Objeto Usuario con los datos actualizados.
     */
    public void actualizar(Usuario u) {

        String sql = "UPDATE usuarios SET nombre=?, email=?, rol=? WHERE idUsuario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getRol());
            ps.setInt(4, u.getIdUsuario());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error UPDATE usuario: " + e.getMessage());
        }
    }

    // =========================
    // DELETE
    // =========================
    /**
     * Elimina un usuario por su ID.
     * @param idUsuario ID del usuario a eliminar.
     */
    public void eliminar(int idUsuario) {

        String sql = "DELETE FROM usuarios WHERE idUsuario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error DELETE usuario: " + e.getMessage());
        }
    }

}