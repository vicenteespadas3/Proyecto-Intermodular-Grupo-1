package Modelo;

/**
 * Clase que representa un usuario dentro del sistema CMMS.
 * Almacena la información de identidad, contacto y permisos del personal.
 * * @author Guadalupe M.G.M
 * @version 1.0
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
     * Crea una instancia de Usuario con atributos sin inicializar.
     */
    public Usuario() {}

    /**
     * Constructor con parámetros para inicializar un usuario completo.
     * * @param idUsuario Identificador único del usuario.
     * @param nombre    Nombre completo del usuario.
     * @param email     Dirección de correo electrónico.
     * @param rol       Cargo o nivel de acceso (ej. "admin", "tecnico").
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
     * Verifica si el usuario tiene datos mínimos válidos para ser procesado.
     * * @return {@code true} si el nombre no está vacío y el email contiene un formato básico.
     */
    public boolean esValido() {
        return nombre != null && !nombre.trim().isEmpty()
            && email != null && email.contains("@");
    }

    /**
     * Comprueba si el usuario posee privilegios de administrador.
     * * @return {@code true} si el rol es "admin", ignorando mayúsculas y minúsculas.
     */
    public boolean esAdmin() {
        return rol != null && rol.equalsIgnoreCase("admin");
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    /** @return El identificador único del usuario. */
    public int getIdUsuario() {
        return idUsuario;
    }

    /** @param idUsuario El nuevo identificador a asignar. */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /** @return El nombre del usuario. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre El nombre completo a asignar. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return La dirección de correo electrónico. */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email solo si contiene un formato válido básico.
     * * @param email El correo electrónico a validar y asignar.
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    /** @return El rol o cargo actual del usuario. */
    public String getRol() {
        return rol;
    }

    /** @param rol El nuevo rol a asignar (ej. "admin", "operador"). */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Retorna una representación en texto del usuario.
     * * @return String con el formato "Nombre (Rol)".
     */
    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}