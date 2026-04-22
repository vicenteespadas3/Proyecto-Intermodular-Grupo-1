package Modelo;

import java.time.LocalTime;

/**
 * @author Guadalupe M.G.M
 */

/**
 * Clase que representa el horario de trabajo de un usuario en el CMMS.
 * Permite validaciones básicas de horarios.
 */
public class HorarioTrabajo {

    private int idHorario;
    private int idUsuario;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    /**
     * Constructor por defecto.
     */
    public HorarioTrabajo() {}

    /**
     * Constructor con todos los parámetros.
     * 
     * @param idHorario Identificador único del horario.
     * @param idUsuario Identificador del usuario asociado.
     * @param diaSemana Día de la semana.
     * @param horaInicio Hora de inicio del turno.
     * @param horaFin Hora de fin del turno.
     */
    public HorarioTrabajo(int idHorario, int idUsuario, String diaSemana,
                            LocalTime horaInicio, LocalTime horaFin) {
        this.idHorario = idHorario;
        this.idUsuario = idUsuario;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // =========================
    // LÓGICA DEL HORARIO
    // =========================

    /**
     * Comprueba si el horario es válido.
     * La hora de inicio debe ser anterior a la de fin.
     *
     * @return true si el horario es correcto
     */
    public boolean esHorarioValido() {
        return horaInicio != null
            && horaFin != null
            && horaInicio.isBefore(horaFin);
    }

    /**
     * Calcula la duración del turno en horas.
     *
     * @return número de horas del turno
     */
    public long duracionHoras() {
        if (!esHorarioValido()) {
            return 0;
        }
        return java.time.Duration.between(horaInicio, horaFin).toHours();
    }

    /**
     * Comprueba si el turno es largo (más de 8 horas).
     *
     * @return true si supera las 8 horas
     */
    public boolean esTurnoLargo() {
        return duracionHoras() > 8;
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    /**
     * Obtiene el ID del horario.
     * @return ID del horario.
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * Establece el ID del horario.
     * @param idHorario ID del horario.
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * Obtiene el ID del usuario asociado.
     * @return ID del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario asociado.
     * @param idUsuario ID del usuario.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el día de la semana.
     * @return Día de la semana.
     */
    public String getDiaSemana() {
        return diaSemana;
    }

    /**
     * Establece el día de la semana.
     * @param diaSemana Día de la semana.
     */
    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Obtiene la hora de inicio.
     * @return Hora de inicio.
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * Establece la hora de inicio.
     * @param horaInicio Hora de inicio.
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtiene la hora de fin.
     * @return Hora de fin.
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * Establece la hora de fin.
     * @param horaFin Hora de fin.
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return diaSemana + ": " + horaInicio + " - " + horaFin;
    }
}