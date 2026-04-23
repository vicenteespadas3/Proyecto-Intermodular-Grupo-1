/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 * <h2>Clase Maquina</h2>
 * Representa un activo físico, herramienta pesada o maquinaria del taller.
 * Es la entidad principal del módulo de Activos e Infraestructura.
 * * @author Vicente
 * @version 0.0.1
 */
public class Maquina {

    private int idMaquina;
    private String numeroSerie;
    private String marca;
    private String modelo;
    private int idEstado;
    private Date fechaAdquisicion;

    // --- 1. Constructor Vacío ---

    /**
     * Constructor vacío por defecto.
     * Requisito estricto para la instanciación de objetos POJO y uso en frameworks.
     */
    public Maquina() {
    }

    // --- 2. Constructores Parametrizados ---

    /**
     * Constructor completo con identificador (ID).
     * Utilizado exclusivamente por el DAO cuando lee un registro ya existente en la base de datos.
     * * @param idMaquina Identificador numérico único de la máquina (Auto-incremental).
     * @param numeroSerie Número de serie de fábrica de la máquina.
     * @param marca Fabricante o marca de la máquina.
     * @param modelo Modelo comercial de la máquina.
     * @param idEstado ID que referencia la situación actual (ej. 1=Activa, 2=Mantenimiento).
     * @param fechaAdquisicion Fecha en la que la máquina fue comprada o dada de alta.
     */
    public Maquina(int idMaquina, String numeroSerie, String marca, String modelo, int idEstado, Date fechaAdquisicion) {
        this.idMaquina = idMaquina;
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.idEstado = idEstado;
        this.fechaAdquisicion = fechaAdquisicion;
    }

    /**
     * Constructor de inserción (Sin ID).
     * Se usa al crear una nueva máquina desde la interfaz antes de enviarla a MySQL.
     * * @param numeroSerie Número de serie de fábrica de la máquina.
     * @param marca Fabricante o marca de la máquina.
     * @param modelo Modelo comercial de la máquina.
     * @param idEstado ID que referencia la situación actual (ej. 1=Activa, 2=Mantenimiento).
     * @param fechaAdquisicion Fecha en la que la máquina fue comprada o dada de alta.
     */
    public Maquina(String numeroSerie, String marca, String modelo, int idEstado, Date fechaAdquisicion) {
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.modelo = modelo;
        this.idEstado = idEstado;
        this.fechaAdquisicion = fechaAdquisicion;
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
     * Obtiene la marca o fabricante del activo.
     * @return La marca comercial.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define la marca fabricante de la máquina.
     * @param marca Nombre del fabricante.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo comercial de la máquina.
     * @return El modelo especificado.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define el modelo comercial de la máquina.
     * @param modelo Texto del modelo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el ID de la tabla de estados que define la situación de la máquina.
     * @return Entero que representa el estado.
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     * Asigna un nuevo ID de estado a la máquina.
     * @param idEstado El identificador del estado (ej. Operativa, Averiada).
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * Obtiene la fecha oficial de adquisición de la maquinaria.
     * @return Objeto Date con la fecha.
     */
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * Establece la fecha de compra o alta del equipo.
     * @param fechaAdquisicion Fecha a asignar.
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    // --- 4. Métodos Sobrescritos ---

    /**
     * Devuelve una cadena de texto formateada con los datos vitales de la máquina.
     * Muy útil para rellenar listados en menús de consola o interfaces de usuario.
     * @return String con el ID, Número de Serie, Marca y Modelo.
     */
    @Override
    public String toString() {
        return "ID: " + idMaquina + " | SN: " + numeroSerie + " | " + marca + " " + modelo;
    }
}