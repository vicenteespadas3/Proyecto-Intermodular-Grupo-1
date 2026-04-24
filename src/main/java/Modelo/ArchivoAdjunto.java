package Modelo;

import java.util.Date;

/**
 * <h2>Clase ArchivoAdjunto</h2>
 * Representa un documento, manual o imagen en el sistema.
 * Utiliza un diseño polimórfico (tipo_referencia e id_referencia) para poder 
 * vincular el archivo a cualquier otra entidad (Máquinas, Incidencias, Proveedores...).
 * Mapea exactamente con la tabla 'archivos_adjuntos' de la BD.
 * * @author Equipo 1
 * @version 0.0.1
 */
public class ArchivoAdjunto {

    /** Identificador único autonumérico del archivo (PK). */
    private int idArchivo;
    
    /** Define a qué entidad pertenece el archivo (ej. "maquina", "incidencia"). */
    private String tipoReferencia;
    
    /** ID de la entidad a la que pertenece (ej. el ID de la máquina concreta). */
    private int idReferencia;
    
    /** Nombre completo del documento, incluyendo extensión (varchar 300). */
    private String nombreArchivo;
    
    /** Ruta física o enlace donde está guardado el archivo (varchar 500). */
    private String rutaArchivo;
    
    /** Fecha y hora exacta en la que se subió al sistema (datetime). */
    private Date fechaSubida;
    
    /** ID del usuario/trabajador que subió el archivo (FK a tabla usuarios). */
    private int idUsuarioSubida;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Obligatorio para la instanciación de objetos POJO y el mapeo de datos de la BD.
     */
    public ArchivoAdjunto() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con ID.
     * Utilizado exclusivamente por el DAO cuando recupera un documento de MySQL.
     * * @param idArchivo Identificador único.
     * @param tipoReferencia Tabla o tipo de entidad vinculada.
     * @param idReferencia ID de la entidad vinculada.
     * @param nombreArchivo Nombre y formato del archivo.
     * @param rutaArchivo Ubicación de almacenamiento.
     * @param fechaSubida Fecha y hora de subida.
     * @param idUsuarioSubida ID del usuario creador.
     */
    public ArchivoAdjunto(int idArchivo, String tipoReferencia, int idReferencia, 
                          String nombreArchivo, String rutaArchivo, Date fechaSubida, int idUsuarioSubida) {
        this.idArchivo = idArchivo;
        this.tipoReferencia = tipoReferencia;
        this.idReferencia = idReferencia;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.fechaSubida = fechaSubida;
        this.idUsuarioSubida = idUsuarioSubida;
    }

    /**
     * Constructor de inserción sin ID.
     * Utilizado al subir un nuevo documento desde la aplicación antes de persistirlo.
     * * @param tipoReferencia Tabla o tipo de entidad vinculada.
     * @param idReferencia ID de la entidad vinculada.
     * @param nombreArchivo Nombre y formato del archivo.
     * @param rutaArchivo Ubicación de almacenamiento.
     * @param fechaSubida Fecha y hora de subida.
     * @param idUsuarioSubida ID del usuario creador.
     */
    public ArchivoAdjunto(String tipoReferencia, int idReferencia, String nombreArchivo, 
                          String rutaArchivo, Date fechaSubida, int idUsuarioSubida) {
        this.tipoReferencia = tipoReferencia;
        this.idReferencia = idReferencia;
        this.nombreArchivo = nombreArchivo;
        this.rutaArchivo = rutaArchivo;
        this.fechaSubida = fechaSubida;
        this.idUsuarioSubida = idUsuarioSubida;
    }

    // --- 3. Getters y Setters ---

    /**
     * Obtiene el identificador único del archivo.
     * @return El ID numérico.
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
     * Obtiene el tipo de entidad al que hace referencia (ej. "maquina").
     * @return String con el tipo de referencia.
     */
    public String getTipoReferencia() {
        return tipoReferencia;
    }

    /**
     * Define el tipo de entidad al que se vincula el archivo.
     * @param tipoReferencia El texto identificador de la entidad.
     */
    public void setTipoReferencia(String tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    /**
     * Obtiene el ID de la entidad vinculada.
     * @return ID numérico de la referencia.
     */
    public int getIdReferencia() {
        return idReferencia;
    }

    /**
     * Define el ID de la entidad a vincular.
     * @param idReferencia El ID a asignar.
     */
    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * Obtiene el nombre completo del archivo.
     * @return Nombre del documento.
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
     * Obtiene la ruta física o lógica de almacenamiento.
     * @return String con la ruta.
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * Define la ruta de almacenamiento.
     * @param rutaArchivo Ruta a asignar.
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Obtiene la fecha y hora de subida del archivo.
     * @return Objeto Date con el Timestamp.
     */
    public Date getFechaSubida() {
        return fechaSubida;
    }

    /**
     * Establece la fecha de subida.
     * @param fechaSubida Objeto Date a asignar.
     */
    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    /**
     * Obtiene el ID del usuario que subió el archivo.
     * @return ID numérico del usuario.
     */
    public int getIdUsuarioSubida() {
        return idUsuarioSubida;
    }

    /**
     * Asigna el ID del usuario responsable de la subida.
     * @param idUsuarioSubida ID a asignar.
     */
    public void setIdUsuarioSubida(int idUsuarioSubida) {
        this.idUsuarioSubida = idUsuarioSubida;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Devuelve una cadena de texto representativa del documento.
     * @return Cadena formateada con el nombre y su vínculo.
     */
    @Override
    public String toString() {
        return "[DOC] " + nombreArchivo + " -> Vinculado a " + tipoReferencia + " (ID: " + idReferencia + ")";
    }
}