package DAO;

import java.sql.*;
import java.util.ArrayList;
import Modelo.HorarioTrabajo;

/**
 * Clase HorarioTrabajoDAO
 * Aquí es donde manejo los horarios de los trabajadores en la base de datos.
 * Sirve para guardar los turnos nuevos, ver los que ya hay, cambiarlos o quitarlos.
* * @see Modelo.HorarioTrabajo
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class HorarioTrabajoDAO {

    private Connection con;

    /**
     * Constructor para recibir la conexión a MySQL.
     * La guardo en la variable 'con' para usarla en todos los métodos de abajo.
     */
    public HorarioTrabajoDAO(Connection con) {
        this.con = con;
    }

    // ==========================================
    // OPERACIONES CRUD (Los métodos de la BD)
    // ==========================================

    /**
     * CREATE - Guardar un nuevo horario.
     * Uso Time.valueOf para pasar la hora de Java al formato de la base de datos.
     */
    public void insertar(HorarioTrabajo h) {
        String sql = "INSERT INTO horario_trabajo (idUsuario, diaSemana, horaInicio, horaFin) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, h.getIdUsuario());
            ps.setString(2, h.getDiaSemana());
            // Paso el LocalTime a Time de SQL
            ps.setTime(3, Time.valueOf(h.getHoraInicio()));
            ps.setTime(4, Time.valueOf(h.getHoraFin()));

            ps.executeUpdate();
            System.out.println("¡Horario guardado perfectamente!");

        } catch (SQLException e) {
            System.out.println("No se pudo guardar el horario: " + e.getMessage());
        }
    }

    /**
     * READ - Traer la lista de todos los horarios.
     * Recorro la tabla y convierto cada fila en un objeto HorarioTrabajo.
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
                // Aquí hago el proceso al revés: de Time de SQL a LocalTime de Java
                h.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                h.setHoraFin(rs.getTime("horaFin").toLocalTime());

                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar leer los horarios: " + e.getMessage());
        }
        return lista;
    }

    /**
     * UPDATE - Modificar un horario que ya existe.
     * Lo busco por su idHorario para cambiar los datos.
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
            System.out.println("Fallo al actualizar el horario: " + e.getMessage());
        }
    }

    /**
     * DELETE - Borrar un horario de la tabla.
     */
    public void eliminar(int idHorario) {
        String sql = "DELETE FROM horario_trabajo WHERE idHorario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHorario);
            ps.executeUpdate();
            System.out.println("Horario eliminado de la base de datos.");

        } catch (SQLException e) {
            System.out.println("No pude borrar el horario: " + e.getMessage());
        }
    }
}