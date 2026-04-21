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

    public HorarioTrabajo() {}

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

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
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

    @Override
    public String toString() {
        return diaSemana + ": " + horaInicio + " - " + horaFin;
    }
}