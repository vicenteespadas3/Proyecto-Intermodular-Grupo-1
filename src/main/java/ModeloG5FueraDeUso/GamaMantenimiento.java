/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG5FueraDeUso;

// import java.util.ArrayList;
// import java.util.List;
/**
 * Clase de entidad que representa una Gama de Mantenimiento.
 * Define el conjunto de operaciones o criterios para un tipo específico de mantenimiento.
 * 
 * @author Eric
 */
public class GamaMantenimiento {
    // --- 1. ATRIBUTOS BÁSICOS ---
    private int idGama;
    private String nombre;
    private String tipoMantenimiento; // 'preventivo', 'correctivo', 'predictivo'
    private String tipoGama;          // 'neumatico', 'electrico', 'mecanico'
    private String descripcion;

    /* =========================================================
       --- 2. CÓDIGO FUTURO ---
       En un modelo avanzado, una Gama podría querer tener una 
       lista de todos los planes de mantenimiento que la utilizan.
       
    private List<PlanMantenimiento> planesAsociados;
       ========================================================= */

    // --- 3. CONSTRUCTORES ---
    /**
     * Constructor por defecto.
     */
    public GamaMantenimiento() {
        // this.planesAsociados = new ArrayList<>();
    }

    /**
     * Constructor con parámetros básicos.
     * 
     * @param nombre Nombre de la gama.
     * @param tipoMantenimiento Tipo de mantenimiento (preventivo, correctivo, predictivo).
     * @param tipoGama Tipo de gama (neumático, eléctrico, mecánico).
     * @param descripcion Descripción detallada.
     */
    public GamaMantenimiento(String nombre, String tipoMantenimiento, String tipoGama, String descripcion) {
        this.nombre = nombre;
        this.tipoMantenimiento = tipoMantenimiento;
        this.tipoGama = tipoGama;
        this.descripcion = descripcion;
        // this.planesAsociados = new ArrayList<>();
    }

    // --- 4. GETTERS Y SETTERS ---
    /**
     * Obtiene el ID de la gama.
     * @return ID de la gama.
     */
    public int getIdGama() { 
        return idGama; 
    }
    /**
     * Establece el ID de la gama.
     * @param idGama ID de la gama.
     */
    public void setIdGama(int idGama) { 
        this.idGama = idGama; 
    }

    /**
     * Obtiene el nombre de la gama.
     * @return Nombre de la gama.
     */
    public String getNombre() { 
        return nombre; 
    }
    /**
     * Establece el nombre de la gama.
     * @param nombre Nombre de la gama.
     */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /**
     * Obtiene el tipo de mantenimiento.
     * @return Tipo de mantenimiento.
     */
    public String getTipoMantenimiento() { 
        return tipoMantenimiento; 
    }
    /**
     * Establece el tipo de mantenimiento.
     * @param tipoMantenimiento Tipo de mantenimiento.
     */
    public void setTipoMantenimiento(String tipoMantenimiento) { 
        this.tipoMantenimiento = tipoMantenimiento; 
    }

    /**
     * Obtiene el tipo de gama.
     * @return Tipo de gama.
     */
    public String getTipoGama() { 
        return tipoGama; 
    }
    /**
     * Establece el tipo de gama.
     * @param tipoGama Tipo de gama.
     */
    public void setTipoGama(String tipoGama) { 
        this.tipoGama = tipoGama; 
    }

    /**
     * Obtiene la descripción de la gama.
     * @return Descripción.
     */
    public String getDescripcion() { 
        return descripcion; 
    }
    /**
     * Establece la descripción de la gama.
     * @param descripcion Descripción.
     */
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    // --- 5. TOSTRING ---
    @Override
    public String toString() {
        return "Gama [" + idGama + "] - " + nombre + " (" + tipoGama + " " + tipoMantenimiento + ")";
    }
}
