/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG5;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */

public class OrdenTrabajo {
    
    //esta clase guarda toda la informacion de un
    //trabajo pendiente o realizado
    
    //creacion de las variables
    private int idOrden;
    private int idMaquina;      // Solo el número, no el objeto
    private Integer idPlan;     // Integer para que pueda ser null
    private int idTipo;
    private int idEstado;
    private int idPrioridad;
    private Integer idRolAsignado;
    private LocalDate fechaProgramada;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinalizacion;
    private Integer tiempoEstimado;
    private Integer tiempoReal;
    private String observaciones;

    public OrdenTrabajo() {
    
    }

    // Getters y Setters de todos los campos...
    public int getIdOrden() {
        return idOrden; 
    }
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden; 
    }
    public int getIdMaquina() {
        return idMaquina; 
    }
    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina; 
    }
    public Integer getIdPlan() {
        return idPlan; 
    }
    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan; 
    }
    public int getIdTipo() {
        return idTipo; 
    }
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo; 
    }
    public int getIdEstado() {
        return idEstado; 
    }
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado; 
    }
    public int getIdPrioridad() {
        return idPrioridad; 
    }
    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad; 
    }
    public Integer getIdRolAsignado() {
        return idRolAsignado; 
    }
    public void setIdRolAsignado(Integer idRolAsignado) {
        this.idRolAsignado = idRolAsignado; 
    }
    public LocalDate getFechaProgramada() {
        return fechaProgramada; 
    }
    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada; 
    }
    public LocalDateTime getFechaInicio() {
        return fechaInicio; 
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio; 
    }
    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion; 
    }
    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion; 
    }
    public Integer getTiempoEstimado() {
        return tiempoEstimado; 
    }
    public void setTiempoEstimado(Integer tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado; 
    }
    public Integer getTiempoReal() {
        return tiempoReal; 
    }
    public void setTiempoReal(Integer tiempoReal) {
        this.tiempoReal = tiempoReal; 
    }
    public String getObservaciones() {
        return observaciones; 
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones; 
    }
}