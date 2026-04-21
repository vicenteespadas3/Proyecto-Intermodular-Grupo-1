/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG1;

/**
 * Clase que representa un Proveedor del taller.
 * @author Vicente
 */
public class Proveedor {
    
    private int idProveedor;
    private String cif;
    private String nombre;
    private String telefono;
    private String emailContacto;

    /**
     * Constructor para recuperar datos de la BD.
     */
    public Proveedor(int idProveedor, String cif, String nombre, String telefono, String emailContacto) {
        this.idProveedor = idProveedor;
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
    }

    /**
     * Constructor para crear nuevos proveedores.
     */
    public Proveedor(String cif, String nombre, String telefono, String emailContacto) {
        this.cif = cif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
    }

    // Getters y Setters
    public int getIdProveedor() {
        return idProveedor;
    }
    
    public String getCif() {
        return cif; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public String getTelefono() {
        return telefono; 
    }
    
    public String getEmailContacto() { 
        return emailContacto; 
    }

    public void setTelefono(String telefono) { 
        this.telefono = telefono;
    }
    
    public void setEmailContacto(String emailContacto) { 
        this.emailContacto = emailContacto;
    }
    

    @Override
    public String toString() {
        return nombre + " (" + cif + ")";
    }
}