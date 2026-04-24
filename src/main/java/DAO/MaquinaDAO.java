package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Maquina;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase MaquinaDAO</h2>
 * Centraliza las operaciones CRUD para la tabla 'maquinas'.
 * Mapea perfectamente la estructura de la base de datos e incorpora caché en RAM.
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
     * NOTA: `fecha_registro` no se pasa en el INSERT asumiendo que la BD lo genera con CURRENT_TIMESTAMP.
     * * @param m Objeto de la clase Maquina con los datos a insertar.
     * @return true si la fila se insertó correctamente, false en caso de error SQL.
     */
    public boolean insertar(Maquina m) {
        String sql = "INSERT INTO maquinas (nombre, tipo, numero_serie, ubicacion, fabricante, fecha_compra, estado, qr_code, proveedor_id, activa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getTipo());
            ps.setString(3, m.getNumeroSerie());
            ps.setString(4, m.getUbicacion());
            ps.setString(5, m.getFabricante());
            ps.setDate(6, new java.sql.Date(m.getFechaCompra().getTime()));
            ps.setString(7, m.getEstado());
            ps.setString(8, m.getQrCode());
            ps.setInt(9, m.getProveedorId());
            ps.setBoolean(10, m.isActiva());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar máquina: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera todas las máquinas registradas en el sistema mapeando todos sus atributos.
     * * @return Una lista de objetos Maquina. Si la tabla está vacía, retorna una lista vacía.
     */
    public List<Maquina> listarTodas() {
        List<Maquina> lista = new ArrayList<>();
        String sql = "SELECT * FROM maquinas";
        
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Maquina(
                        rs.getInt("id_maquina"), 
                        rs.getString("nombre"),
                        rs.getString("tipo"), 
                        rs.getString("numero_serie"),
                        rs.getString("ubicacion"), 
                        rs.getString("fabricante"),
                        rs.getDate("fecha_compra"),
                        rs.getString("estado"),
                        rs.getString("qr_code"),
                        rs.getInt("proveedor_id"),
                        rs.getBoolean("activa"),
                        rs.getTimestamp("fecha_registro") // Timestamp captura el DATETIME completo
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las máquinas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca una máquina específica mediante su número de identificación (PK).
     * * @param id El identificador único de la máquina a buscar.
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
                            rs.getString("nombre"),
                            rs.getString("tipo"), 
                            rs.getString("numero_serie"),
                            rs.getString("ubicacion"), 
                            rs.getString("fabricante"),
                            rs.getDate("fecha_compra"),
                            rs.getString("estado"),
                            rs.getString("qr_code"),
                            rs.getInt("proveedor_id"),
                            rs.getBoolean("activa"),
                            rs.getTimestamp("fecha_registro") // Timestamp captura el DATETIME completo
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
     * Elimina una máquina de MySQL, pero la guarda en la caché RAM como respaldo de seguridad.
     * * @param id El identificador de la máquina a borrar.
     * @return true si la máquina se borró y respaldó con éxito. false en caso contrario.
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
     * Recupera la última máquina eliminada de la papelera en RAM (LIFO) y la reinserta en la base de datos.
     * * @return true si la recuperación y re-inserción fue exitosa. false si la papelera está vacía.
     */
    public boolean restaurarUltimoBorrado() {
        if (papeleraCache.isEmpty()) {
            System.out.println("[AVISO] La papelera está vacía. No hay nada que restaurar.");
            return false;
        }
        // Extraemos la última máquina añadida a la caché
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