/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 * <h2>Clase Maquina</h2>
 * Representa un activo físico, herramienta pesada o maquinaria del taller.
 * Esta clase mapea de forma exacta la estructura de la tabla 'maquinas' de la base de datos.
 * * @author Vicente
 * @version 0.0.1
 */
public class Maquina {

    /** Identificador numérico único de la máquina (PK - int). */
    private int idMaquina;
    
    /** Nombre descriptivo de la máquina (varchar 200). */
    private String nombre;
    
    /** Categoría o familia a la que pertenece (varchar 100). */
    private String tipo;
    
    /** Número de serie oficial proporcionado por el fabricante (varchar 100). */
    private String numeroSerie;
    
    /** Lugar físico del taller donde se encuentra instalada (varchar 200). */
    private String ubicacion;
    
    /** Empresa fabricante de la maquinaria (varchar 150). */
    private String fabricante;
    
    /** Fecha en la que se realizó la compra (date). */
    private Date fechaCompra;
    
    /** Situación actual del activo (varchar 30). */
    private String estado;
    
    /** Cadena de texto o enlace asociado al código QR de la máquina (varchar 100). */
    private String qrCode;
    
    /** Identificador del proveedor que suministró la máquina (FK - int). */
    private int proveedorId;
    
    /** Indica si la máquina está dada de alta en el sistema (tinyint -> boolean). */
    private boolean activa;
    
    /** Fecha y hora exacta en la que se registró la máquina (datetime). */
    private Date fechaRegistro;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Requisito estricto para la instanciación de objetos POJO, frameworks y serialización.
     */
    public Maquina() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con identificador (ID).
     * Utilizado exclusivamente por el DAO cuando lee un registro ya existente en la base de datos.
     * * @param idMaquina Identificador único (Auto-incremental).
     * @param nombre Nombre descriptivo.
     * @param tipo Categoría de la máquina.
     * @param numeroSerie Número de serie de fábrica.
     * @param ubicacion Lugar de instalación.
     * @param fabricante Marca o empresa fabricante.
     * @param fechaCompra Fecha de adquisición.
     * @param estado Situación técnica actual.
     * @param qrCode Referencia al código QR.
     * @param proveedorId ID del proveedor asociado.
     * @param activa Estado lógico de alta/baja.
     * @param fechaRegistro Timestamp de creación en el sistema.
     */
    public Maquina(int idMaquina, String nombre, String tipo, String numeroSerie, String ubicacion, 
                   String fabricante, Date fechaCompra, String estado, String qrCode, 
                   int proveedorId, boolean activa, Date fechaRegistro) {
        this.idMaquina = idMaquina;
        this.nombre = nombre;
        this.tipo = tipo;
        this.numeroSerie = numeroSerie;
        this.ubicacion = ubicacion;
        this.fabricante = fabricante;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
        this.qrCode = qrCode;
        this.proveedorId = proveedorId;
        this.activa = activa;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Constructor de inserción (Sin ID ni Fecha de Registro).
     * Se usa al crear una nueva máquina desde la interfaz antes de enviarla a MySQL.
     * El ID y la Fecha de Registro los generará automáticamente la base de datos.
     * * @param nombre Nombre descriptivo.
     * @param tipo Categoría de la máquina.
     * @param numeroSerie Número de serie de fábrica.
     * @param ubicacion Lugar de instalación.
     * @param fabricante Marca o empresa fabricante.
     * @param fechaCompra Fecha de adquisición.
     * @param estado Situación técnica actual.
     * @param qrCode Referencia al código QR.
     * @param proveedorId ID del proveedor asociado.
     * @param activa Estado lógico de alta/baja.
     */
    public Maquina(String nombre, String tipo, String numeroSerie, String ubicacion, 
                   String fabricante, Date fechaCompra, String estado, String qrCode, 
                   int proveedorId, boolean activa) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.numeroSerie = numeroSerie;
        this.ubicacion = ubicacion;
        this.fabricante = fabricante;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
        this.qrCode = qrCode;
        this.proveedorId = proveedorId;
        this.activa = activa;
    }

    // --- 3. Getters y Setters ---

    /**
     * Obtiene el ID único de la base de datos.
     * @return El identificador de la máquina.
     */
    public int getIdMaquina() {
        return idMaquina;
    }

    /**
     * Establece el ID único de la máquina.
     * @param idMaquina El identificador a asignar.
     */
    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    /**
     * Obtiene el nombre de la máquina.
     * @return Nombre descriptivo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre de la máquina.
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la categoría o tipo de la máquina.
     * @return El tipo de maquinaria.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define la categoría o tipo de la máquina.
     * @param tipo El tipo a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el número de serie asignado por el fabricante.
     * @return Cadena de texto con el número de serie.
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Define el número de serie de la máquina.
     * @param numeroSerie El texto del número de serie.
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * Obtiene la ubicación física dentro del taller.
     * @return Texto con la ubicación.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Define la ubicación física de la máquina.
     * @param ubicacion La ubicación a asignar.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el nombre de la empresa fabricante.
     * @return El fabricante.
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Define el nombre de la empresa fabricante.
     * @param fabricante El fabricante a asignar.
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * Obtiene la fecha oficial de adquisición de la maquinaria.
     * @return Objeto Date con la fecha.
     */
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Establece la fecha de compra del equipo.
     * @param fechaCompra Fecha a asignar.
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * Obtiene el estado actual de la máquina.
     * @return Texto descriptivo del estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el estado actual de la máquina.
     * @param estado El estado a asignar.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la cadena de texto asociada al QR de la máquina.
     * @return El contenido del QR.
     */
    public String getQrCode() {
        return qrCode;
    }

    /**
     * Establece el código QR de la máquina.
     * @param qrCode El contenido a asignar.
     */
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /**
     * Obtiene el identificador del proveedor asociado.
     * @return El ID del proveedor.
     */
    public int getProveedorId() {
        return proveedorId;
    }

    /**
     * Establece el identificador del proveedor asociado.
     * @param proveedorId El ID a asignar.
     */
    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    /**
     * Comprueba si la máquina está dada de alta en el sistema.
     * @return true si está activa, false en caso contrario.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Define si la máquina está dada de alta o baja.
     * @param activa Estado lógico a asignar.
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Obtiene la fecha y hora en la que se registró el activo.
     * @return Fecha de registro en el sistema.
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha y hora de registro.
     * @param fechaRegistro La fecha a asignar.
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Devuelve una cadena de texto formateada con los datos vitales de la máquina.
     * @return String con el ID, Nombre, Fabricante y Número de Serie.
     */
    @Override
    public String toString() {
        String estadoActiva = activa ? "[ACTIVA]" : "[BAJA]";
        return estadoActiva + " ID: " + idMaquina + " | " + nombre + " (" + fabricante + ") | SN: " + numeroSerie;
    }
}