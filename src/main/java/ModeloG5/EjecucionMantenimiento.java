/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG5;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Usuario
 */

public class EjecucionMantenimiento {
    private int idEjecucion;
    private int idOrden;        // Referencia a tu otra tabla
    private int idTecnico;      // Solo el ID del usuario
    private LocalDate fechaEjecucion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String observaciones;
    private String resultado;

    public EjecucionMantenimiento() {
    
    }
    
    // Getters y Setters...
    public int getIdEjecucion() {
        return idEjecucion; 
    }
    public void setIdEjecucion(int idEjecucion) {
        this.idEjecucion = idEjecucion; 
    }
    public int getIdOrden() {
        return idOrden; 
    }
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden; 
    }
    public int getIdTecnico() {
        return idTecnico; 
    }
    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico; 
    }
    public LocalDate getFechaEjecucion() {
        return fechaEjecucion; 
    }
    public void setFechaEjecucion(LocalDate fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion; 
    }
    public LocalTime getHoraInicio() {
        return horaInicio; 
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio; 
    }
    public LocalTime getHoraFin() {
        return horaFin; 
    }
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin; 
    }
    public String getObservaciones() {
        return observaciones; 
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones; 
    }
    public String getResultado() {
        return resultado; 
    }
    public void setResultado(String resultado) {
        this.resultado = resultado; 
    }
}
