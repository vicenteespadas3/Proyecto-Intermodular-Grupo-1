package DAO;

import java.sql.*;
import java.util.ArrayList;
import Modelo.Usuario;

/**
 * <h2>Clase UsuarioDAO</h2>
 * Implementa el patrón Data Access Object (DAO) para la entidad {@link Usuario}.
 * Gestiona de forma centralizada todas las operaciones de persistencia (CRUD) 
 * en la base de datos MySQL para los trabajadores del sistema.
 * * @see Modelo.Usuario
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class UsuarioDAO {

    /** Objeto de conexión hacia la base de datos MySQL. */
    private Connection con;

    /**
     * Constructor que inyecta la conexión a la base de datos.
     * Esto permite reutilizar una única conexión para múltiples operaciones.
     * * @param con Objeto {@link Connection} activo.
     */
    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // ==========================================
    // OPERACIONES CRUD
    // ==========================================

    /**
     * Registra un nuevo usuario en la base de datos (Operación CREATE).
     * Convierte la fecha de creación a {@link java.sql.Timestamp} para mantener 
     * la precisión en MySQL.
     * * @param u El objeto {@link Usuario} que contiene la información a registrar.
     */
    public void insertar(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, apellidos, email, password, rol, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword()); 
            ps.setString(5, u.getRol());
            ps.setTimestamp(6, Timestamp.valueOf(u.getFechaCreacion()));
            
            ps.executeUpdate();
            System.out.println("¡Usuario guardado con éxito!");
            
        } catch (SQLException e) {
            System.err.println("No pude guardar el usuario: " + e.getMessage());
        }
    }

    /**
     * Recupera todos los usuarios registrados en el sistema (Operación READ).
     * Mapea cada fila del {@link ResultSet} a una instancia de la clase {@link Usuario},
     * manejando posibles valores nulos en los campos de fecha.
     * * @return Un {@link ArrayList} con todos los usuarios encontrados.
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
                u.setApellidos(rs.getString("apellidos"));
                u.setEmail(rs.getString("email"));
                u.setRol(rs.getString("rol"));
                
                Timestamp fc = rs.getTimestamp("fecha_creacion");
                if (fc != null) u.setFechaCreacion(fc.toLocalDateTime());
                
                Timestamp ua = rs.getTimestamp("ultimo_acceso");
                if (ua != null) u.setUltimoAcceso(ua.toLocalDateTime());

                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Hubo un fallo al leer los usuarios: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza los datos de un usuario existente (Operación UPDATE).
     * Este método modifica el nombre, apellidos, email, rol y la marca de último acceso.
     * * @param u Objeto {@link Usuario} con los datos actualizados y el ID correspondiente.
     */
    public void actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, apellidos=?, email=?, rol=?, ultimo_acceso=? WHERE idUsuario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellidos());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getRol());
            
            if (u.getUltimoAcceso() != null) {
                ps.setTimestamp(5, Timestamp.valueOf(u.getUltimoAcceso()));
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }
            
            ps.setInt(6, u.getIdUsuario());
            ps.executeUpdate();
            System.out.println("¡Datos actualizados!");
            
        } catch (SQLException e) {
            System.err.println("Error al intentar actualizar: " + e.getMessage());
        }
    }

    /**
     * Elimina un usuario de la base de datos (Operación DELETE) según su identificador.
     * * @param id El identificador único (ID) del usuario que se desea eliminar.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE idUsuario = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuario borrado correctamente.");
            
        } catch (SQLException e) {
            System.err.println("No se pudo eliminar al usuario: " + e.getMessage());
        }
    }
}