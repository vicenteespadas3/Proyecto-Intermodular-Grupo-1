package Modelo;

/**
 * <h2>Clase Definiciones</h2>
 * Proporciona un sistema de gestión de catálogos y estados dinámicos.
 * Esta clase centraliza las clasificaciones (como estados de compra, tipos de prioridad o categorías)
 * para que puedan ser reutilizadas por distintos módulos del sistema sin necesidad de hardcoding.
 * * @see DAO.DefinicionesDAO
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class Definiciones {

    private int idDefinicion;
    private String nombre;
    private String categoria;
    private String codigo;
    private String descripcion;
    private int ordenVisual;
    private boolean activo;
    private String estado; 

    /**
     * Constructor por defecto.
     * Permite la creación de una instancia vacía para ser poblada posteriormente 
     * por el DAO o mediante métodos de acceso.
     */
    public Definiciones() {
    }

    /**
     * Constructor completo para inicializar una definición con todos sus parámetros.
     * * @param idDefinicion Identificador único en la base de datos.
     * @param nombre       Etiqueta descriptiva (ej. "Enviado").
     * @param categoria    Grupo al que pertenece (ej. "ORDENES_COMPRA").
     * @param codigo       Abreviatura técnica para lógica interna (ej. "ENV").
     * @param descripcion  Detalle extendido sobre el propósito de esta definición.
     * @param ordenVisual  Prioridad de aparición en componentes de interfaz (combos/listas).
     * @param activo       Indica si el registro es funcional en el sistema actual.
     * @param estado       Representación del valor de estado actual.
     */
    public Definiciones(int idDefinicion, String nombre, String categoria, String codigo, 
                        String descripcion, int ordenVisual, boolean activo, String estado) {
        this.idDefinicion = idDefinicion;
        this.nombre = nombre;
        this.categoria = categoria;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.ordenVisual = ordenVisual;
        this.activo = activo;
        this.estado = estado;
    }

    // ==========================================
    // LÓGICA DE NEGOCIO
    // ==========================================

    /**
     * Verifica la disponibilidad operativa de la definición.
     * * @return {@code true} si la definición está marcada como activa; {@code false} en caso contrario.
     */
    public boolean estaDisponible() {
        return activo;
    }

    /**
     * Actualiza de forma directa el estado asociado a esta definición.
     * * @param nuevoEstado Cadena de texto con el nuevo valor de estado.
     */
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    /** @return ID único de la definición. */
    public int getIdDefinicion() { return idDefinicion; }
    /** @param idDefinicion ID a establecer. */
    public void setIdDefinicion(int idDefinicion) { this.idDefinicion = idDefinicion; }

    /** @return Nombre de la etiqueta. */
    public String getNombre() { return nombre; }
    /** @param nombre Nombre a asignar. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return Categoría del catálogo. */
    public String getCategoria() { return categoria; }
    /** @param categoria Categoría a asignar. */
    public void setCategoria(String categoria) { this.categoria = categoria; }

    /** @return Código corto de identificación. */
    public String getCodigo() { return codigo; }
    /** @param codigo Código a asignar. */
    public void setCodigo(String codigo) { this.codigo = codigo; }

    /** @return Descripción detallada. */
    public String getDescripcion() { return descripcion; }
    /** @param descripcion Texto descriptivo a establecer. */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /** @return Posición en el orden de visualización. */
    public int getOrdenVisual() { return ordenVisual; }
    /** @param ordenVisual Índice de posición a establecer. */
    public void setOrdenVisual(int ordenVisual) { this.ordenVisual = ordenVisual; }

    /** @return {@code true} si el registro está activo. */
    public boolean isActivo() { return activo; }
    /** @param activo Estado de actividad a establecer. */
    public void setActivo(boolean activo) { this.activo = activo; }

    /** @return El estado actual de la definición. */
    public String getEstado() { return estado; }
    /** @param estado Estado a asignar. */
    public void setEstado(String estado) { this.estado = estado; }

    /**
     * Genera una representación legible del estado para el usuario final.
     * * @return Nombre seguido del estado entre paréntesis.
     */
    @Override
    public String toString() {
        return nombre + " (" + estado + ")";
    }
}