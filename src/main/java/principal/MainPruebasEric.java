package principal; 

import DAOG5FueraDeUso.GamaMantenimientoDAO;
import DAOG5FueraDeUso.PlanMantenimientoDAO;
import DAOG5FueraDeUso.TareaMantenimientoDAO;

import ModeloG5FueraDeUso.GamaMantenimiento;
import ModeloG5FueraDeUso.PlanMantenimiento;
import ModeloG5FueraDeUso.TareaMantenimiento;

import java.util.List;

/**
 * Clase de prueba para verificar el correcto funcionamiento de las lecturas
 * de gamas, planes y tareas de mantenimiento desde la base de datos.
 * 
 * @author Eric
 */
public class MainPruebasEric {

    /**
     * Punto de entrada principal para la ejecución de las pruebas de lectura.
     * 
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        
        System.out.println("==================================================");
        System.out.println("   ⚙️ INICIANDO PRUEBAS DE LECTURA (ERIC) ⚙️   ");
        System.out.println("==================================================");

        // --- 1. PROBAR GAMAS DE MANTENIMIENTO ---
        System.out.println("\n--- 1. LISTADO DE GAMAS DE MANTENIMIENTO ---");
        GamaMantenimientoDAO gamaDAO = new GamaMantenimientoDAO();
        List<GamaMantenimiento> listaGamas = gamaDAO.obtenerTodos();
        
        if (listaGamas.isEmpty()) {
            System.out.println("No hay gamas registradas en la base de datos.");
        } else {
            for (GamaMantenimiento gama : listaGamas) {
                // Al imprimir el objeto, Java llama automáticamente a tu método toString()
                System.out.println(gama); 
            }
        }

        // --- 2. PROBAR PLANES DE MANTENIMIENTO ---
        System.out.println("\n--- 2. LISTADO DE PLANES DE MANTENIMIENTO ---");
        PlanMantenimientoDAO planDAO = new PlanMantenimientoDAO();
        List<PlanMantenimiento> listaPlanes = planDAO.obtenerTodos();
        
        if (listaPlanes.isEmpty()) {
            System.out.println("No hay planes registrados en la base de datos.");
        } else {
            for (PlanMantenimiento plan : listaPlanes) {
                System.out.println(plan);
            }
        }

        // --- 3. PROBAR TAREAS DE MANTENIMIENTO ---
        System.out.println("\n--- 3. LISTADO DE TAREAS DE MANTENIMIENTO ---");
        TareaMantenimientoDAO tareaDAO = new TareaMantenimientoDAO();
        List<TareaMantenimiento> listaTareas = tareaDAO.obtenerTodas();
        
        if (listaTareas.isEmpty()) {
            System.out.println("No hay tareas registradas en la base de datos.");
        } else {
            for (TareaMantenimiento tarea : listaTareas) {
                System.out.println(tarea);
            }
        }

        System.out.println("\n==================================================");
        System.out.println("               ✅ PRUEBAS FINALIZADAS               ");
        System.out.println("==================================================");
    }
}