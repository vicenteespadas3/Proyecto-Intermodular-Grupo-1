/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG5FueraDeUso;

/**
 *
 * @author Eric
 */
public class TareaMantenimiento {
    
// --- 1. ATRIBUTOS BÁSICOS ---
    private int idTarea;
    private String descripcion;
    private int orden;
    private String instruccionesDetalladas;
    private boolean requiereEpi;
    private String episNecesarios;
    private String reglasSeguridad;
    private String reglasMedioAmbiente;   
    
    private PlanMantenimiento plan;

// --- 3. CONSTRUCTORES ---
    public TareaMantenimiento() {
    }

    public TareaMantenimiento(PlanMantenimiento plan, String descripcion, int orden, String instruccionesDetalladas, 
                              boolean requiereEpi, String episNecesarios, String reglasSeguridad, String reglasMedioAmbiente) {
        this.plan = plan;
        this.descripcion = descripcion;
        this.orden = orden;
        this.instruccionesDetalladas = instruccionesDetalladas;
        this.requiereEpi = requiereEpi;
        this.episNecesarios = episNecesarios;
        this.reglasSeguridad = reglasSeguridad;
        this.reglasMedioAmbiente = reglasMedioAmbiente;
    }
    
// --- 4. GETTERS Y SETTERS ---

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getInstruccionesDetalladas() {
        return instruccionesDetalladas;
    }

    public void setInstruccionesDetalladas(String instruccionesDetalladas) {
        this.instruccionesDetalladas = instruccionesDetalladas;
    }

    public boolean isRequiereEpi() {
        return requiereEpi;
    }

    public void setRequiereEpi(boolean requiereEpi) {
        this.requiereEpi = requiereEpi;
    }

    public String getEpisNecesarios() {
        return episNecesarios;
    }

    public void setEpisNecesarios(String episNecesarios) {
        this.episNecesarios = episNecesarios;
    }

    public String getReglasSeguridad() {
        return reglasSeguridad;
    }

    public void setReglasSeguridad(String reglasSeguridad) {
        this.reglasSeguridad = reglasSeguridad;
    }

    public String getReglasMedioAmbiente() {
        return reglasMedioAmbiente;
    }

    public void setReglasMedioAmbiente(String reglasMedioAmbiente) {
        this.reglasMedioAmbiente = reglasMedioAmbiente;
    }

    public PlanMantenimiento getPlan() {
        return plan;
    }

    public void setPlan(PlanMantenimiento plan) {
        this.plan = plan;
    }

// --- 5. TOSTRING ---
    @Override
    public String toString() {
        int planId = (plan != null) ? plan.getIdPlan() : 0;
        String epiMsg = requiereEpi ? " (Requiere EPI)" : " (Sin EPI)";
        return "Tarea [" + idTarea + "] - Plan " + planId + " | Orden: " + orden + " | " + descripcion + epiMsg;
    }  
}
