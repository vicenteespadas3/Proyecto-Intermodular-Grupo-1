/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package principal;

import java.sql.Connection;
import java.util.Scanner;

// Importamos la conexión central
import PckConexionBD.ConexionBD;

// DAOs de Vicente
import DAO.MaquinaDAO;
import DAO.ProveedorDAO;

// DAOs de Guadalupe
import DAO.UsuarioDAO;

// DAOs de Miguel Ángel
import DAO.PedidosCompraDAO;

/**
 * Clase principal que actúa como menú de entrada para el ERP del Taller.
 * Integra los módulos de Vicente, Guadalupe y Miguel Ángel.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // 1. Iniciamos la conexión global.
        // Necesaria porque Guadalupe y Miguel Ángel la piden por parámetro en sus constructores.
        System.out.println("Iniciando sistema y verificando conexion a la base de datos...");
        Connection conexionGlobal = ConexionBD.getConnection();

        if (conexionGlobal == null) {
            System.out.println("[ERROR CRITICO] No se pudo conectar a la base de datos.");
            System.out.println("Asegurate de que XAMPP/MySQL esta encendido.");
            return; // Detenemos el programa si no hay base de datos
        }

        System.out.println("[OK] Conexion establecida con exito.\n");

        // 2. Bucle principal del menú
        while (!salir) {
            System.out.println("==========================================");
            System.out.println("      ERP GESTION DE TALLER - GRUPO 1     ");
            System.out.println("==========================================");
            System.out.println("1. Modulo de Activos y Proveedores (Vicente)");
            System.out.println("2. Modulo de Recursos Humanos (Guadalupe)");
            System.out.println("3. Modulo de Compras (Miguel Angel)");
            System.out.println("0. Salir del Sistema");
            System.out.print("Selecciona una opcion: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    menuVicente();
                    break;
                case "2":
                    menuGuadalupe(conexionGlobal);
                    break;
                case "3":
                    menuMiguelAngel(conexionGlobal);
                    break;
                case "0":
                    salir = true;
                    System.out.println("Cerrando las conexiones... Hasta pronto!");
                    break;
                default:
                    System.out.println("[AVISO] Opcion no valida. Intentalo de nuevo.\n");
            }
        }

        // 3. Limpieza de recursos al salir
        scanner.close();
        try {
            if (conexionGlobal != null && !conexionGlobal.isClosed()) {
                conexionGlobal.close();
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la conexion global: " + e.getMessage());
        }
    }

    // ==========================================================
    // SUBMENUS DE CADA INTEGRANTE
    // ==========================================================

    private static void menuVicente() {
        System.out.println("\n--- MODULO DE ACTIVOS (VICENTE) ---");
        System.out.println("Conectando con MaquinaDAO y ProveedorDAO...");
        
        // Vicente usa try-with-resources internamente, así que instanciamos sin pasar la conexión
        MaquinaDAO maquinaDAO = new MaquinaDAO();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        
        System.out.println("[OK] Modulos de Activos instanciados correctamente.");
        System.out.println(">> Funcionalidad lista para operar con la base de datos.\n");
    }

    private static void menuGuadalupe(Connection con) {
        System.out.println("\n--- MODULO DE RRHH (GUADALUPE) ---");
        System.out.println("Inyectando conexion en UsuarioDAO...");
        
        // Guadalupe requiere que le pasemos la conexión abierta
        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        
        System.out.println("[OK] Modulo de Usuarios instanciado correctamente.");
        System.out.println(">> Funcionalidad lista para leer trabajadores y turnos.\n");
    }

    private static void menuMiguelAngel(Connection con) {
        System.out.println("\n--- MODULO DE COMPRAS (MIGUEL ANGEL) ---");
        System.out.println("Inyectando conexion en PedidosCompraDAO...");
        
        // Miguel Ángel también requiere la conexión abierta
        PedidosCompraDAO pedidosDAO = new PedidosCompraDAO(con);
        
        System.out.println("[OK] Modulo de Compras instanciado.");
        System.out.println("[AVISO] Nota de equipo: Pendiente de que se anadan las sentencias SQL al DAO.\n");
    }
}