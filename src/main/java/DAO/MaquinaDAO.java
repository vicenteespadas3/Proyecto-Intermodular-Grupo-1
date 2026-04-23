/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Maquina;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase MaquinaDAO</h2>
 * Centraliza las operaciones CRUD (Crear, Leer, Actualizar, Borrar) para la entidad Maquina.
 * Incorpora un mecanismo de seguridad basado en caché (RAM) para recuperar borrados accidentales.
 * * @author Vicente
 * @version 0.0.1
 */
public class MaquinaDAO {

    private Connection con;
    private List<Maquina> papeleraCache;

    // --- 1. Constructores ---

    /**
     * Constructor por defecto.
     * Obtiene la conexión centralizada a la base de datos e inicializa la caché de la papelera.
     */
    public MaquinaDAO() {
        this.con = ConexionBD.getConnection();
        this.papeleraCache = new ArrayList<>();
    }

    // --- 2. Métodos de Acceso a Datos (CRUD) ---

    /**
     * Inserta una nueva máquina en la base de datos MySQL.
     * Realiza automáticamente la conversión de fechas de Java (util.Date) a SQL (sql.Date).
     * * @param m Objeto de la clase Maquina con los datos a insertar (sin ID).
     * @return true si la fila se insertó correctamente, false en caso de error o excepciones SQL.
     */
    public boolean insertar(Maquina m) {
        String sql = "INSERT INTO maquinas (numero_serie, marca, modelo, id_estado, fecha_adquisicion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNumeroSerie());
            ps.setString(2, m.getMarca());
            ps.setString(3, m.getModelo());
            ps.setInt(4, m.getIdEstado());
            ps.setDate(5, new java.sql.Date(m.getFechaAdquisicion().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar máquina: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera todas las máquinas registradas en el sistema.
     * * @return Una lista de objetos Maquina. Si la tabla está vacía o hay error, retorna una lista vacía.
     */
    public List<Maquina> listarTodas() {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquinas";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Maquina(
                        rs.getInt("id_maquina"), 
                        rs.getString("numero_serie"),
                        rs.getString("marca"), 
                        rs.getString("modelo"),
                        rs.getInt("id_estado"), 
                        rs.getDate("fecha_adquisicion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las máquinas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca una máquina específica mediante su número de identificación.
     * * @param id El identificador único (Primary Key) de la máquina a buscar.
     * @return El objeto Maquina si se encuentra el registro, o null si no existe.
     */
    public Maquina buscarPorId(int id) {
        String sql = "SELECT * FROM maquinas WHERE id_maquina = ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Maquina(
                            rs.getInt("id_maquina"), 
                            rs.getString("numero_serie"),
                            rs.getString("marca"), 
                            rs.getString("modelo"),
                            rs.getInt("id_estado"), 
                            rs.getDate("fecha_adquisicion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar la máquina: " + e.getMessage());
        }
        return null;
    }

    // --- 3. Métodos del Sistema de Caché (Papelera) ---

    /**
     * Elimina una máquina físicamente de la base de datos, pero la guarda antes en 
     * una lista de memoria RAM como respaldo de seguridad.
     * * @param id El identificador de la máquina que se desea borrar.
     * @return true si la máquina existía, se borró de MySQL y se guardó en caché. false en caso contrario.
     */
    public boolean eliminarConRespaldo(int id) {
        Maquina m = buscarPorId(id);
        if (m != null) {
            String sql = "DELETE FROM maquinas WHERE id_maquina = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    papeleraCache.add(m);
                    System.out.println("[INFO] Máquina movida a la papelera temporal.");
                    return true;
                }
            } catch (SQLException e) {
                System.err.println("Error al borrar máquina: " + e.getMessage());
            }
        } else {
            System.out.println("[AVISO] No se encontró ninguna máquina con ID: " + id);
        }
        return false;
    }

    /**
     * Recupera la última máquina eliminada (LIFO) de la papelera en RAM y la 
     * vuelve a insertar en la base de datos.
     * * @return true si la recuperación y re-inserción fue exitosa. false si la papelera está vacía.
     */
    public boolean restaurarUltimoBorrado() {
        if (papeleraCache.isEmpty()) {
            System.out.println("[AVISO] La papelera está vacía. No hay nada que restaurar.");
            return false;
        }
        // Se extrae la última máquina añadida a la lista
        Maquina m = papeleraCache.remove(papeleraCache.size() - 1);
        boolean exito = insertar(m);
        
        if (exito) {
            System.out.println("[OK] Máquina restaurada con éxito en la Base de Datos.");
        }
        return exito;
    }

    /**
     * Imprime por consola cuántas máquinas se encuentran actualmente respaldadas en la RAM.
     */
    public void verEstadoPapelera() {
        System.out.println("[Papelera de Máquinas: " + papeleraCache.size() + " elementos]");
    }
}