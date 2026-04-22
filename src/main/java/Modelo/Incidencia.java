/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;

/**
 * <h2>Clase Incidencia</h2>
 * Representa una avería o problema reportado en una máquina.
 * Sirve como puente de integración funcional entre el módulo de Activos 
 * y el módulo de Recursos Humanos (asignación de técnicos).
 * * @see DAO.IncidenciaDAO
 * @author Vicente
 * @version 0.0.1
 */
public class Incidencia {
    
    // --- Atributos de la Clase ---
    private int idIncidencia;
    private int idMaquina;
    private Integer idTecnicoAsignado; // Integer permite valores nulos en la BD
    private String descripcion;
    private LocalDateTime fechaReporte;
    private String prioridad; // Ej: "Alta", "Media", "Baja"
    private String estado;    // Ej: "Abierta", "En curso", "Cerrada"

    /**
     * Constructor vacio por defecto.
     * Util para frameworks de persistencia y para instanciar objetos vacios.
     */
    public Incidencia() {
    }

    /**
     * Constructor completo para instanciar incidencias recuperadas de la base de datos.
     * * @param idIncidencia Identificador unico de la incidencia.
     * @param idMaquina ID de la maquina afectada.
     * @param idTecnicoAsignado ID del usuario tecnico (puede ser null).
     * @param descripcion Detalle del problema detectado.
     * @param fechaReporte Fecha y hora exacta del reporte.
     * @param prioridad Nivel de urgencia.
     * @param estado Situacion actual de la reparacion.
     */
    public Incidencia(int idIncidencia, int idMaquina, Integer idTecnicoAsignado, String descripcion, 
                      LocalDateTime fechaReporte, String prioridad, String estado) {
        this.idIncidencia = idIncidencia;
        this.idMaquina = idMaquina;
        this.idTecnicoAsignado = idTecnicoAsignado;
        this.descripcion = descripcion;
        this.fechaReporte = fechaReporte;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    // --- Metodos Getter y Setter ---

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Integer getIdTecnicoAsignado() {
        return idTecnicoAsignado;
    }

    public void setIdTecnicoAsignado(Integer idTecnicoAsignado) {
        this.idTecnicoAsignado = idTecnicoAsignado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // --- Metodos Sobrescritos ---

    /**
     * Representacion textual de la incidencia.
     * Facilita la depuracion y la visualizacion en controles de lista.
     */
    @Override
    public String toString() {
        String tecnico = (idTecnicoAsignado != null) ? idTecnicoAsignado.toString() : "SIN ASIGNAR";
        return "Incidencia #" + idIncidencia + " [Maquina: " + idMaquina + "] - " +
               "Tecnico: " + tecnico + " - Prioridad: " + prioridad + " - Estado: " + estado;
    }
}