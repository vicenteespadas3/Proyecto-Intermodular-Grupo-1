package Modelo;

/**
 * Clase Definiciones
 * Esta clase sirve para gestionar todos los estados y clasificaciones del sistema.
 * Se hizo para que módulos como el de 'Compras' puedan usar etiquetas como 
 * "Pendiente", "Enviado" o "Cancelado" de forma organizada.
 * * @see DAO.DefinicionesDAO
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class Definiciones {

    // Atributos de la clase
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
     * Lo uso para crear el objeto básico y luego ir llenando los datos.
     */
    public Definiciones() {
    }

    /**
     * Constructor completo.
     * Lo uso para crear una definición con todas sus propiedades de un solo golpe.
     * * @param idDefinicion El ID único de la base de datos.
     * @param nombre       Nombre de la etiqueta.
     * @param categoria    Categoría a la que pertenece (ej. "PEDIDOS").
     * @param codigo       Código corto de identificación.
     * @param descripcion  Texto explicativo de la definición.
     * @param ordenVisual  Posición en la que debe aparecer en las listas.
     * @param activo       Si está disponible para usarse o no.
     * @param estado       El valor del estado actual.
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
    // LÓGICA DE LA DEFINICIÓN
    // ==========================================

    /**
     * Para saber si esta definición se puede usar o está pausada.
     */
    public boolean estaDisponible() {
        return activo;
    }

    /**
     * Un método rápido para cambiar el estado sin líos.
     */
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    public int getIdDefinicion() {
        return idDefinicion;
    }

    public void setIdDefinicion(int idDefinicion) {
        this.idDefinicion = idDefinicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getOrdenVisual() {
        return ordenVisual;
    }

    public void setOrdenVisual(int ordenVisual) {
        this.ordenVisual = ordenVisual;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método para mostrar la información principal en consola o en listas.
     */
    @Override
    public String toString() {
        return nombre + " (" + estado + ")";
    }
}