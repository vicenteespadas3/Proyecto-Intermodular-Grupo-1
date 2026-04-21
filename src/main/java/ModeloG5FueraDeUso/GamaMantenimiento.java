/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG5FueraDeUso;

// import java.util.ArrayList;
// import java.util.List;
/**
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
    public GamaMantenimiento() {
        // this.planesAsociados = new ArrayList<>();
    }

    public GamaMantenimiento(String nombre, String tipoMantenimiento, String tipoGama, String descripcion) {
        this.nombre = nombre;
        this.tipoMantenimiento = tipoMantenimiento;
        this.tipoGama = tipoGama;
        this.descripcion = descripcion;
        // this.planesAsociados = new ArrayList<>();
    }

    // --- 4. GETTERS Y SETTERS ---
    public int getIdGama() { 
        return idGama; 
    }
    public void setIdGama(int idGama) { 
        this.idGama = idGama; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getTipoMantenimiento() { 
        return tipoMantenimiento; 
    }
    public void setTipoMantenimiento(String tipoMantenimiento) { 
        this.tipoMantenimiento = tipoMantenimiento; 
    }

    public String getTipoGama() { 
        return tipoGama; 
    }
    public void setTipoGama(String tipoGama) { 
        this.tipoGama = tipoGama; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    // --- 5. TOSTRING ---
    @Override
    public String toString() {
        return "Gama [" + idGama + "] - " + nombre + " (" + tipoGama + " " + tipoMantenimiento + ")";
    }
}
