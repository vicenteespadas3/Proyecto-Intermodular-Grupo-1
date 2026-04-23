/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 * <h2>Clase ArchivoAdjunto</h2>
 * Representa un documento, manual de instrucciones, certificado de garantía 
 * o imagen fotográfica vinculada a una máquina específica del taller.
 * * @author Vicente
 * @version 0.0.1
 */
public class ArchivoAdjunto {

    private int idArchivo;
    private Maquina maquina;
    private String nombreArchivo;
    private String extension;
    private String rutaLocal;
    private Date fechaSubida;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Obligatorio para la instanciación de objetos POJO y el mapeo de datos.
     */
    public ArchivoAdjunto() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con ID.
     * Utilizado exclusivamente por el DAO cuando recupera un documento existente desde la base de datos.
     * * @param idArchivo Identificador único y autonumérico del archivo.
     * @param maquina Objeto Maquina al que pertenece este documento.
     * @param nombreArchivo Nombre del archivo sin extensión (ej. "Manual_Excavadora").
     * @param extension Formato del archivo (ej. "pdf", "jpg", "docx").
     * @param rutaLocal Ruta absoluta o relativa donde se almacena físicamente el archivo en el servidor.
     * @param fechaSubida Fecha y hora en la que el documento fue registrado en el sistema.
     */
    public ArchivoAdjunto(int idArchivo, Maquina maquina, String nombreArchivo, String extension, String rutaLocal, Date fechaSubida) {
        this.idArchivo = idArchivo;
        this.maquina = maquina;
        this.nombreArchivo = nombreArchivo;
        this.extension = extension;
        this.rutaLocal = rutaLocal;
        this.fechaSubida = fechaSubida;
    }

    /**
     * Constructor de inserción sin ID.
     * Utilizado al subir un nuevo documento desde la aplicación antes de persistirlo en MySQL.
     * * @param maquina Objeto Maquina al que pertenece este documento.
     * @param nombreArchivo Nombre del archivo sin extensión.
     * @param extension Formato del archivo.
     * @param rutaLocal Ruta física del archivo en el servidor.
     * @param fechaSubida Fecha de registro del documento.
     */
    public ArchivoAdjunto(Maquina maquina, String nombreArchivo, String extension, String rutaLocal, Date fechaSubida) {
        this.maquina = maquina;
        this.nombreArchivo = nombreArchivo;
        this.extension = extension;
        this.rutaLocal = rutaLocal;
        this.fechaSubida = fechaSubida;
    }

    // --- 3. Getters y Setters ---

    /**
     * Obtiene el identificador único del archivo.
     * @return El ID numérico del documento.
     */
    public int getIdArchivo() {
        return idArchivo;
    }

    /**
     * Establece el identificador único del archivo.
     * @param idArchivo El ID a asignar.
     */
    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    /**
     * Obtiene la máquina a la que está vinculado este documento.
     * @return El objeto Maquina asociado.
     */
    public Maquina getMaquina() {
        return maquina;
    }

    /**
     * Vincula el documento a una máquina específica.
     * @param maquina El objeto Maquina a vincular.
     */
    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    /**
     * Obtiene el nombre del archivo.
     * @return Nombre del documento sin la extensión.
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Define el nombre del documento.
     * @param nombreArchivo El nombre a asignar.
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene el formato o extensión del archivo.
     * @return La extensión (ej. "pdf").
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Define la extensión del archivo.
     * @param extension El formato a asignar.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Obtiene la ruta donde está guardado el archivo físicamente.
     * @return La ruta del directorio local o de red.
     */
    public String getRutaLocal() {
        return rutaLocal;
    }

    /**
     * Define la ruta de almacenamiento del archivo.
     * @param rutaLocal La ruta física a guardar.
     */
    public void setRutaLocal(String rutaLocal) {
        this.rutaLocal = rutaLocal;
    }

    /**
     * Obtiene la fecha en la que se subió o adjuntó el documento.
     * @return Objeto Date con la fecha de subida.
     */
    public Date getFechaSubida() {
        return fechaSubida;
    }

    /**
     * Establece la fecha de subida del documento.
     * @param fechaSubida La fecha a asignar.
     */
    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Devuelve una cadena de texto representativa del documento.
     * Ideal para mostrar el archivo y la máquina a la que pertenece en un listado o consola.
     * @return Cadena formateada con el nombre, extensión y número de serie de la máquina vinculada.
     */
    @Override
    public String toString() {
        String sn = (maquina != null) ? maquina.getNumeroSerie() : "S/N Desconocido";
        return "[DOC] " + nombreArchivo + "." + extension + " -> Vinculado a Máquina: " + sn;
    }
}