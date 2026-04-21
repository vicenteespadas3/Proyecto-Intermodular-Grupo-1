/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

// Importaciones necesarias para operaciones de base de datos y manejo de listas
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD;
import Modelo.Proveedor;

/**
 * Clase DAO (Data Access Object) para gestionar la persistencia de los proveedores.
 * Se encarga de aislar la lógica de acceso a la base de datos (tabla 'proveedores') 
 * del resto de la aplicación, siguiendo el patrón de diseño DAO.
 * * @author Vicente
 */
public class ProveedorDAO {

    /**
     * Inserta un nuevo proveedor en la base de datos.
     * Utiliza consultas parametrizadas para evitar vulnerabilidades de Inyección SQL.
     * * @param p El objeto Proveedor con los datos que queremos insertar.
     * @return true si la inserción fue correcta (1 o más filas afectadas), false en caso de error.
     */
    public boolean insertar(Proveedor p) {
        // Preparamos la consulta SQL con '?' como marcadores de posición para los valores.
        // No pasamos el ID porque la base de datos lo genera automáticamente (Auto Incremental).
        String sql = "INSERT INTO proveedores (cif, nombre, telefono, email_contacto) VALUES (?, ?, ?, ?)";
        
        // Bloque try-with-resources: asegura que la conexión y el PreparedStatement
        // se cierren automáticamente al terminar, evitando fugas de memoria (memory leaks).
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Sustituimos los marcadores '?' por los valores reales del objeto Proveedor
            pstmt.setString(1, p.getCif());
            pstmt.setString(2, p.getNombre());
            pstmt.setString(3, p.getTelefono());
            pstmt.setString(4, p.getEmailContacto());
            
            // Ejecutamos la consulta. Si executeUpdate() devuelve > 0, significa que 
            // la fila se ha guardado correctamente en la tabla.
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            // Si hay un error (ej. CIF duplicado o base de datos apagada), lo capturamos
            System.err.println("Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera todos los proveedores registrados en la base de datos.
     * Convierte cada registro (fila) de la tabla en un objeto de la clase Proveedor.
     * * @return Una lista (List) de objetos Proveedor. Si no hay datos, devuelve una lista vacía.
     */
    public List<Proveedor> listarTodos() {
        // Instanciamos la lista donde almacenaremos los resultados
        List<Proveedor> listaProveedores = new ArrayList<>();
        
        // Consulta SQL básica para obtener todas las columnas de todos los proveedores
        String sql = "SELECT * FROM proveedores";
        
        // Usamos try-with-resources anidando la creación del Statement y la ejecución del ResultSet
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // El ResultSet contiene la "tabla" devuelta por la BD
            
            // Recorremos el ResultSet iterando fila por fila mientras existan resultados
            while (rs.next()) {
                // Instanciamos un nuevo objeto Proveedor mapeando las columnas de la BD a los atributos de la clase
                Proveedor p = new Proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("cif"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email_contacto")
                );
                
                // Añadimos el proveedor recién mapeado a nuestra lista
                listaProveedores.add(p);
            }
            
        } catch (SQLException e) {
            // Capturamos cualquier error de lectura o conexión
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }
        
        // Retornamos la lista final (lista con datos o vacía si falló)
        return listaProveedores;
    }
}