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

    public Usuario() {}

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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Establece el email solo si contiene formato válido básico.
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}
