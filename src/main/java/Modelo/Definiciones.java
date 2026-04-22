/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * <h2>Clase Definiciones (Stub / Versión de Supervivencia)</h2>
 * * <b>¿Por qué se ha creado en este momento?</b><br>
 * El módulo de Compras (Miguel Ángel) hace referencia a esta clase en 
 * 'PedidosCompra' para gestionar el estado del pedido. Como esta parte 
 * aún no estaba desarrollada en el proyecto, bloqueaba la compilación 
 * de todo el equipo. Se ha creado esta estructura mínima para salvar 
 * el error y permitir que el programa funcione.
 * * @author Equipo 1 (Tech Lead Hotfix)
 */
public class Definiciones {

    // --- Atributos ---
    private int idDefinicion;
    private String nombre;

    // --- Constructores ---
    /**
     * Constructor por defecto vacío.
     */
    public Definiciones() {
    }

    /**
     * Constructor con parámetros mínimos para instanciar rápidamente.
     * @param idDefinicion ID de la definición.
     * @param nombre Nombre o valor (ej. "Enviado", "Pendiente").
     */
    public Definiciones(int idDefinicion, String nombre) {
        this.idDefinicion = idDefinicion;
        this.nombre = nombre;
    }

    // --- Getters y Setters ---
    public int getIdDefinicion() {
        return idDefinicion;
    }

    public void setIdDefinicion(int idDefinicion) {
        this.idDefinicion = idDefinicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}