package DAO;

import java.sql.*;
import java.util.ArrayList;
import Modelo.HorarioTrabajo;

/**
 * <h2>Clase HorarioTrabajoDAO</h2>
 * Proporciona los métodos de acceso a datos (DAO) para la gestión de horarios en la base de datos.
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre la tabla {@code horario_trabajo}.
 * * @see Modelo.HorarioTrabajo
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class HorarioTrabajoDAO {

    /** Objeto de conexión activa a la base de datos MySQL. */
    private Connection con;

    /**
     * Constructor que establece la conexión con el motor de base de datos.
     * * @param con Objeto {@link Connection} que se utilizará para las consultas.
     */
    public HorarioTrabajoDAO(Connection con) {
        this.con = con;
    }

    // ==========================================
    // OPERACIONES CRUD
    // ==========================================

    /**
     * Inserta un nuevo registro de horario en la base de datos.
     * Convierte los objetos {@code LocalTime} del modelo a {@code java.sql.Time} para su almacenamiento.
     * * @param h Objeto {@link HorarioTrabajo} que contiene los datos del turno a guardar.
     */
    public void insertar(HorarioTrabajo h) {
        String sql = "INSERT INTO horario_trabajo (idUsuario, diaSemana, horaInicio, horaFin) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getIdUsuario());
            ps.setString(2, h.getDiaSemana());
            ps.setTime(3, Time.valueOf(h.getHoraInicio()));
            ps.setTime(4, Time.valueOf(h.getHoraFin()));

            ps.executeUpdate();
            System.out.println("¡Horario guardado perfectamente!");

        } catch (SQLException e) {
            System.err.println("No se pudo guardar el horario: " + e.getMessage());
        }
    }

    /**
     * Obtiene la totalidad de los registros de horarios almacenados.
     * Realiza el mapeo inverso de tipos de datos SQL a tipos de Java (LocalTime).
     * * @return Un {@link ArrayList} de objetos {@link HorarioTrabajo}. Si no hay registros, devuelve una lista vacía.
     */
    public ArrayList<HorarioTrabajo> listar() {
        ArrayList<HorarioTrabajo> lista = new ArrayList<>();
        String sql = "SELECT * FROM horario_trabajo";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HorarioTrabajo h = new HorarioTrabajo();
                h.setIdHorario(rs.getInt("idHorario"));
                h.setIdUsuario(rs.getInt("idUsuario"));
                h.setDiaSemana(rs.getString("diaSemana"));
                h.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                h.setHoraFin(rs.getTime("horaFin").toLocalTime());

                lista.add(h);
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar leer los horarios: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza la información de un turno existente en la base de datos.
     * Utiliza el {@code idHorario} como criterio de búsqueda principal.
     * * @param h Objeto {@link HorarioTrabajo} con los datos actualizados.
     */
    public void actualizar(HorarioTrabajo h) {
        String sql = "UPDATE horario_trabajo SET idUsuario=?, diaSemana=?, horaInicio=?, horaFin=? WHERE idHorario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getIdUsuario());
            ps.setString(2, h.getDiaSemana());
            ps.setTime(3, Time.valueOf(h.getHoraInicio()));
            ps.setTime(4, Time.valueOf(h.getHoraFin()));
            ps.setInt(5, h.getIdHorario());

            ps.executeUpdate();
            System.out.println("¡Horario actualizado!");

        } catch (SQLException e) {
            System.err.println("Fallo al actualizar el horario: " + e.getMessage());
        }
    }

    /**
     * Elimina un registro de horario de la tabla mediante su identificador único.
     * * @param idHorario El identificador del horario que se desea suprimir.
     */
    public void eliminar(int idHorario) {
        String sql = "DELETE FROM horario_trabajo WHERE idHorario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHorario);
            ps.executeUpdate();
            System.out.println("Horario eliminado de la base de datos.");

        } catch (SQLException e) {
            System.err.println("No pude borrar el horario: " + e.getMessage());
        }
    }
}