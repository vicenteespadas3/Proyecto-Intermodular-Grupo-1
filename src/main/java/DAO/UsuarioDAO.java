package DAO;

import java.sql.*;
import java.util.ArrayList;
import Modelo.Usuario;

/**
 * Clase UsuarioDAO
 * Aquí es donde hago todo el trabajo con la base de datos MySQL.
 * Uso este DAO para guardar, leer, cambiar o borrar usuarios (el CRUD).
 * * @see Modelo.Usuario
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class UsuarioDAO {

    private Connection con;

    /**
     * Constructor para pasarle la conexión.
     * Así no tengo que abrir una conexión nueva cada vez.
     */
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // ==========================================
    // OPERACIONES CRUD (Para la base de datos)
    // ==========================================

    /**
     * CREATE - Guardar un usuario nuevo.
     * Meto todos los datos en la tabla 'usuarios'.
     */
    public void insertar(Usuario u) {
        // La consulta SQL con '?' para que sea más seguro
        String sql = "INSERT INTO usuarios (nombre, apellidos, email, password, rol, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword()); 
            ps.setString(5, u.getRol());
            // Paso la fecha de Java a un formato que MySQL entienda
            ps.setTimestamp(6, Timestamp.valueOf(u.getFechaCreacion()));
            
            ps.executeUpdate();
            System.out.println("¡Usuario guardado con éxito!");
            
        } catch (SQLException e) {
            System.out.println("No pude guardar el usuario: " + e.getMessage());
        }
    }

    /**
     * READ - Traer todos los usuarios.
     * Hago un SELECT y los voy metiendo en una lista uno por uno.
     */
    public ArrayList<Usuario> listar() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Creo un objeto vacío y lo lleno con lo que trae la fila de la BD
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidos(rs.getString("apellidos"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));
                
                // Traigo las fechas de creación y último acceso
                Timestamp fc = rs.getTimestamp("fecha_creacion");
                if (fc != null) u.setFechaCreacion(fc.toLocalDateTime());
                
                Timestamp ua = rs.getTimestamp("ultimo_acceso");
                if (ua != null) u.setUltimoAcceso(ua.toLocalDateTime());

                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Hubo un fallo al leer los usuarios: " + e.getMessage());
        }
        return lista;
    }

    /**
     * UPDATE - Cambiar los datos.
     * Actualizo la info del usuario y también cuando fue la última vez que entró.
     */
    public void actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, email=?, rol=?, ultimo_acceso=? WHERE idUsuario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getRol());
            
            // Si el usuario tiene fecha de acceso, la pongo; si no, la dejo vacía
            if (u.getUltimoAcceso() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(u.getUltimoAcceso()));
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }
            
            ps.setInt(6, u.getIdUsuario());
            ps.executeUpdate();
            System.out.println("¡Datos actualizados!");
            
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar: " + e.getMessage());
        }
    }

    /**
     * DELETE - Borrar un usuario.
     * Lo busco por su ID y lo saco de la tabla.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario borrado correctamente.");
            
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar al usuario: " + e.getMessage());
        }
    }
}