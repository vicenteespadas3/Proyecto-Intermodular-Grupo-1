/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package principal;

import java.util.Scanner;
import java.sql.Connection;
import PckConexionBD.ConexionBD;
import DAO.*;

/**
 * <h2>Clase Main</h2>
 * Controlador central interactivo del ERP del Taller.
 * Actúa únicamente como enrutador principal hacia los submenús de cada módulo.
 * * @author Equipo 1
 * 
 * @version 0.0.1
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que arranca la aplicación.
     * Comprueba la conexión a la base de datos antes de permitir el acceso al menú
     * raíz.
     * * @param args Argumentos pasados por línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("Iniciando sistema ERP...");
        Connection conexionGlobal = ConexionBD.getConnection();

        if (conexionGlobal == null) {
            System.out.println("[ERROR] No se pudo establecer conexión con MySQL.");
            return;
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n==========================================");
            System.out.println("      ERP GESTIÓN DE TALLER - GRUPO 1     ");
            System.out.println("==========================================");
            System.out.println("1. Gestión de Activos e Infraestructura (Vicente)");
            System.out.println("2. Módulo Recursos Humanos (Guadalupe)");
            System.out.println("3. Módulo Compras y Suministros (Miguel Ángel)");
            System.out.println("0. Salir del Sistema");
            System.out.print("Selección: ");

            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    // ¡Mira qué limpio queda esto ahora!
                    MenuActivos menuActivos = new MenuActivos();
                    menuActivos.iniciarMenu();
                    break;
                case "2":
                    // Devuelve true si el usuario pulsó "9" para salir completamente
                    salir = menuGuadalupe(conexionGlobal);
                    break;
                case "3":
                    // Devuelve true si el usuario pulsó "9" para salir completamente
                    MenuCompras menuCompras = new MenuCompras();
                    menuCompras.menuMiguelAngel(conexionGlobal);
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        System.out.println("Cerrando el ERP... ¡Hasta pronto!");
    }

    // ====================================================================
    // MÓDULO DE GUADALUPE (RECURSOS HUMANOS Y CONFIGURACIÓN)
    // Nota del Tech Lead: Pendiente de que Guadalupe extraiga esto a
    // 'MenuRRHH.java'
    // ====================================================================

    private static boolean menuGuadalupe(Connection con) {
        UsuarioDAO uDao = new UsuarioDAO(con);
        HorarioTrabajoDAO hDao = new HorarioTrabajoDAO(con);
        DefinicionesDAO dDao = new DefinicionesDAO(con);
        boolean volver = false;

        while (!volver) {
            System.out.println("\n--- MÓDULO DE RECURSOS HUMANOS (GUADALUPE) ---");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Horarios de Trabajo");
            System.out.println("3. Gestión de Definiciones/Estados");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("9. Salir del Sistema");
            System.out.print("Selección: ");

            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    menuGestionUsuarios(uDao);
                    break;
                case "2":
                    menuGestionHorarios(hDao);
                    break;
                case "3":
                    menuGestionDefiniciones(dDao);
                    break;
                case "0":
                    return false;
                case "9":
                    return true;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        return false;
    }

    private static void menuGestionUsuarios(UsuarioDAO uDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Listar Usuarios | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    uDao.listar().forEach(System.out::println);
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuGestionHorarios(HorarioTrabajoDAO hDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE HORARIOS ---");
            System.out.println("1. Listar Horarios | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    hDao.listar().forEach(System.out::println);
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuGestionDefiniciones(DefinicionesDAO dDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE DEFINICIONES ---");
            System.out.println("1. Listar Categoría 'PEDIDOS' | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    dDao.listarPorCategoria("PEDIDOS").forEach(System.out::println);
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}