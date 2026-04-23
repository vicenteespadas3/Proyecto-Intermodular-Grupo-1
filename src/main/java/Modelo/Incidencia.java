/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 * <h2>Clase Incidencia</h2>
 * Representa un fallo, avería o tarea de mantenimiento en una máquina.
 * Esta clase actúa como puente intermodular relacionando una Máquina (Activos)
 * con un Usuario (Recursos Humanos).
 * * @author Equipo 1
 * @version 0.0.1
 */
public class Incidencia {

    private int idIncidencia;
    private Maquina maquina;
    private int idUsuario;
    private String descripcion;
    private Date fecha;
    private String prioridad;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Requisito fundamental para la instanciación de objetos POJO y uso genérico.
     */
    public Incidencia() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con ID.
     * Utilizado por el DAO cuando se recupera un registro existente desde la base de datos.
     * * @param idIncidencia Identificador único de la incidencia.
     * @param maquina Objeto Maquina asociada a la incidencia.
     * @param idUsuario ID del empleado que reporta o gestiona la incidencia.
     * @param descripcion Detalle del fallo o avería.
     * @param fecha Fecha en la que ocurrió o se reportó la incidencia.
     * @param prioridad Nivel de urgencia (ej. Alta, Media, Baja).
     */
    public Incidencia(int idIncidencia, Maquina maquina, int idUsuario, String descripcion, Date fecha, String prioridad) {
        this.idIncidencia = idIncidencia;
        this.maquina = maquina;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }

    /**
     * Constructor de inserción sin ID.
     * Utilizado para crear nuevas incidencias antes de guardarlas en MySQL (Auto-Incremental).
     * * @param maquina Objeto Maquina asociada a la incidencia.
     * @param idUsuario ID del empleado que reporta o gestiona la incidencia.
     * @param descripcion Detalle del fallo o avería.
     * @param fecha Fecha en la que ocurrió o se reportó la incidencia.
     * @param prioridad Nivel de urgencia (ej. Alta, Media, Baja).
     */
    public Incidencia(Maquina maquina, int idUsuario, String descripcion, Date fecha, String prioridad) {
        this.maquina = maquina;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }

    // --- 3. Getters y Setters ---

    /**
     * Obtiene el ID único de la incidencia.
     * @return El ID numérico de la incidencia.
     */
    public int getIdIncidencia() {
        return idIncidencia;
    }

    /**
     * Establece el ID único de la incidencia.
     * @param idIncidencia El identificador a asignar.
     */
    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    /**
     * Obtiene la máquina afectada por la incidencia.
     * @return El objeto Maquina asociado.
     */
    public Maquina getMaquina() {
        return maquina;
    }

    /**
     * Vincula una máquina a la incidencia.
     * @param maquina El objeto Maquina a vincular.
     */
    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    /**
     * Obtiene el ID del usuario asignado a la incidencia.
     * @return El ID numérico del usuario/trabajador.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Asigna un usuario/trabajador a la incidencia.
     * @param idUsuario El ID del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene la descripción detallada del problema.
     * @return Texto descriptivo de la incidencia.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define la descripción del problema o tarea.
     * @param descripcion Texto descriptivo a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha de reporte de la incidencia.
     * @return Objeto Date con la fecha.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la incidencia.
     * @param fecha Objeto Date a establecer.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el nivel de prioridad de la incidencia.
     * @return String indicando la prioridad (Alta, Media, Baja).
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * Establece el nivel de prioridad de la incidencia.
     * @param prioridad El texto de la prioridad a asignar.
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Representación en formato cadena de texto de la incidencia.
     * Útil para mostrar resúmenes por consola o interfaces gráficas.
     * @return Una cadena formateada con los datos principales de la incidencia.
     */
    @Override
    public String toString() {
        String sn = (maquina != null) ? maquina.getNumeroSerie() : "S/N Desconocido";
        return "[" + prioridad + "] Máquina " + sn + " (User ID: " + idUsuario + ") -> " + descripcion;
    }
}