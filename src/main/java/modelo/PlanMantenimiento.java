package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlanMantenimiento {
    
    // --- 1. ATRIBUTOS BÁSICOS ---
    private int idPlan;
    private Integer horasIntervalo; 
    private Integer duracionEstimada;
    private boolean activo;
    private LocalDateTime fechaCreacion;

    // --- 2. Claves Foraneas ---
    private Maquina maquina;
    private GamaMantenimiento gama;
    private Definicion frecuencia;
    private Definicion dificultad;
    
    //--- Lista de las tareas ---
    private List<TareaMantenimiento> tareas; 

    // --- 3. CONSTRUCTORES ---
    public PlanMantenimiento() {
        // Inicializar la lista vacía en el constructor por defecto
        // para evitar errores de NullPointerException al añadir tareas después.
        this.tareas = new ArrayList<>();
    }

    public PlanMantenimiento(Maquina maquina, GamaMantenimiento gama, Definicion frecuencia, 
                             Integer horasIntervalo, Definicion dificultad, Integer duracionEstimada, boolean activo) {
        this.maquina = maquina;
        this.gama = gama;
        this.frecuencia = frecuencia;
        this.horasIntervalo = horasIntervalo;
        this.dificultad = dificultad;
        this.duracionEstimada = duracionEstimada;
        this.activo = activo;
        this.tareas = new ArrayList<>();
    }

    // --- 4. GETTERS Y SETTERS ---
    //Borramos setID ya que lo crea la BBDD automatica
    public int getIdPlan() { 
        return idPlan; 
    }
    
    public Integer getHorasIntervalo() { 
        return horasIntervalo; 
    }
    public void setHorasIntervalo(Integer horasIntervalo) { 
        this.horasIntervalo = horasIntervalo; 
    }

    public Integer getDuracionEstimada() { 
        return duracionEstimada; 
    }
    public void setDuracionEstimada(Integer duracionEstimada) { 
        this.duracionEstimada = duracionEstimada; 
    }

    public boolean isActivo() { 
        return activo; 
    }
    public void setActivo(boolean activo) { 
        this.activo = activo; 
    }

    public LocalDateTime getFechaCreacion() { 
        return fechaCreacion; 
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { 
        this.fechaCreacion = fechaCreacion; 
    }

    // Getters y Setters de los Objetos
    public Maquina getMaquina() { 
        return maquina; 
    }
    public void setMaquina(Maquina maquina) { 
        this.maquina = maquina; 
    }

    public GamaMantenimiento getGama() { 
        return gama; 
    }
    public void setGama(GamaMantenimiento gama) { 
        this.gama = gama; 
    }

    public Definicion getFrecuencia() { 
        return frecuencia; 
    }
    public void setFrecuencia(Definicion frecuencia) { 
        this.frecuencia = frecuencia; 
    }

    public Definicion getDificultad() { 
        return dificultad; 
    }
    public void setDificultad(Definicion dificultad) { 
        this.dificultad = dificultad; 
    }

    public List<TareaMantenimiento> getTareas() { 
        return tareas; 
    }
    public void setTareas(List<TareaMantenimiento> tareas) { 
        this.tareas = tareas; 
    }

    // --- 5. MÉTODOS DE AYUDA (Para gestionar la lista de tareas fácilmente) ---
    public void addTarea(TareaMantenimiento tarea) {
        this.tareas.add(tarea);
    }

    public void removeTarea(TareaMantenimiento tarea) {
        this.tareas.remove(tarea);
    }

    // --- 6. TOSTRING ---
    @Override
    public String toString() {
        // Al imprimir, usamos los métodos de los otros objetos para mostrar sus nombres
        String nombreMaquina = (maquina != null) ? maquina.getNombre() : "Sin máquina";
        String nombreGama = (gama != null) ? gama.getNombre() : "Sin gama";
        
        return "Plan [" + idPlan + "] - Máquina: " + nombreMaquina + 
               " | Gama: " + nombreGama + 
               " | Tareas asignadas: " + tareas.size();
    }
}