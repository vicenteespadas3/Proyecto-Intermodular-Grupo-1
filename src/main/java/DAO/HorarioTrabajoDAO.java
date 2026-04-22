package DAO;

/**
 * @author Guadalupe M.G.M
 */

import java.sql.*;
import java.util.ArrayList;

import Modelo.HorarioTrabajo;


/**
 * Clase DAO para gestionar la persistencia de los horarios de trabajo.
 * Proporciona métodos para realizar operaciones CRUD en la tabla 'horario_trabajo'.
 */
public class HorarioTrabajoDAO {

    private Connection con;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * @param con Conexión activa.
     */
    public HorarioTrabajoDAO(Connection con) {
        this.con = con;
    }

    // CREATE
    /**
     * Inserta un nuevo horario de trabajo en la base de datos.
     * @param h Objeto HorarioTrabajo con los datos a insertar.
     */
    public void insertar(HorarioTrabajo h) {

        String sql = "INSERT INTO horario_trabajo (idUsuario, diaSemana, horaInicio, horaFin) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, h.getIdUsuario());
            ps.setString(2, h.getDiaSemana());
            ps.setTime(3, Time.valueOf(h.getHoraInicio()));
            ps.setTime(4, Time.valueOf(h.getHoraFin()));

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error INSERT horario: " + e.getMessage());
        }
    }

    // READ
    /**
     * Recupera todos los horarios de trabajo registrados.
     * @return Lista de objetos HorarioTrabajo.
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
            System.out.println("Error SELECT horario: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE
    /**
     * Actualiza un horario de trabajo existente.
     * @param h Objeto HorarioTrabajo con los datos actualizados.
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

        } catch (SQLException e) {
            System.out.println("Error UPDATE horario: " + e.getMessage());
        }
    }

    // DELETE
    /**
     * Elimina un horario de trabajo por su ID.
     * @param idHorario ID del horario a eliminar.
     */
    public void eliminar(int idHorario) {

        String sql = "DELETE FROM horario_trabajo WHERE idHorario=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHorario);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error DELETE horario: " + e.getMessage());
        }
    }
}