/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Proveedor;
import PckConexionBD.ConexionBD;

/**
 * <h2>Clase ProveedorDAO</h2>
 * Centraliza las operaciones CRUD (Crear, Leer, Actualizar, Borrar) para la tabla 'proveedores_maquinas'.
 * Incluye un sistema de recuperación en RAM y constructores de inyección de dependencias 
 * para garantizar la compatibilidad con el módulo de Compras de Miguel Ángel.
 * * @author Vicente
 * @version 0.0.1
 */
public class ProveedorDAO {

    private Connection con;
    private List<Proveedor> papeleraCache;

    // --- 1. Constructores ---

    /**
     * Constructor por defecto.
     * Obtiene la conexión centralizada a la base de datos e inicializa la caché RAM para la papelera.
     */
    public ProveedorDAO() {
        this.con = ConexionBD.getConnection();
        this.papeleraCache = new ArrayList<>();
    }

    /**
     * Constructor con inyección de dependencias.
     * Permite que otros DAOs (como PedidosCompraDAO) pasen su propia conexión activa.
     * Evita abrir múltiples conexiones simultáneas y previene problemas de concurrencia.
     * * @param con Conexión activa a la base de datos MySQL.
     */
    public ProveedorDAO(Connection con) {
        this.con = con;
        this.papeleraCache = new ArrayList<>();
    }

    // --- 2. Métodos de Acceso a Datos (CRUD) ---

    /**
     * Inserta un nuevo proveedor en la base de datos.
     * Utiliza consultas preparadas (PreparedStatement) para evitar ataques de inyección SQL.
     * * @param p Objeto Proveedor con la información a insertar (sin ID).
     * @return true si la inserción fue exitosa y afectó al menos a una fila, false si hubo error.
     */
    public boolean insertar(Proveedor p) {
        String sql = "INSERT INTO proveedores_maquinas (nombre, contacto, telefono, email, direccion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getContacto());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Extrae todos los proveedores registrados en la base de datos.
     * * @return Una lista (List) de objetos Proveedor. Devuelve una lista vacía si no hay registros o hay error.
     */
    public List<Proveedor> listarTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores_maquinas";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("contacto"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca un proveedor concreto utilizando su clave primaria.
     * Método indispensable para la construcción de objetos compuestos, como Pedidos de Compra.
     * * @param id El identificador único del proveedor en la base de datos.
     * @return El objeto Proveedor si la búsqueda es exitosa, o null si no se encuentra coincidencia.
     */
    public Proveedor buscarPorId(int id) {
        String sql = "SELECT * FROM proveedores_maquinas WHERE id_proveedor = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Proveedor(
                            rs.getInt("id_proveedor"),
                            rs.getString("nombre"),
                            rs.getString("contacto"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getString("direccion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar proveedor: " + e.getMessage());
        }
        return null;
    }

    // --- 3. Métodos del Sistema de Caché (Papelera) ---

    /**
     * Borra un proveedor de la tabla física pero guarda una copia de seguridad en memoria (RAM).
     * Protege contra el borrado accidental por parte del usuario.
     * * @param id El identificador del proveedor a eliminar.
     * @return true si se encontró, se borró en MySQL y se respaldó con éxito. false si hubo error.
     */
    public boolean eliminarConRespaldo(int id) {
        Proveedor p = buscarPorId(id);
        if (p != null) {
            String sql = "DELETE FROM proveedores_maquinas WHERE id_proveedor = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    papeleraCache.add(p);
                    System.out.println("[INFO] Proveedor movido a la papelera temporal.");
                    return true;
                }
            } catch (SQLException e) {
                System.err.println("Error al borrar proveedor: " + e.getMessage());
            }
        } else {
            System.out.println("[AVISO] No se encontró ningún proveedor con ID: " + id);
        }
        return false;
    }

    /**
     * Saca el último proveedor almacenado en la caché RAM y vuelve a registrarlo en MySQL.
     * Restaura los datos siguiendo una pila LIFO (último en borrar, primero en recuperar).
     * * @return true si la recuperación en base de datos funcionó. false si la papelera estaba vacía.
     */
    public boolean restaurarUltimoBorrado() {
        if (papeleraCache.isEmpty()) {
            System.out.println("[AVISO] La papelera de proveedores está vacía.");
            return false;
        }
        Proveedor p = papeleraCache.remove(papeleraCache.size() - 1);
        boolean exito = insertar(p);
        
        if (exito) {
            System.out.println("[OK] Proveedor restaurado con éxito en la Base de Datos.");
        }
        return exito;
    }

    /**
     * Imprime por consola el número de proveedores que se encuentran retenidos temporalmente en la caché.
     */
    public void verEstadoPapelera() {
        System.out.println("[Papelera Proveedores: " + papeleraCache.size() + " elementos]");
    }
}