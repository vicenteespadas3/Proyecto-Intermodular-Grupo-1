/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * <h2>Clase Proveedor</h2>
 * Representa la entidad de un proveedor o suministrador del taller.
 * Esta clase mapea la estructura de la base de datos y es fundamental 
 * para el módulo de compras y aprovisionamiento.
 * * @see DAO.ProveedorDAO
 * @author Vicente
 * @version 0.0.1
 */
public class Proveedor {
    
    // --- Atributos ---
    // Se declaran como 'private' para cumplir con el principio de Encapsulamiento,
    // protegiendo los datos para que solo puedan modificarse mediante sus métodos Setters.
    private int idProveedor;
    private String cif;
    private String nombre;
    private String telefono;
    private String emailContacto;

    /**
     * Constructor completo.
     * Se utiliza principalmente desde el ProveedorDAO para instanciar objetos 
     * con los datos que ya existen y se han recuperado de la base de datos.
     * * @param idProveedor Identificador único (Clave Primaria).
     * @param cif Código de Identificación Fiscal.
     * @param nombre Nombre de la empresa proveedora.
     * @param telefono Teléfono de contacto.
     * @param emailContacto Correo electrónico del proveedor.
     */
    public Proveedor(int idProveedor, String cif, String nombre, String telefono, String emailContacto) {
        this.idProveedor = idProveedor;
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
    }

    /**
     * Constructor sobrecargado sin el ID.
     * Se utiliza desde la Interfaz Gráfica cuando un usuario va a dar de alta
     * un proveedor nuevo. Como el ID es Auto-Incremental en la BD, no lo conocemos aún.
     * 
     * @param cif Código de Identificación Fiscal.
     * @param nombre Nombre de la empresa proveedora.
     * @param telefono Teléfono de contacto.
     * @param emailContacto Correo electrónico del proveedor.
     */
    public Proveedor(String cif, String nombre, String telefono, String emailContacto) {
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
    }

    // --- Getters y Setters ---
    // Métodos de acceso público para interactuar con los atributos privados.
    
    /**
     * Obtiene el ID del proveedor.
     * @return ID del proveedor.
     */
    public int getIdProveedor() {
        return idProveedor;
    }
    
    /**
     * Obtiene el CIF del proveedor.
     * @return CIF del proveedor.
     */
    public String getCif() {
        return cif; 
    }
    
    /**
     * Obtiene el nombre del proveedor.
     * @return Nombre del proveedor.
     */
    public String getNombre() { 
        return nombre; 
    }
    
    /**
     * Obtiene el teléfono del proveedor.
     * @return Teléfono del proveedor.
     */
    public String getTelefono() {
        return telefono; 
    }
    
    /**
     * Obtiene el email de contacto del proveedor.
     * @return Email de contacto.
     */
    public String getEmailContacto() { 
        return emailContacto; 
    }

    // Nota: Solo creamos setters para los campos que tiene sentido modificar 
    // en la vida real. Un CIF o un ID no deberían cambiar, pero el teléfono sí.
    /**
     * Establece el teléfono del proveedor.
     * @param telefono Nuevo teléfono.
     */
    public void setTelefono(String telefono) { 
        this.telefono = telefono;
    }
    
    /**
     * Establece el email de contacto del proveedor.
     * @param emailContacto Nuevo email.
     */
    public void setEmailContacto(String emailContacto) { 
        this.emailContacto = emailContacto;
    }
    
    /**
     * Sobrescribimos el método toString() heredado de la clase Object.
     * Es de gran utilidad para que, si metemos el objeto en un ComboBox o Lista visual (JavaFX/Swing),
     * el usuario lea texto comprensible y no la dirección de memoria del objeto.
     * * @return Una cadena formateada con el nombre y el CIF.
     */
    @Override
    public String toString() {
        return nombre + " (" + cif + ")";
    }
}