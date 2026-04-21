/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG1;

// Importaciones necesarias para manejar la base de datos (JDBC) y las estructuras de datos (Listas)
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD;
import ModeloG1.Maquina;

/**
 * Clase DAO (Data Access Object) para gestionar la persistencia de las máquinas.
 * Se encarga de aislar la lógica de negocio de la lógica de acceso a la base de datos,
 * realizando las operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre la tabla 'maquinas'.
 * * @author Vicente
 */
public class MaquinaDAO {

    /**
     * Inserta un nuevo registro de máquina en la base de datos.
     * * @param m Objeto de tipo Maquina que contiene los datos a guardar.
     * @return true si la inserción fue exitosa (se afectó 1 fila), false si hubo algún error.
     */
    public boolean insertar(Maquina m) {
        // Consulta SQL parametrizada (con '?') para evitar ataques de Inyección SQL
        String sql = "INSERT INTO maquinas (numero_serie, marca, modelo, id_estado, fecha_adquisicion) VALUES (?, ?, ?, ?, ?)";
        
        // Usamos try-with-resources para garantizar que la conexión y el PreparedStatement
        // se cierren automáticamente al terminar, liberando recursos del servidor.
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Asignamos los valores del objeto a las interrogaciones de la consulta SQL
            // El primer parámetro es el 1, el segundo el 2, etc.
            pstmt.setString(1, m.getNumeroSerie());
            pstmt.setString(2, m.getMarca());
            pstmt.setString(3, m.getModelo());
            pstmt.setInt(4, m.getIdEstado());
            pstmt.setDate(5, m.getFechaAdquisicion());
            
            // Ejecutamos la consulta. executeUpdate() devuelve el número de filas afectadas.
            // Si es mayor que 0, significa que el insert funcionó correctamente.
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            // Si falla la conexión o la consulta, capturamos el error para que el programa no "pete"
            System.err.println("Error al insertar máquina: " + e.getMessage());
            return false;
        }
    }

    /**
     * Crea una relación entre una máquina y un proveedor en la tabla intermedia.
     * Esto resuelve la relación de Muchos a Muchos (N:M) de la base de datos.
     * * @param idMaquina El identificador único de la máquina.
     * @param idProveedor El identificador único del proveedor.
     */
    public void vincularProveedor(int idMaquina, int idProveedor) {
        // Consulta SQL para insertar en la tabla puente
        String sql = "INSERT INTO proveedores_maquinas (id_maquina, id_proveedor) VALUES (?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Sustituimos los interrogantes por los IDs recibidos por parámetro
            pstmt.setInt(1, idMaquina);
            pstmt.setInt(2, idProveedor);
            
            // Ejecutamos la inserción en la tabla intermedia
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al vincular proveedor: " + e.getMessage());
        }
    }

    /**
     * Recupera todos los registros de la tabla 'maquinas' y los convierte en objetos Java.
     * * @return Una lista (List) que contiene todos los objetos Maquina encontrados en la BD.
     */
    public List<Maquina> listarTodas() {
        // Inicializamos una lista vacía donde iremos guardando las máquinas que encontremos
        List<Maquina> maquinas = new ArrayList<>();
        
        // Consulta SQL simple para traer todas las filas
        String sql = "SELECT * FROM maquinas";
        
        // Try-with-resources anidado instanciando Connection, Statement y ResultSet
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             // El ResultSet (rs) es como una tabla virtual que guarda los resultados devueltos por MySQL
             ResultSet rs = stmt.executeQuery(sql)) {
            
            // Recorremos el ResultSet fila por fila usando un bucle while
            while (rs.next()) {
                // Por cada fila, extraemos los datos de sus columnas y construimos un objeto Maquina nuevo
                Maquina m = new Maquina(
                    rs.getInt("id_maquina"),
                    rs.getString("numero_serie"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("id_estado"),
                    rs.getDate("fecha_adquisicion")
                );
                // Añadimos la máquina recién creada a nuestra lista
                maquinas.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar máquinas: " + e.getMessage());
        }
        
        // Devolvemos la lista llena (o vacía si no había datos o hubo un error)
        return maquinas;
    }
}