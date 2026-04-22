package Modelo;

/**
 * @author Guadalupe M.G.M
 */

/**
 * Clase que representa un usuario del sistema CMMS.
 * Incluye información básica y métodos de validación simples.
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String email;
    private String rol;

    /**
     * Constructor por defecto.
     */
    public Usuario() {}

    /**
     * Constructor con todos los parámetros.
     * 
     * @param idUsuario Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     * @param rol Rol del usuario en el sistema.
     */
    public Usuario(int idUsuario, String nombre, String email, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    // =========================
    // VALIDACIONES BÁSICAS
    // =========================

    /**
     * Verifica si el usuario tiene datos mínimos válidos.
     *
     * @return true si nombre y email no están vacíos
     */
    public boolean esValido() {
        return nombre != null && !nombre.trim().isEmpty()
            && email != null && email.contains("@");
    }

    /**
     * Comprueba si el usuario tiene rol de administrador.
     *
     * @return true si el rol es "admin"
     */
    public boolean esAdmin() {
        return rol != null && rol.equalsIgnoreCase("admin");
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    /**
     * Obtiene el ID del usuario.
     * @return ID del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario.
     * @param idUsuario ID del usuario.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del usuario.
     * @return Email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email solo si contiene formato válido básico.
     * @param email Correo electrónico.
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    /**
     * Obtiene el rol del usuario.
     * @return Rol del usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     * @param rol Rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}
