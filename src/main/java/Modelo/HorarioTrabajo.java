package Modelo;

import java.time.LocalTime;
import java.time.Duration;

/**
 * Clase HorarioTrabajo
 * Esta clase es para saber en qué horario trabaja cada usuario del taller.
 * Sirve para organizar los turnos y ver quién está disponible para 
 * hacer los mantenimientos según el día y la hora.
 * * @see DAO.HorarioTrabajoDAO
 * @author Guadalupe M.G.M
 */
public class HorarioTrabajo {

    // Datos que necesito para los turnos
    private int idHorario;
    private int idUsuario;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    /**
     * Constructor vacío.
     * Lo uso cuando el DAO va a llenar los datos poco a poco.
     */
    public HorarioTrabajo() {}

    /**
     * Constructor con todo.
     * Lo uso para crear un turno completo de una vez con todos sus datos.
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
    // LÓGICA PARA CONTROLAR EL HORARIO
    // ==========================================

    /**
     * Para revisar que el horario tenga sentido.
     * Checa que no falten las horas y que la de inicio sea antes que la de fin.
     */
    public boolean esHorarioValido() {
        return horaInicio != null
            && horaFin != null
            && horaInicio.isBefore(horaFin);
    }

    /**
     * Para saber cuántas horas dura el turno.
     * Si el horario está mal, me devuelve 0.
     */
    public long duracionHoras() {
        if (!esHorarioValido()) {
            return 0;
        }
        // Uso Duration para calcular la diferencia entre las horas
        return Duration.between(horaInicio, horaFin).toHours();
    }

    /**
     * Para saber si es un turno de más de 8 horas.
     */
    public boolean esTurnoLargo() {
        return duracionHoras() > 8;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public int getIdHorario() { return idHorario; }
    public void setIdHorario(int idHorario) { this.idHorario = idHorario; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    /**
     * Para mostrar el horario en un formato que se entienda fácil.
     */
    @Override
    public String toString() {
        return diaSemana + ": " + horaInicio + " - " + horaFin;
    }
}