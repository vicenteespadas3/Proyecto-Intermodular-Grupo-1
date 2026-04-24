package Modelo;

import java.time.LocalTime;
import java.time.Duration;

/**
 * <h2>Clase HorarioTrabajo</h2>
 * Modela la jornada laboral de un trabajador en el taller.
 * Permite gestionar la asignación de turnos, verificar la disponibilidad para mantenimientos
 * y realizar cálculos de duración de jornada.
 * * @see DAO.HorarioTrabajoDAO
 * @author Guadalupe M.G.M
 * @version 1.0.0
 * @since 2026
 */
public class HorarioTrabajo {

    private int idHorario;
    private int idUsuario;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    /**
     * Constructor por defecto.
     * Útil para la instanciación dinámica y el llenado progresivo de atributos 
     * mediante métodos setter.
     */
    public HorarioTrabajo() {}

    /**
     * Constructor completo para inicializar todos los atributos de la jornada.
     * * @param idHorario Identificador único del registro de horario.
     * @param idUsuario Identificador del usuario (trabajador) asociado.
     * @param diaSemana Día de la semana (ej. "Lunes", "Martes").
     * @param horaInicio Hora de entrada al turno.
     * @param horaFin Hora de salida del turno.
     */
    public HorarioTrabajo(int idHorario, int idUsuario, String diaSemana,
                            LocalTime horaInicio, LocalTime horaFin) {
        this.idHorario = idHorario;
        this.idUsuario = idUsuario;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // ==========================================
    // LÓGICA DE NEGOCIO
    // ==========================================

    /**
     * Valida la coherencia temporal del horario.
     * Comprueba que las horas no sean nulas y que la hora de inicio sea estrictamente 
     * anterior a la hora de finalización.
     * * @return {@code true} si el rango horario es lógico; {@code false} en caso contrario.
     */
    public boolean esHorarioValido() {
        return horaInicio != null
            && horaFin != null
            && horaInicio.isBefore(horaFin);
    }

    /**
     * Calcula la cantidad total de horas transcurridas en el turno.
     * Utiliza la clase {@link Duration} para computar la diferencia entre inicio y fin.
     * * @return El número de horas completas del turno. Devuelve {@code 0} si el horario no es válido.
     */
    public long duracionHoras() {
        if (!esHorarioValido()) {
            return 0;
        }
        return Duration.between(horaInicio, horaFin).toHours();
    }

    /**
     * Determina si la jornada laboral excede las 8 horas estándar.
     * * @return {@code true} si la duración es superior a 8 horas; {@code false} de lo contrario.
     */
    public boolean esTurnoLargo() {
        return duracionHoras() > 8;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    /** @return ID único del horario. */
    public int getIdHorario() { return idHorario; }
    /** @param idHorario ID único a asignar. */
    public void setIdHorario(int idHorario) { this.idHorario = idHorario; }

    /** @return ID del usuario propietario del turno. */
    public int getIdUsuario() { return idUsuario; }
    /** @param idUsuario ID del usuario a vincular. */
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    /** @return Día de la semana asignado. */
    public String getDiaSemana() { return diaSemana; }
    /** @param diaSemana Nombre del día a establecer. */
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    /** @return Objeto {@link LocalTime} con la hora de inicio. */
    public LocalTime getHoraInicio() { return horaInicio; }
    /** @param horaInicio Hora de inicio a establecer. */
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    /** @return Objeto {@link LocalTime} con la hora de fin. */
    public LocalTime getHoraFin() { return horaFin; }
    /** @param horaFin Hora de finalización a establecer. */
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    /**
     * Devuelve una cadena con el resumen del horario.
     * Ejemplo: "Lunes: 08:00 - 16:00"
     * * @return Representación textual del horario.
     */
    @Override
    public String toString() {
        return diaSemana + ": " + horaInicio + " - " + horaFin;
    }
}