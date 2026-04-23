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
     * Constructor con los datos principales.
     * Sirve para crear una definición con su nombre y estado rápidamente.
     * * @param idDefinicion El ID que viene de la base de datos.
     * @param nombre       El nombre de la etiqueta (ej. "Urgente").
     * @param estado       El estado actual de esta definición.
     */
    public Definiciones(int idDefinicion, String nombre, String estado) {
        this.idDefinicion = idDefinicion;
        this.nombre = nombre;
        this.estado = estado;
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