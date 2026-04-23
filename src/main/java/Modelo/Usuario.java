package Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario - Sistema CMMS
 * Esta clase sirve para guardar toda la info de los trabajadores.
 * La idea es tener aquí sus datos personales, su seguridad y 
 * saber qué incidencias tienen asignadas.
 * * @see DAO.UsuarioDAO
 * @author Guadalupe M.G.M (Equipo 1)
 * @version 1.0.0
 * @since 2026
 */
public class Usuario {

    // Atributos básicos que pide el diagrama
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password; // Aquí guardamos el hash para que sea seguro
    private String rol;
    
    // Estos son para llevar el control de cuándo entran al sistema
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimoAcceso;
    
    // Lista para guardar los códigos de las incidencias que le tocan
    private List<String> listIncidencias;

    /**
     * Constructor vacío.
     * Lo uso más que nada cuando necesito crear un usuario desde cero 
     * o para que el DAO lo llene después.
     */
    public Usuario() {
        this.listIncidencias = new ArrayList<>();
        this.fechaCreacion = LocalDateTime.now(); // Ponemos la fecha de hoy por defecto
    }

    /**
     * Constructor lleno.
     * Este lo uso cuando ya tengo los datos (por ejemplo, al traerlos de la BD).
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
    // MÉTODOS PARA QUE TODO FUNCIONE (LÓGICA)
    // ==========================================

    /**
     * Este método ayuda al UPDATE del DAO.
     * Solo actualiza la fecha de último acceso a "ahora mismo".
     */
    public void registrarAcceso() {
        this.ultimoAcceso = LocalDateTime.now();
    }

    /**
     * Validación rápida antes de hacer un CREATE.
     * Revisa que el nombre no esté vacío y que el email tenga un @.
     */
    public boolean esValido() {
        return nombre != null && !nombre.trim().isEmpty()
            && email != null && email.contains("@");
    }

    /**
     * Para ir metiendo incidencias a la lista sin borrar las anteriores.
     */
    public void agregarIncidencia(String codigo) {
        if (codigo != null) {
            this.listIncidencias.add(codigo);
        }
    }

    // ==========================================
    // GETTERS Y SETTERS (Para entrar y salir de los datos)
    // ==========================================

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getUltimoAcceso() { return ultimoAcceso; }
    public void setUltimoAcceso(LocalDateTime ultimoAcceso) { this.ultimoAcceso = ultimoAcceso; }

    public List<String> getListIncidencias() { return listIncidencias; }
    public void setListIncidencias(List<String> listIncidencias) { this.listIncidencias = listIncidencias; }

    /**
     * Para imprimir los datos del usuario de forma bonita.
     */
    @Override
    public String toString() {
        return "ID: " + idUsuario + " | " + nombre + " " + apellidos + " [" + rol + "]";
    }
}