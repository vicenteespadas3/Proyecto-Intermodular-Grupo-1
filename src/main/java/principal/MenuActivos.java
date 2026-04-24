/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.util.Scanner;
import java.util.Date;
import DAO.*;
import Modelo.*;

/**
 * <h2>Clase MenuActivos</h2>
 * Gestiona toda la interfaz de usuario (consola) del módulo de Activos e Infraestructura.
 * Se ha separado del Main para cumplir con el Principio de Responsabilidad Única.
 * * @author Equipo 1 (Vicente)
 * @version 0.0.1
 */
public class MenuActivos {

    private Scanner scanner;
    
    // Instanciamos los DAOs a nivel de clase para que la papelera (caché RAM) 
    // no se borre mientras el usuario navega por los distintos submenús.
    private MaquinaDAO mDao;
    private ProveedorDAO pDao;
    private IncidenciaDAO iDao;
    private ArchivoAdjuntoDAO aDao;

    /**
     * Constructor de la clase.
     * Inicializa el lector de teclado y las conexiones a la base de datos (DAOs).
     */
    public MenuActivos() {
        this.scanner = new Scanner(System.in);
        this.mDao = new MaquinaDAO();
        this.pDao = new ProveedorDAO();
        this.iDao = new IncidenciaDAO();
        this.aDao = new ArchivoAdjuntoDAO();
    }

    /**
     * Inicia el bucle principal del módulo de Activos.
     */
    public void iniciarMenu() {
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
                    menuGestionMaquinas();
                    break;
                case "2":
                    menuGestionProveedores();
                    break;
                case "3":
                    menuGestionIncidencias();
                    break;
                case "4":
                    menuGestionArchivos();
                    break;
                case "0":
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuGestionMaquinas() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE MÁQUINAS ---");
            mDao.verEstadoPapelera();
            System.out.println("1. Listar | 2. Añadir Prueba | 3. Borrar | 4. Deshacer | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    mDao.listarTodas().forEach(System.out::println);
                    break;
                case "2":
                    mDao.insertar(new Maquina("Taladro CNC", "Corte", "SN-999", "Nave A", "Bosch", new Date(), "Operativa", "QR-999", 1, true));
                    break;
                case "3":
                    System.out.print("ID a eliminar: ");
                    try {
                        mDao.eliminarConRespaldo(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido.");
                    }
                    break;
                case "4":
                    mDao.restaurarUltimoBorrado();
                    break;
                case "0":
                    volver = true;
                    break;
            }
        }
    }

    private void menuGestionProveedores() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PROVEEDORES ---");
            pDao.verEstadoPapelera();
            System.out.println("1. Listar | 2. Añadir Prueba | 3. Borrar | 4. Deshacer | 0. Volver");
            System.out.print("Opción: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1":
                    pDao.listarTodos().forEach(System.out::println);
                    break;
                case "2":
                    pDao.insertar(new Proveedor("Suministros SL", "Paco", "600000000", "paco@mail.com", "Dir 1"));
                    break;
                case "3":
                    System.out.print("ID a eliminar: ");
                    try {
                        pDao.eliminarConRespaldo(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, introduce un número válido.");
                    }
                    break;
                case "4":
                    pDao.restaurarUltimoBorrado();
                    break;
                case "0":
                    volver = true;
                    break;
            }
        }
    }

    private void menuGestionIncidencias() {
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
                        iDao.insertar(new Incidencia(m, 1, new Date(), "Fallo detectado en motor", "Mecánico", "Alta", "Pendiente", 0, null, ""));
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

    private void menuGestionArchivos() {
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
                        aDao.insertar(new ArchivoAdjunto("maquina", m.getIdMaquina(), "manual_instrucciones.pdf", "C:/docs/manual.pdf", new Date(), 1));
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
}