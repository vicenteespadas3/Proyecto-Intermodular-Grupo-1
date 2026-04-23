/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package principal;

import java.util.Scanner;
import java.sql.Connection;
import java.util.Date;
import PckConexionBD.ConexionBD;
import DAO.*;
import Modelo.*;

/**
 * <h2>Clase Main</h2>
 * Controlador central interactivo del ERP del Taller.
 * Gestiona la jerarquía de menús, la captura de opciones del usuario y la 
 * inyección de dependencias entre las diferentes capas DAO.
 * * @author Equipo 1
 * @version 0.0.1
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que arranca la aplicación.
     * Comprueba la conexión a la base de datos antes de permitir el acceso al menú raíz.
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
                    menuVicente();
                    break;
                case "2":
                    salir = menuGuadalupe();
                    break;
                case "3":
                    salir = menuMiguelAngel();
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
    // MÓDULO DE VICENTE (ACTIVOS E INFRAESTRUCTURA)
    // ====================================================================

    /**
     * Despliega el menú principal del módulo de Activos.
     * Instancia todos los DAOs necesarios al inicio para asegurar que la caché 
     * en RAM no se destruya mientras el usuario navega entre submenús.
     */
    private static void menuVicente() {
        MaquinaDAO mDao = new MaquinaDAO();
        ProveedorDAO pDao = new ProveedorDAO();
        IncidenciaDAO iDao = new IncidenciaDAO();
        ArchivoAdjuntoDAO aDao = new ArchivoAdjuntoDAO();
        boolean volver = false;

        while (!volver) {
            System.out.println("\n--- ÁREA DE ACTIVOS E INFRAESTRUCTURA ---");
            System.out.println("1. Gestionar Máquinas");
            System.out.println("2. Gestionar Proveedores");
            System.out.println("3. Registro de Incidencias");
            System.out.println("4. Gestión Documental (Archivos Adjuntos)");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Selección: ");

            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    menuGestionMaquinas(mDao);
                    break;
                case "2":
                    menuGestionProveedores(pDao);
                    break;
                case "3":
                    menuGestionIncidencias(iDao, mDao);
                    break;
                case "4":
                    menuGestionArchivos(aDao, mDao);
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Submenú para gestionar el CRUD de la maquinaria física.
     * @param dao Instancia inyectada de MaquinaDAO para interactuar con la persistencia.
     */
    private static void menuGestionMaquinas(MaquinaDAO dao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE MÁQUINAS ---");
            dao.verEstadoPapelera();
            System.out.println("1. Listar | 2. Añadir Prueba | 3. Borrar | 4. Deshacer | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    dao.listarTodas().forEach(System.out::println);
                    break;
                case "2":
                    dao.insertar(new Maquina("SN-TEST", "Generica", "Pro-2024", 1, new Date()));
                    break;
                case "3":
                    System.out.print("ID a eliminar: ");
                    try {
                        dao.eliminarConRespaldo(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido.");
                    }
                    break;
                case "4":
                    dao.restaurarUltimoBorrado();
                    break;
                case "0":
                    volver = true;
                    break;
            }
        }
    }

    /**
     * Submenú para gestionar el CRUD de los proveedores de maquinaria.
     * @param dao Instancia inyectada de ProveedorDAO.
     */
    private static void menuGestionProveedores(ProveedorDAO dao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PROVEEDORES ---");
            dao.verEstadoPapelera();
            System.out.println("1. Listar | 2. Añadir Prueba | 3. Borrar | 4. Deshacer | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    dao.listarTodos().forEach(System.out::println);
                    break;
                case "2":
                    dao.insertar(new Proveedor("Suministros SL", "Paco", "600000000", "paco@mail.com", "Dir 1"));
                    break;
                case "3":
                    System.out.print("ID a eliminar: ");
                    try {
                        dao.eliminarConRespaldo(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido.");
                    }
                    break;
                case "4":
                    dao.restaurarUltimoBorrado();
                    break;
                case "0":
                    volver = true;
                    break;
            }
        }
    }

    /**
     * Submenú de operaciones para fallos técnicos, puenteando con RRHH.
     * @param iDao Instancia de IncidenciaDAO para la persistencia.
     * @param mDao Instancia de MaquinaDAO requerida para comprobar la existencia previa del activo.
     */
    private static void menuGestionIncidencias(IncidenciaDAO iDao, MaquinaDAO mDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- REGISTRO DE INCIDENCIAS ---");
            iDao.verEstadoPapelera();
            System.out.println("1. Ver Incidencias | 2. Reportar Fallo (ID 1) | 3. Borrar | 4. Deshacer | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    iDao.listarTodas().forEach(System.out::println);
                    break;
                case "2":
                    Maquina m = mDao.buscarPorId(1);
                    if (m != null) {
                        iDao.insertar(new Incidencia(m, 5, "Fallo detectado", new Date(), "Alta"));
                        System.out.println("[OK] Incidencia registrada.");
                    } else {
                        System.out.println("[ERROR] No existe la máquina con ID 1. Créala primero.");
                    }
                    break;
                case "3":
                    System.out.print("ID a eliminar: ");
                    try {
                        iDao.eliminarConRespaldo(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido.");
                    }
                    break;
                case "4":
                    iDao.restaurarUltimo();
                    break;
                case "0":
                    volver = true;
                    break;
            }
        }
    }

    /**
     * Submenú para gestionar los documentos, manuales e imágenes de la maquinaria.
     * @param aDao Instancia de ArchivoAdjuntoDAO para la persistencia.
     * @param mDao Instancia de MaquinaDAO requerida para vincular el documento a un activo real.
     */
    private static void menuGestionArchivos(ArchivoAdjuntoDAO aDao, MaquinaDAO mDao) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DOCUMENTAL (ARCHIVOS ADJUNTOS) ---");
            aDao.verEstadoPapelera();
            System.out.println("1. Listar Documentos | 2. Subir Manual (Prueba ID 1) | 3. Borrar | 4. Deshacer | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    aDao.listarTodos().forEach(System.out::println);
                    break;
                case "2":
                    Maquina m = mDao.buscarPorId(1);
                    if (m != null) {
                        aDao.insertar(new ArchivoAdjunto(m, "manual_instrucciones", "pdf", "C:/docs/manual.pdf", new Date()));
                        System.out.println("[OK] Documento adjuntado a la máquina.");
                    } else {
                        System.out.println("[ERROR] No existe la máquina con ID 1. Créala primero.");
                    }
                    break;
                case "3":
                    System.out.print("ID del documento a eliminar: ");
                    try {
                        aDao.eliminarConRespaldo(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido.");
                    }
                    break;
                case "4":
                    aDao.restaurarUltimo();
                    break;
                case "0":
                    volver = true;
                    break;
            }
        }
    }

    // ====================================================================
    // MÓDULO DE GUADALUPE (EN DESARROLLO)
    // ====================================================================

    /**
     * Submenú temporal para el módulo de Recursos Humanos.
     * Permite regresar al menú principal o emitir una señal de cierre total de la aplicación.
     * * @return true si el usuario solicita salir del programa, false si solo desea retroceder.
     */
    private static boolean menuGuadalupe() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MÓDULO DE RECURSOS HUMANOS (GUADALUPE) ---");
            System.out.println("[AVISO] Este módulo se encuentra actualmente en fase de desarrollo.");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("9. Salir del Sistema");
            System.out.print("Selección: ");

            String op = scanner.nextLine();
            if (op.equals("0")) {
                return false;
            } else if (op.equals("9")) {
                return true;
            } else {
                System.out.println("Opción no válida.");
            }
        }
        return false;
    }

    // ====================================================================
    // MÓDULO DE MIGUEL ÁNGEL (EN DESARROLLO)
    // ====================================================================

    /**
     * Submenú temporal para el módulo de Compras.
     * Permite regresar al menú principal o emitir una señal de cierre total de la aplicación.
     * * @return true si el usuario solicita salir del programa, false si solo desea retroceder.
     */
    private static boolean menuMiguelAngel() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- MÓDULO DE COMPRAS (MIGUEL ÁNGEL) ---");
            System.out.println("[AVISO] Este módulo se encuentra actualmente en fase de desarrollo.");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("9. Salir del Sistema");
            System.out.print("Selección: ");

            String op = scanner.nextLine();
            if (op.equals("0")) {
                return false;
            } else if (op.equals("9")) {
                return true;
            } else {
                System.out.println("Opción no válida.");
            }
        }
        return false;
    }
}