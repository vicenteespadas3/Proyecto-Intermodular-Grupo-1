/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG1;

/**
 * Representa un documento técnico o imagen asociado a una máquina.
 * @author Vicente
 */
public class ArchivoAdjunto {
    
    private int idArchivo;
    private String nombre;
    private String ruta;
    private String tipo;
    private int idMaquina;
    

    public ArchivoAdjunto(int idArchivo, String nombre, String ruta, String tipo, int idMaquina) {
        this.idArchivo = idArchivo;
        this.nombre = nombre;
        this.ruta = ruta;
        this.tipo = tipo;
        this.idMaquina = idMaquina;
    }

    
    // Getters
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
    

    @Override
    public String toString() {
        return nombre + " (" + tipo + ")";
    }
}