package Modelo;

/**
 * <h2>Clase Proveedor</h2>
 * Representa la entidad de un proveedor, empresa o persona física que suministra
 * maquinaria, activos o piezas de repuesto al taller.
 * Esta clase mapea de forma exacta la estructura de la tabla 'proveedores_maquinas' de MySQL.
 * * @author Equipo 1
 * @version 0.0.1
 */
public class Proveedor {

    /** Identificador numérico único del proveedor (int, PK). */
    private int idProveedor;
    
    /** Nombre fiscal o comercial de la empresa proveedora (varchar 200). */
    private String nombre;
    
    /** Nombre de la persona física de referencia o comercial (varchar 150). */
    private String contacto;
    
    /** Número de teléfono de contacto (varchar 20). */
    private String telefono;
    
    /** Correo electrónico principal del proveedor (varchar 200). */
    private String email;
    
    /** Ubicación física, calle o sede de la empresa (varchar 300). */
    private String direccion;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Requisito fundamental para la creación de objetos POJO, serialización 
     * y uso genérico en estructuras de datos.
     */
    public Proveedor() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con identificador (ID).
     * Utilizado exclusivamente por el DAO cuando recupera un registro existente de la base de datos.
     * * @param idProveedor Identificador numérico único del proveedor (Auto-incremental en BD).
     * @param nombre Nombre fiscal o comercial de la empresa proveedora.
     * @param contacto Nombre de la persona física de referencia o comercial.
     * @param telefono Número de teléfono de contacto.
     * @param email Correo electrónico principal del proveedor.
     * @param direccion Ubicación física, calle o sede de la empresa.
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
     * Constructor de inserción (Sin ID).
     * Se usa al crear un nuevo proveedor desde la interfaz antes de guardarlo en MySQL.
     * La base de datos se encargará de generar su ID correspondiente.
     * * @param nombre Nombre fiscal o comercial de la empresa proveedora.
     * @param contacto Nombre de la persona física de referencia o comercial.
     * @param telefono Número de teléfono de contacto.
     * @param email Correo electrónico principal del proveedor.
     * @param direccion Ubicación física, calle o sede de la empresa.
     */
    public Proveedor(String nombre, String contacto, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    // --- 3. Getters y Setters ---

    /**
     * Obtiene el ID único asignado por la base de datos.
     * @return El identificador numérico del proveedor.
     */
    public int getIdProveedor() {
        return idProveedor;
    }

    /**
     * Establece el ID único del proveedor.
     * @param idProveedor El identificador a asignar.
     */
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    /**
     * Obtiene el nombre comercial de la empresa proveedora.
     * @return Cadena de texto con el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre de la empresa proveedora.
     * @param nombre El nombre comercial o fiscal.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de la persona de contacto.
     * @return Nombre del responsable o comercial.
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Define a la persona física de referencia.
     * @param contacto Nombre de la persona de contacto.
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Obtiene el número de teléfono del proveedor.
     * @return Texto con el número de teléfono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del proveedor.
     * @param telefono El teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electrónico de contacto.
     * @return Dirección de email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del proveedor.
     * @param email La dirección de email a asignar.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la dirección física o sede del proveedor.
     * @return Texto con la dirección completa.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Define la dirección física de la empresa.
     * @param direccion Calle, número, código postal, etc.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Proporciona una representación textual resumida del proveedor.
     * Facilita la visualización en listados de consola o desplegables en interfaces gráficas.
     * @return Una cadena que muestra el nombre de la empresa y la persona de contacto.
     */
    @Override
    public String toString() {
        return nombre + " (Contacto: " + contacto + ")";
    }
}