package DAO;

import java.sql.*;
import java.util.ArrayList;
import Modelo.Definiciones;

/**
 * <h2>Clase DefinicionesDAO</h2>
 * Gestiona el acceso a datos para la entidad {@link Definiciones}.
 * Proporciona los mecanismos necesarios para persistir y recuperar catálogos de estados
 * y etiquetas dinámicas, permitiendo que otros módulos (como Pedidos o Compras) 
 * utilicen clasificaciones estandarizadas.
 * * @see Modelo.Definiciones
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class DefinicionesDAO {

    /** Conexión activa a la base de datos MySQL. */
    private Connection con;

    /**
     * Constructor que inicializa la conexión para las operaciones del DAO.
     * * @param con Objeto {@link Connection} a utilizar.
     */
    public DefinicionesDAO(Connection con) {
        this.con = con;
    }

    /**
     * Inserta un nuevo registro de definición o estado en la base de datos.
     * Registra todos los campos incluyendo metadatos de orden visual y estado de actividad.
     * * @param d El objeto {@link Definiciones} con la información a insertar.
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
            System.err.println("Error al insertar definición: " + e.getMessage());
        }
    }

    /**
     * Recupera una lista filtrada de definiciones basadas en una categoría específica.
     * Muy útil para cargar componentes de interfaz como selectores o filtros de búsqueda.
     * * @param cat Nombre de la categoría a filtrar (ej. "PEDIDOS", "INCIDENCIAS").
     * @return Un {@link ArrayList} de {@link Definiciones} que pertenecen a dicha categoría.
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
                // Nota: Se recomienda completar el mapeo de campos si la lógica lo requiere
                lista.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer definiciones: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Elimina una definición de la base de datos de forma permanente.
     * * @param id El identificador único de la definición a suprimir.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM definiciones WHERE idDefinicion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Definición eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("No se pudo borrar: " + e.getMessage());
        }
    }
}