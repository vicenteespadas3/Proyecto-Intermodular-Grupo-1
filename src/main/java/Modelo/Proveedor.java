/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * <h2>Clase Proveedor</h2>
 * Representa la entidad de un proveedor o suministrador del taller.
 * Esta clase mapea de forma exacta la estructura de la tabla 'proveedores_maquinas' 
 * de la base de datos MySQL.
 * * @see DAO.ProveedorDAO
 * @author Vicente
 * @version 0.0.1
 */
public class Proveedor {

    // --- Atributos (Mapeados directamente de las columnas de la BD) ---
    private int idProveedor;
    private String nombre;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;

    // --- Constructores ---

    /**
     * Constructor 1: Vacío por defecto.
     * Requisito para el estándar POJO y para instanciaciones genéricas.
     */
    public Proveedor() {
    }

    /**
     * Constructor 2: Completo (Con ID).
     * Se utiliza principalmente en el DAO al recuperar registros existentes de la BD (SELECT).
     * * @param idProveedor Identificador único autoincremental.
     * @param nombre Nombre de la empresa o entidad.
     * @param contacto Nombre de la persona de referencia.
     * @param telefono Número de teléfono de contacto.
     * @param email Correo electrónico de contacto.
     * @param direccion Ubicación física del proveedor.
     */
    public Proveedor(int idProveedor, String nombre, String contacto, String telefono, String email, String direccion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    /**
     * Constructor 3: Para inserción (Sin ID).
     * Se utiliza al crear un proveedor nuevo desde la interfaz o lógica de negocio.
     * El ID no se incluye porque la base de datos lo genera automáticamente.
     * * @param nombre Nombre de la empresa o entidad.
     * @param contacto Nombre de la persona de referencia.
     * @param telefono Número de teléfono de contacto.
     * @param email Correo electrónico de contacto.
     * @param direccion Ubicación física del proveedor.
     */
    public Proveedor(String nombre, String contacto, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // --- Métodos Getter y Setter ---

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // --- Métodos Sobrescritos ---

    /**
     * Representación textual del proveedor, útil para mostrar en JLists o JComboBoxes.
     * @return El nombre del proveedor y su contacto.
     */
    @Override
    public String toString() {
        return nombre + " (" + contacto + ")";
    }
}