package principal;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import DAO.PedidosCompraDAO;
import DAO.TareasHerramientasDAO;
import Modelo.PedidosCompra;

public class MenuCompras {
    private static Scanner scanner = new Scanner(System.in);
    // ====================================================================
    // MÓDULO DE MIGUEL ÁNGEL (COMPRAS Y TAREAS)
    // ====================================================================

    public static boolean menuMiguelAngel(Connection con) {
        PedidosCompraDAO pcDao = new PedidosCompraDAO(con);
        TareasHerramientasDAO thDao = new TareasHerramientasDAO(con);
        boolean volver = false;

        while (!volver) {
            System.out.println("\n--- MÓDULO DE COMPRAS Y TAREAS (MIGUEL ÁNGEL) ---");
            System.out.println("1. Gestión de Pedidos de Compra");
            System.out.println("2. Gestión de Tareas y Herramientas");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("9. Salir del Sistema");
            System.out.print("Selección: ");

            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    menuGestionPedidos(pcDao);
                    break;
                case "2":
                    menuGestionTareas(thDao);
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

    private static void menuGestionPedidos(PedidosCompraDAO pcDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Listar Pedidos | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    // Usamos el objeto pcDao que recibimos por parámetro
                    List<PedidosCompra> lista = pcDao.listar();
                    if (lista.isEmpty()) {
                        System.out.println("No hay pedidos.");
                    } else {
                        lista.forEach(p -> System.out
                                .println("ID: " + p.getId_pedido() + " - Prov: " + p.getProveedor().getNombre()));
                    }
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuGestionTareas(TareasHerramientasDAO thDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE TAREAS Y HERRAMIENTAS ---");
            System.out.println("1. Listar Tareas | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    System.out.println(
                            "[AVISO] El método listar() de TareasHerramientasDAO depende de HerramientasDAO y TareasMantenimientoDAO, debido a ser la tabla intermedia entre estás dos tablas");
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
