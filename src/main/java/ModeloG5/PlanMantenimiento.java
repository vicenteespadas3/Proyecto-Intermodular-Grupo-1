package ModeloG5;

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

/* --- CÓDIGO FUTURO  ---
    private Maquina maquina;
    private Definicion frecuencia;
    private Definicion dificultad;
    */
    
    private GamaMantenimiento gama;
    private int idMaquina;
    private int idFrecuencia;
    private int idDificultad;
    
    //--- Lista de las tareas ---
    private List<TareaMantenimiento> tareas; 

    // --- 3. CONSTRUCTORES ---
    public PlanMantenimiento() {
        // Inicializar la lista vacía en el constructor por defecto
        // para evitar errores de NullPointerException al añadir tareas después.
        this.tareas = new ArrayList<>();
    }

public PlanMantenimiento(Integer horasIntervalo, Integer duracionEstimada, boolean activo, 
                             GamaMantenimiento gama, int idMaquina, int idFrecuencia, int idDificultad) {
        this.horasIntervalo = horasIntervalo;
        this.duracionEstimada = duracionEstimada;
        this.activo = activo;
        this.gama = gama;
        this.idMaquina = idMaquina;
        this.idFrecuencia = idFrecuencia;
        this.idDificultad = idDificultad;
        this.tareas = new ArrayList<>();
    }

    // --- 4. GETTERS Y SETTERS ---
    //Borramos setID ya que lo crea la BBDD automatica
    public int getIdPlan() { 
        return idPlan; 
    }
    public void setIdPlan(int idPlan) { 
        this.idPlan = idPlan; 
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
    public int getIdMaquina() { 
        return idMaquina; 
    }
    public void setIdMaquina(int idMaquina) { 
        this.idMaquina = idMaquina; 
    }

    public int getIdFrecuencia() { 
        return idFrecuencia; 
    }
    public void setIdFrecuencia(int idFrecuencia) { 
        this.idFrecuencia = idFrecuencia; 
    }

    public int getIdDificultad() { 
        return idDificultad; 
    }
    public void setIdDificultad(int idDificultad) { 
        this.idDificultad = idDificultad; 
    }
    
    public GamaMantenimiento getGama() { 
        return gama; 
    }
    public void setGama(GamaMantenimiento gama) { 
        this.gama = gama; 
    }
    

    public List<TareaMantenimiento> getTareas() { 
        return tareas; 
    }
    public void setTareas(List<TareaMantenimiento> tareas) { 
        this.tareas = tareas; 
    }
    
    
    // Métodos para tu lista de tareas
    public void addTarea(TareaMantenimiento tarea) { 
        this.tareas.add(tarea); 
    }
    public void removeTarea(TareaMantenimiento tarea) { 
        this.tareas.remove(tarea); 
    }




    // --- 6. TOSTRING ---
    @Override
        public String toString() {
            String nombreGama = (gama != null) ? "ID " + gama.getIdGama() : "Sin gama";
            return "Plan [" + idPlan + "] - ID Máquina: " + idMaquina + 
                   " | Gama: " + nombreGama + 
                   " | Tareas: " + tareas.size();
        }
}