/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Clase Maquina</h2>
 * Representa una máquina física del taller.
 * Actúa como el núcleo central del módulo de Gestión de Activos, relacionando
 * los datos físicos de la máquina con sus documentos adjuntos y sus incidencias.
 * * @see DAO.MaquinaDAO
 * @author Vicente
 * @version 0.0.1
 */
public class Maquina {
    
    // --- Atributos de la Ficha Técnica ---
    // Encapsulados (private) para garantizar la integridad de los datos.
    private int idMaquina;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private int idEstado;
    private Date fechaAdquisicion;
    
    // --- Relaciones ---
    // Representación en Programación Orientada a Objetos de una relación 1:N (Uno a Muchos).
    // Una máquina puede tener múltiples archivos (manuales, garantías, fotos).
    private List<ArchivoAdjunto> listaArchivos;

    /**
     * Constructor principal para instanciar máquinas recuperadas de la base de datos.
     * * @param idMaquina Identificador único (Clave Primaria).
     * @param numeroSerie Número de serie físico del fabricante.
     * @param marca Marca de la máquina.
     * @param modelo Modelo específico.
     * @param idEstado ID que representa si está operativa, averiada, etc.
     * @param fechaAdquisicion Fecha en la que el taller compró la máquina.
     */
    public Maquina(int idMaquina, String numeroSerie, String marca, String modelo, int idEstado, Date fechaAdquisicion) {
        this.idMaquina = idMaquina;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.idEstado = idEstado;
        this.fechaAdquisicion = fechaAdquisicion;
        
        // ¡MUY IMPORTANTE! Inicializamos la lista vacía en lugar de dejarla a null.
        // Esto previene los temidos NullPointerException si intentamos hacer un .size() 
        // o un .add() antes de cargarle archivos reales desde la base de datos.
        this.listaArchivos = new ArrayList<>();
    }

    
    // --- Getters ---
    // Permiten la lectura de los datos de la máquina desde otras partes de la aplicación.
    
    /**
     * Obtiene el identificador único de la máquina.
     * @return ID de la máquina.
     */
    public int getIdMaquina() { 
        return idMaquina;
    }
    
    /**
     * Obtiene el número de serie de la máquina.
     * @return Número de serie.
     */
    public String getNumeroSerie() { 
        return numeroSerie;
    }
    
    /**
     * Obtiene la marca de la máquina.
     * @return Marca de la máquina.
     */
    public String getMarca() { 
        return marca; 
    }
    
    /**
     * Obtiene el modelo de la máquina.
     * @return Modelo de la máquina.
     */
    public String getModelo() { 
        return modelo; 
    }
    
    /**
     * Obtiene el identificador del estado de la máquina.
     * @return ID del estado.
     */
    public int getIdEstado() { 
        return idEstado; 
    }
    
    /**
     * Obtiene la fecha de adquisición de la máquina.
     * @return Fecha de adquisición.
     */
    public Date getFechaAdquisicion() { 
        return fechaAdquisicion;
    }
        
    /**
     * Obtiene la lista de archivos adjuntos vinculados a la máquina.
     * @return Lista de archivos adjuntos.
     */
    public List<ArchivoAdjunto> getListaArchivos() { 
        return listaArchivos; 
    }
    
    // --- Setters ---
    // Fíjate que de forma deliberada NO hay setters para marca, modelo o numSerie.
    // Una máquina física no cambia de marca ni de número de serie mágicamente, 
    // por lo que restringimos su modificación para asegurar la consistencia del software.
    
    /**
     * Permite cargar o actualizar la lista de documentos vinculados a esta máquina.
     * @param listaArchivos Lista completa de objetos ArchivoAdjunto.
     */
    public void setListaArchivos(List<ArchivoAdjunto> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }
    

    // --- Métodos Sobrescritos ---
    
    /**
     * Sobrescribe toString() para proporcionar una representación visual amigable.
     * Resulta vital si esta máquina se añade directamente a un JComboBox o JList en la interfaz gráfica.
     * @return String con formato: "Marca Modelo [NumSerie]"
     */
    @Override
    public String toString() {
        return marca + " " + modelo + " [" + numeroSerie + "]";
    }
}