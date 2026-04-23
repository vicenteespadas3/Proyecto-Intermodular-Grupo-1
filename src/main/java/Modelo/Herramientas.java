/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * <h2>Clase Herramientas (Stub / Versión de Supervivencia)</h2>
 * * <b>¿Por qué se ha creado en este momento?</b><br>
 * La clase 'TareasHerramientas' del compañero Miguel Ángel requiere asociar 
 * un número de herramientas a una tarea. Sin este archivo, el proyecto no 
 * se podía ejecutar. Es una implementación "dummy" (de prueba) que en el 
 * futuro deberá mapearse con la tabla real de herramientas de la base de datos.
 * * @author Equipo 1
 */
public class Herramientas {

    // --- Atributos ---
    private int idHerramienta;
    private String nombre;

    // --- Constructores ---
    /**
     * Constructor por defecto vacío.
     */
    public Herramientas() {
    }

    /**
     * Constructor con parámetros mínimos.
     * @param idHerramienta Identificador de la herramienta en el taller.
     * @param nombre Nombre de la herramienta (ej. "Taladro", "Llave inglesa").
     */
    public Herramientas(int idHerramienta, String nombre) {
        this.idHerramienta = idHerramienta;
        this.nombre = nombre;
    }

    // --- Getters y Setters ---
    public int getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}