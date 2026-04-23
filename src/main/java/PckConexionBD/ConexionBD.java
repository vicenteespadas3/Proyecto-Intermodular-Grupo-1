package PckConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos MySQL.
 * Proporciona un método estático para obtener una instancia de Connection.
 * 
 * @author Vicente
 */
public class ConexionBD {

    // 1. Datos de conexión a tu base de datos local
    // Asegúrate de que el puerto sea el 3306 (el habitual de MySQL/XAMPP)
    private static final String URL = "jdbc:mysql://172.20.10.2:3306/GestionTaller?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "admin"; // Cambia esto si tu usuario en clase es distinto
    private static final String PASSWORD = "123456"; // Cambia esto si tienes contraseña en tu MySQL local
    /*
     * private static final String URL =
     * "jdbc:mysql://192.168.56.103:3306/gestiontaller?useSSL=false&serverTimezone=UTC";
     * private static final String USUARIO = "usuario";
     * private static final String PASSWORD = "1234";
     */

    /**
     * Establece y devuelve una conexión a la base de datos.
     * 
     * @return Connection objeto de conexión SQL, o null si falla.
     */
    public static Connection getConnection() {
        Connection conexion = null;
        try {
            // Se establece la conexión usando el driver que Maven nos acaba de descargar
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);

        } catch (SQLException e) {
            System.err.println("ERROR: No se pudo conectar a la base de datos 'GestionTaller'.");
            System.err.println("Comprueba que tu servidor MySQL (XAMPP/Workbench) esté encendido.");
            System.err.println("Detalle del error: " + e.getMessage());
        }
        return conexion;
    }

    /**
     * Método principal para probar la conexión a la base de datos.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        System.out.println("Intentando conectar a la base de datos...");
        Connection pruebaConexion = ConexionBD.getConnection();

        if (pruebaConexion != null) {
            System.out.println("¡ÉXITO! Conexión a MySQL establecida correctamente.");
            try {
                pruebaConexion.close(); // Cerramos la conexión de prueba
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Fallo en la conexión. Revisa los datos de URL, USUARIO o PASSWORD.");
        }
    }

}
