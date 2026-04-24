package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Clase Usuario - Sistema CMMS</h2>
 * Representa a los trabajadores dentro del sistema de gestión de mantenimiento.
 * Almacena información personal, credenciales de seguridad y el historial de 
 * incidencias asignadas.
 * * @see DAO.UsuarioDAO
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class Usuario {

    // Atributos privados
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password; 
    private String rol;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimoAcceso;
    private List<String> listIncidencias;

    /**
     * Constructor por defecto.
     * Inicializa la lista de incidencias y establece la fecha de creación 
     * al momento actual del sistema.
     */
    public Usuario() {
        this.listIncidencias = new ArrayList<>();
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Constructor con parámetros esenciales.
     * Utilizado principalmente para la reconstrucción de objetos desde la base de datos.
     * * @param idUsuario Identificador único del usuario.
     * @param nombre Nombre de pila del trabajador.
     * @param apellidos Apellidos del trabajador.
     * @param email Correo electrónico institucional.
     * @param rol Cargo o nivel de acceso (ej. Admin, Técnico).
     * @param fechaCreacion Fecha y hora original de registro.
     */
    public Usuario(int idUsuario, String nombre, String apellidos, String email, String rol, LocalDateTime fechaCreacion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
        this.listIncidencias = new ArrayList<>();
    }

    // ==========================================
    // MÉTODOS DE LÓGICA DE NEGOCIO
    // ==========================================

    /**
     * Actualiza el marcador de tiempo del último acceso al sistema.
     * Este método debe invocarse cada vez que el usuario inicia una sesión exitosa.
     */
    public void registrarAcceso() {
        this.ultimoAcceso = LocalDateTime.now();
    }

    /**
     * Realiza una validación básica de integridad de los datos del usuario.
     * Verifica que el nombre no esté vacío y que el formato del email sea mínimamente válido.
     * * @return {@code true} si el usuario cumple los requisitos mínimos; {@code false} en caso contrario.
     */
    public boolean esValido() {
        return nombre != null && !nombre.trim().isEmpty()
            && email != null && email.contains("@");
    }

    /**
     * Registra un nuevo código de incidencia en el historial del usuario.
     * * @param codigo El código alfanumérico de la incidencia a asignar.
     */
    public void agregarIncidencia(String codigo) {
        if (codigo != null) {
            this.listIncidencias.add(codigo);
        }
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================

    /** @return Identificador del usuario. */
    public int getIdUsuario() { return idUsuario; }
    /** @param idUsuario Identificador único a asignar. */
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    /** @return Nombre del usuario. */
    public String getNombre() { return nombre; }
    /** @param nombre Nombre a asignar. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return Apellidos del usuario. */
    public String getApellidos() { return apellidos; }
    /** @param apellidos Apellidos a asignar. */
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    /** @return Email institucional. */
    public String getEmail() { return email; }
    /** @param email Email a asignar. */
    public void setEmail(String email) { this.email = email; }

    /** @return Hash de la contraseña. */
    public String getPassword() { return password; }
    /** @param password Hash de la contraseña a almacenar. */
    public void setPassword(String password) { this.password = password; }

    /** @return Rol del usuario en el sistema. */
    public String getRol() { return rol; }
    /** @param rol Rol (ej. "ADMIN", "USER") a asignar. */
    public void setRol(String rol) { this.rol = rol; }

    /** @return Fecha de registro inicial. */
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    /** @param fechaCreacion Fecha de creación a establecer. */
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    /** @return Fecha y hora de la última actividad registrada. */
    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; }
    /** @param ultimoAcceso Fecha del último acceso a establecer. */
    public void setUltimoAcceso(LocalDateTime ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }

    /** @return Lista de códigos de incidencias asociadas. */
    public List<String> getListIncidencias() { return listIncidencias; }
    /** @param listIncidencias Colección de incidencias a asignar. */
    public void setListIncidencias(List<String> listIncidencias) { this.listIncidencias = listIncidencias; }

    /**
     * Genera una representación textual simplificada del usuario.
     * * @return Cadena formateada con ID, nombre completo y rol.
     */
    @Override
    public String toString() {
        return "ID: " + idUsuario + " | " + nombre + " " + apellidos + " [" + rol + "]";
    }
}