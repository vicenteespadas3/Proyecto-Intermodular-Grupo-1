/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG1;

/**
 * Clase de entidad (Modelo/POJO) que representa un documento técnico o imagen.
 * Sirve para mapear los registros de la tabla 'archivos_adjuntos' y manejarlos 
 * de forma orientada a objetos en Java.
 * @author Vicente
 */
public class ArchivoAdjunto {
    
    // --- Atributos ---
    // Variables privadas para encapsular la información del archivo.
    private int idArchivo;
    private String nombre;
    private String ruta;
    private String tipo;
    
    // Este atributo actúa como la "Clave Foránea" (Foreign Key) en el mundo de los objetos,
    // permitiendo saber a qué máquina exacta pertenece este documento.
    private int idMaquina;
    
    /**
     * Constructor para instanciar un archivo recuperado de la base de datos o
     * para preparar uno nuevo antes de enviarlo al DAO.
     * * @param idArchivo Identificador único del archivo.
     * @param nombre Nombre visible del documento (ej. "Manual_Uso").
     * @param ruta Ubicación física o URL donde está guardado el archivo.
     * @param tipo Extensión o categoría del archivo (ej. "PDF", "JPG").
     * @param idMaquina ID de la máquina a la que está vinculado.
     */
    public ArchivoAdjunto(int idArchivo, String nombre, String ruta, String tipo, int idMaquina) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.idMaquina = idMaquina;
    }

    
    // --- Getters ---
    // Métodos de acceso para leer la información del archivo desde otras clases.
    
    public int getIdArchivo() {
        return idArchivo;
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public String getRuta() { 
        return ruta; 
    }
    
    public String getTipo() { 
        return tipo;
    }
    
    public int getIdMaquina() { 
        return idMaquina;
    }
    
    // ¡OJO! No incluimos Setters (setNombre, setRuta...). 
    // Un archivo adjunto, una vez subido y registrado, se considera "Inmutable" en este contexto.
    // Si un archivo cambia, físicamente es un archivo distinto, por lo que se borraría el viejo 
    // y se insertaría uno nuevo en lugar de modificar sus rutas en memoria.
    
    /**
     * Sobrescribe toString() para mostrar el archivo de forma amigable en la Interfaz Gráfica.
     * Si ponemos una lista de ArchivoAdjunto en un JList, esto es lo que leerá el usuario.
     * * @return Cadena con formato: "NombreDelArchivo (Tipo)"
     */
    @Override
    public String toString() {
        return nombre + " (" + tipo + ")";
    }
}