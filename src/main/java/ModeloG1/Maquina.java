/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloG1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la información de las máquinas del taller.
 * @author Vicente
 */
public class Maquina {
    
    private int idMaquina;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private int idEstado;
    private Date fechaAdquisicion;
    
    // Lista de archivos vinculados (Relación 1:N)
    private List<ArchivoAdjunto> listaArchivos;

    public Maquina(int idMaquina, String numeroSerie, String marca, String modelo, int idEstado, Date fechaAdquisicion) {
        this.idMaquina = idMaquina;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.idEstado = idEstado;
        this.fechaAdquisicion = fechaAdquisicion;
        this.listaArchivos = new ArrayList<>();
    }

    
    // Getters y Setters
    public int getIdMaquina() { 
        return idMaquina;
    }
    
    public String getNumeroSerie() { 
        return numeroSerie;
    }
    
    public String getMarca() { 
        return marca; 
    }
    
    public String getModelo() { 
        return modelo; 
    }
    
    public int getIdEstado() { 
        return idEstado; 
    }
    
    public Date getFechaAdquisicion() { 
        return fechaAdquisicion;
    }
        
    public List<ArchivoAdjunto> getListaArchivos() { 
        return listaArchivos; 
    }
    
    public void setListaArchivos(List<ArchivoAdjunto> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }
    

    @Override
    public String toString() {
        return marca + " " + modelo + " [" + numeroSerie + "]";
    }
}