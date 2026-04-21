/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOG1;

// Importaciones para manejar bases de datos relacionales y listas dinámicas en Java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PckConexionBD.ConexionBD; 
import ModeloG1.ArchivoAdjunto;

/**
 * Clase DAO (Data Access Object) para gestionar la persistencia de los archivos adjuntos.
 * Esta clase se encarga de guardar y recuperar los documentos técnicos, manuales o fotos 
 * que están vinculados a una máquina específica.
 * @author Vicente
 */
public class ArchivoAdjuntoDAO {

    /**
     * Inserta el registro de un nuevo archivo en la base de datos.
     * Solo guarda los "metadatos" (nombre, ruta, tipo), no el archivo físico en sí.
     * * @param archivo El objeto ArchivoAdjunto que contiene los datos a guardar.
     * @return true si se insertó correctamente (afectó al menos 1 fila), false en caso de error.
     */
    public boolean insertar(ArchivoAdjunto archivo) {
        // Consulta SQL con parámetros posicionales ('?') para evitar Inyección SQL.
        // Fíjate que insertamos el 'id_maquina' (Clave Foránea) para mantener la relación relacional.
        String sql = "INSERT INTO archivos_adjuntos (nombre, ruta, tipo, id_maquina) VALUES (?, ?, ?, ?)";
        
        // try-with-resources: asegura el cierre de la conexión y el PreparedStatement
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Asignamos los valores del objeto a los interrogantes de la consulta
            pstmt.setString(1, archivo.getNombre());
            pstmt.setString(2, archivo.getRuta());
            pstmt.setString(3, archivo.getTipo());
            pstmt.setInt(4, archivo.getIdMaquina()); // Vinculamos el archivo a su máquina correspondiente
            
            // executeUpdate() ejecuta el INSERT. Si devuelve más de 0, fue un éxito.
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            // Capturamos el error por si la máquina a la que intentamos vincular el archivo no existe
            System.err.println("Error al insertar archivo adjunto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los archivos que pertenecen a una máquina en concreto.
     * Es ideal para cargar la "Ficha Técnica" de una máquina en la interfaz gráfica.
     * * @param idMaquina El ID de la máquina de la que queremos extraer sus archivos.
     * @return Una lista (List) de ArchivoAdjunto vinculados a esa máquina.
     */
    public List<ArchivoAdjunto> obtenerPorMaquina(int idMaquina) {
        // Instanciamos una lista vacía para ir guardando los resultados
        List<ArchivoAdjunto> listaArchivos = new ArrayList<>();
        
        // A diferencia del listarTodos() normal, aquí usamos la cláusula WHERE 
        // para filtrar solo los archivos cuya Clave Foránea coincida con el ID proporcionado.
        String sql = "SELECT * FROM archivos_adjuntos WHERE id_maquina = ?";
        
        // Primer try-with-resources para la Conexión y el PreparedStatement
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Sustituimos el '?' de la consulta SQL por el ID de la máquina que nos pasan
            pstmt.setInt(1, idMaquina);
            
            // Segundo try-with-resources anidado exclusivamente para el ResultSet.
            // Lo hacemos así porque executeQuery() devuelve un recurso que también debe cerrarse.
            try (ResultSet rs = pstmt.executeQuery()) {
                
                // Mientras haya filas en la respuesta de la base de datos...
                while (rs.next()) {
                    // Extraemos los datos de las columnas y creamos un nuevo objeto ArchivoAdjunto
                    ArchivoAdjunto archivo = new ArchivoAdjunto(
                        rs.getInt("id_archivo"),
                        rs.getString("nombre"),
                        rs.getString("ruta"),
                        rs.getString("tipo"),
                        rs.getInt("id_maquina")
                    );
                    // Añadimos el objeto a nuestra lista
                    listaArchivos.add(archivo);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener los archivos de la máquina: " + e.getMessage());
        }
        
        // Devolvemos la lista filtrada
        return listaArchivos;
    }
}