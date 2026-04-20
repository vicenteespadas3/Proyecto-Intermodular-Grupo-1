/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG5;

/**
 *
 * @author Usuario
 */

public class TareaEjecutada {
    
    //esta clase indica si una tarea concreta de la
    //lista se ha completado o no
    
    //creacion de las variables
    private int idTareaEjecutada;
    private int idEjecucion;    // Referencia a tu otra tabla
    private int idTarea;        // Solo el ID de la tarea
    private boolean completada;
    private String observaciones;
    private Integer tiempoEmpleado;

    public TareaEjecutada() {
    
    }

    // Getters y Setters...
    public int getIdTareaEjecutada() {
        return idTareaEjecutada; 
    }
    public void setIdTareaEjecutada(int idTareaEjecutada) {
        this.idTareaEjecutada = idTareaEjecutada; 
    }
    public int getIdEjecucion() {
        return idEjecucion; 
    }
    public void setIdEjecucion(int idEjecucion) {
        this.idEjecucion = idEjecucion; 
    }
    public int getIdTarea() {
        return idTarea; 
    }
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea; 
    }
    public boolean isCompletada() {
        return completada; 
    }
    public void setCompletada(boolean completada) {
        this.completada = completada; 
    }
    public String getObservaciones() {
        return observaciones; 
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones; 
    }
    public Integer getTiempoEmpleado() {
        return tiempoEmpleado; 
    }
    public void setTiempoEmpleado(Integer tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado; 
    }
}