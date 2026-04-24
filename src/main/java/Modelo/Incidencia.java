package Modelo;

import java.util.Date;

/**
 * <h2>Clase Incidencia</h2>
 * Representa un fallo, avería o tarea de mantenimiento en una máquina.
 * Esta clase actúa como puente intermodular relacionando una Máquina (Activos)
 * con dos posibles Usuarios (quien reporta y el técnico asignado).
 * Mapea exactamente con la tabla 'incidencias' de MySQL.
 * * @author Vicente
 * @version 0.0.1
 */
public class Incidencia {

    /** Identificador único autonumérico de la incidencia (PK - int). */
    private int idIncidencia;
    
    /** Objeto Máquina afectada por la incidencia (FK -> maquinas). */
    private Maquina maquina;
    
    /** Identificador del usuario que reporta el fallo (FK -> usuarios). */
    private int idUsuarioReporta;
    
    /** Fecha y hora exacta en la que se registró la incidencia (datetime). */
    private Date fechaReporte;
    
    /** Descripción detallada del problema detectado (text). */
    private String descripcion;
    
    /** Categoría del fallo, ej. 'Eléctrico', 'Mecánico' (varchar 20). */
    private String tipo;
    
    /** Nivel de urgencia, ej. 'Alta', 'Media', 'Baja' (varchar 10). */
    private String prioridad;
    
    /** Estado de la reparación, ej. 'Pendiente', 'En progreso', 'Resuelta' (varchar 15). */
    private String estado;
    
    /** Identificador del técnico responsable de la reparación (FK -> usuarios). */
    private int idTecnicoAsignado;
    
    /** Fecha y hora exacta en la que se solucionó el problema (datetime). */
    private Date fechaResolucion;
    
    /** Detalle de las acciones tomadas para arreglar la máquina (text). */
    private String solucionAplicada;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Requisito fundamental para la instanciación de objetos POJO y frameworks.
     */
    public Incidencia() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con ID.
     * Utilizado por el DAO cuando recupera un registro existente desde la base de datos.
     * * @param idIncidencia Identificador único de la incidencia.
     * @param maquina Objeto Maquina asociada a la incidencia.
     * @param idUsuarioReporta ID del empleado que reporta.
     * @param fechaReporte Fecha y hora de creación.
     * @param descripcion Detalle del fallo.
     * @param tipo Categoría del fallo.
     * @param prioridad Nivel de urgencia.
     * @param estado Situación actual del ticket.
     * @param idTecnicoAsignado ID del técnico que repara (0 si no hay asignado).
     * @param fechaResolucion Fecha de cierre del ticket (null si está pendiente).
     * @param solucionAplicada Informe de reparación.
     */
    public Incidencia(int idIncidencia, Maquina maquina, int idUsuarioReporta, Date fechaReporte, 
                      String descripcion, String tipo, String prioridad, String estado, 
                      int idTecnicoAsignado, Date fechaResolucion, String solucionAplicada) {
        this.idIncidencia = idIncidencia;
        this.maquina = maquina;
        this.idUsuarioReporta = idUsuarioReporta;
        this.fechaReporte = fechaReporte;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = estado;
        this.idTecnicoAsignado = idTecnicoAsignado;
        this.fechaResolucion = fechaResolucion;
        this.solucionAplicada = solucionAplicada;
    }

    /**
     * Constructor de inserción sin ID.
     * Utilizado para crear nuevas incidencias antes de guardarlas en MySQL.
     * * @param maquina Objeto Maquina asociada a la incidencia.
     * @param idUsuarioReporta ID del empleado que reporta.
     * @param fechaReporte Fecha y hora de creación.
     * @param descripcion Detalle del fallo.
     * @param tipo Categoría del fallo.
     * @param prioridad Nivel de urgencia.
     * @param estado Situación actual del ticket.
     * @param idTecnicoAsignado ID del técnico que repara.
     * @param fechaResolucion Fecha de cierre del ticket.
     * @param solucionAplicada Informe de reparación.
     */
    public Incidencia(Maquina maquina, int idUsuarioReporta, Date fechaReporte, 
                      String descripcion, String tipo, String prioridad, String estado, 
                      int idTecnicoAsignado, Date fechaResolucion, String solucionAplicada) {
        this.maquina = maquina;
        this.idUsuarioReporta = idUsuarioReporta;
        this.fechaReporte = fechaReporte;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = estado;
        this.idTecnicoAsignado = idTecnicoAsignado;
        this.fechaResolucion = fechaResolucion;
        this.solucionAplicada = solucionAplicada;
    }

    // --- 3. Getters y Setters ---

    /**
     * Obtiene el ID único de la incidencia.
     * @return El ID numérico.
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
     * Obtiene el ID del usuario que registró el fallo.
     * @return El ID numérico del usuario.
     */
    public int getIdUsuarioReporta() {
        return idUsuarioReporta;
    }

    /**
     * Asigna el usuario que reporta la incidencia.
     * @param idUsuarioReporta El ID del usuario.
     */
    public void setIdUsuarioReporta(int idUsuarioReporta) {
        this.idUsuarioReporta = idUsuarioReporta;
    }

    /**
     * Obtiene la fecha y hora del reporte original.
     * @return Objeto Date con el timestamp.
     */
    public Date getFechaReporte() {
        return fechaReporte;
    }

    /**
     * Establece la fecha y hora del reporte.
     * @param fechaReporte Objeto Date a asignar.
     */
    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    /**
     * Obtiene la descripción detallada del problema.
     * @return Texto descriptivo.
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
     * Obtiene el tipo o categoría del fallo.
     * @return Tipo de incidencia.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el tipo o categoría del fallo.
     * @param tipo Cadena de texto con la categoría.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el nivel de prioridad de la incidencia.
     * @return Nivel de urgencia.
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

    /**
     * Obtiene el estado de tramitación del ticket.
     * @return Estado actual.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el estado de tramitación del ticket.
     * @param estado Texto con el estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el ID del técnico que debe reparar la máquina.
     * @return ID del técnico o 0 si no hay ninguno.
     */
    public int getIdTecnicoAsignado() {
        return idTecnicoAsignado;
    }

    /**
     * Asigna un técnico para la reparación.
     * @param idTecnicoAsignado ID del técnico.
     */
    public void setIdTecnicoAsignado(int idTecnicoAsignado) {
        this.idTecnicoAsignado = idTecnicoAsignado;
    }

    /**
     * Obtiene la fecha de resolución del problema.
     * @return Date con la fecha de cierre o null si sigue abierta.
     */
    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    /**
     * Establece la fecha de resolución del problema.
     * @param fechaResolucion Fecha a establecer.
     */
    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    /**
     * Obtiene el texto con la solución aplicada por el técnico.
     * @return El informe de reparación.
     */
    public String getSolucionAplicada() {
        return solucionAplicada;
    }

    /**
     * Establece el texto con la solución aplicada.
     * @param solucionAplicada Informe de reparación.
     */
    public void setSolucionAplicada(String solucionAplicada) {
        this.solucionAplicada = solucionAplicada;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Representación en formato cadena de texto de la incidencia.
     * Útil para mostrar resúmenes por consola.
     * @return Cadena formateada con los datos principales.
     */
    @Override
    public String toString() {
        String sn = (maquina != null) ? maquina.getNumeroSerie() : "S/N Desconocido";
        return "[" + estado.toUpperCase() + " | " + prioridad + "] Máquina " + sn + " -> " + tipo + ": " + descripcion;
    }
}