/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Objects;

/**
 * <h2>Clase TareasHerramientas</h2>
 * Representa la cantidad de herramientas que se usan en una tarea de mantenimiento en el sistema
 * Esta clase se relaciona con la tabla tareas_herramientas de la base de datos
 * Esta clase se compone de los siguientes atributos
 * <ul>
 *  <li></li>
 * </ul>
 * @see DAO.TareasHerramientasDAO
 * @author Miguel Ángel Sánchez Martínez
 * @version 0.0.1
 */
public class TareasHerramientas {
    //Atributos
    /**
     * Es la ID de esta clase y no puede ser nulo
     */
    private int id_tareas_herramientas;
    /**
     * Es un objeto simple de la clase herramientas y no puede ser nulo
     */
    private Herramientas herramienta;
    /**
     * Es la cantidad de herramientas usadas por tarea, no puede ser nulo y por defecto es uno en la base de datos
     */
    private int cantidad;
    
    //Constructor
    /**
     * Constructor por defecto
     * Crea una instancia de la clase TareasHerramientas
     * @param id_tareas_herramientas
     * @param herramienta
     * @param cantidad 
     */
    public TareasHerramientas(int id_tareas_herramientas, Herramientas herramienta, int cantidad) {
        this.id_tareas_herramientas = id_tareas_herramientas;
        this.herramienta = herramienta;
        this.cantidad = cantidad;
    }
    
    //Getters
    /**
     * Obtiene el ID de la relación tarea-herramienta.
     * @return ID de la relación.
     */
    public int getId_tareas_herramientas() {
        return id_tareas_herramientas;
    }

    /**
     * Obtiene la herramienta utilizada.
     * @return Objeto Herramientas.
     */
    public Herramientas getHerramienta() {
        return herramienta;
    }

    /**
     * Obtiene la cantidad de herramientas utilizadas.
     * @return Cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }
    //Setters
    /**
     * Establece la herramienta utilizada.
     * @param herramienta Objeto Herramientas.
     */
    public void setHerramienta(Herramientas herramienta) {
        this.herramienta = herramienta;
    }

    /**
     * Establece la cantidad de herramientas utilizadas.
     * @param cantidad Cantidad.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    //Métodos Públicos
    
    //toString
    /**
     * Devuelve una representación en cadena de las herramientas usadas en las tareas
     * Incluye la ID, herramienta y cantidad
     * @return String con todos los atributos del objeto/clase
     */
    @Override
    public String toString() {
        return "TareasHerramientas{" + "id_tareas_herramientas=" + id_tareas_herramientas + ", herramienta=" + herramienta + ", cantidad=" + cantidad + '}';
    }
    
    //equals y hashCode
    /**
     * Genera un código hash sobre la clase TareasHerramientas
     * Es indispensable mantener la consistencia con {@link #equals(Object)}
     * @return código hash del objeto
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id_tareas_herramientas;
        hash = 67 * hash + Objects.hashCode(this.herramienta);
        hash = 67 * hash + this.cantidad;
        return hash;
    }
    /**
     * Compara este objeto con otro objeto para verificar igualdad
     * Dos usuarios se consideran iguales si y solo si comparten el mismo ID único
     * @param obj El objeto a comparar
     * @return true si los IDs coinciden; false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TareasHerramientas other = (TareasHerramientas) obj;
        if (this.id_tareas_herramientas != other.id_tareas_herramientas) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        return Objects.equals(this.herramienta, other.herramienta);
    }
    
    //Métodos Privados
    
}
