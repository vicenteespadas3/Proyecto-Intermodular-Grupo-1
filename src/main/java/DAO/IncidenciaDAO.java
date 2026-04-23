/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Incidencia;
import Modelo.Maquina;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase IncidenciaDAO</h2>
 * Gestiona el acceso a datos (CRUD) de los reportes de fallos en el taller.
 * Incluye un sistema de recuperación en RAM para evitar pérdidas accidentales.
 * * @author Equipo 1
 * @version 0.0.1
 */
public class IncidenciaDAO {

    private Connection con;
    private List<Incidencia> papeleraCache;

    /**
     * Constructor por defecto del DAO.
     * Inicializa la conexión con la Base de Datos y la papelera de reciclaje en RAM.
     */
    public IncidenciaDAO() {
        this.con = ConexionBD.getConnection();
        this.papeleraCache = new ArrayList<>();
    }

    /**
     * Inserta una nueva incidencia en la base de datos.
     * Transforma la fecha de Java (util.Date) a SQL (sql.Date) automáticamente.
     * * @param i El objeto Incidencia a persistir en MySQL.
     * @return true si la inserción fue exitosa, false en caso de error SQL.
     */
    public boolean insertar(Incidencia i) {
        String sql = "INSERT INTO incidencias (id_maquina, id_usuario, descripcion, fecha, prioridad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, i.getMaquina().getIdMaquina());
            ps.setInt(2, i.getIdUsuario());
            ps.setString(3, i.getDescripcion());
            ps.setDate(4, new java.sql.Date(i.getFecha().getTime()));
            ps.setString(5, i.getPrioridad());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar incidencia: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera el listado completo de todas las incidencias registradas.
     * Realiza un JOIN con la tabla de máquinas para recuperar los datos esenciales del activo.
     * * @return Una lista de objetos Incidencia. Estará vacía si no hay registros.
     */
    public List<Incidencia> listarTodas() {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT i.*, m.numero_serie, m.marca FROM incidencias i JOIN maquinas m ON i.id_maquina = m.id_maquina";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Maquina m = new Maquina();
                m.setIdMaquina(rs.getInt("id_maquina"));
                m.setNumeroSerie(rs.getString("numero_serie"));
                m.setMarca(rs.getString("marca"));

                lista.add(new Incidencia(
                        rs.getInt("id_incidencia"), m, rs.getInt("id_usuario"),
                        rs.getString("descripcion"), rs.getDate("fecha"), rs.getString("prioridad")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar incidencias: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca una incidencia específica a través de su identificador único.
     * * @param id El identificador (Primary Key) de la incidencia a buscar.
     * @return El objeto Incidencia encontrado, o null si no existe en la base de datos.
     */
    public Incidencia buscarPorId(int id) {
        String sql = "SELECT i.*, m.numero_serie, m.marca FROM incidencias i JOIN maquinas m ON i.id_maquina = m.id_maquina WHERE i.id_incidencia = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Maquina m = new Maquina();
                    m.setIdMaquina(rs.getInt("id_maquina"));
                    m.setNumeroSerie(rs.getString("numero_serie"));
                    m.setMarca(rs.getString("marca"));

                    return new Incidencia(
                            rs.getInt("id_incidencia"), m, rs.getInt("id_usuario"),
                            rs.getString("descripcion"), rs.getDate("fecha"), rs.getString("prioridad")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar incidencia: " + e.getMessage());
        }
        return null;
    }

    /**
     * Borra una incidencia de la base de datos pero la respalda previamente en la memoria RAM.
     * Permite deshacer la operación durante la sesión actual del usuario.
     * * @param id El identificador de la incidencia a eliminar.
     * @return true si se borró y respaldó correctamente, false en caso contrario.
     */
    public boolean eliminarConRespaldo(int id) {
        Incidencia i = buscarPorId(id);
        if (i != null) {
            String sql = "DELETE FROM incidencias WHERE id_incidencia = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    papeleraCache.add(i);
                    System.out.println("[INFO] Incidencia movida a la papelera temporal.");
                    return true;
                }
            } catch (SQLException e) {
                System.err.println("Error al borrar incidencia: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Restaura la última incidencia borrada sacándola de la caché RAM e insertándola de nuevo en MySQL.
     * Funciona bajo un esquema LIFO (Last In, First Out).
     * * @return true si se logró restaurar con éxito, false si la papelera estaba vacía o hubo error SQL.
     */
    public boolean restaurarUltimo() {
        if (papeleraCache.isEmpty()) {
            return false;
        }
        return insertar(papeleraCache.remove(papeleraCache.size() - 1));
    }

    /**
     * Imprime por consola la cantidad de elementos que actualmente residen en la caché de recuperación.
     */
    public void verEstadoPapelera() {
        System.out.println("[Papelera Incidencias: " + papeleraCache.size() + "]");
    }
}