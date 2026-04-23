/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * <h2>Clase PiezasRepuesto (Stub / Versión de Supervivencia)</h2>
 * * <b>¿Por qué se ha creado en este momento?</b><br>
 * El archivo 'PedidosDetalle' de Miguel Ángel exige un objeto de tipo pieza 
 * para saber qué se está comprando exactamente. Al no existir la clase, 
 * NetBeans marcaba el proyecto en rojo. Esta clase actúa como un "maniquí" 
 * temporal para que la relación orientada a objetos no se rompa y el código compile.
 * * @author Equipo 1
 */
public class PiezasRepuesto {

    // --- Atributos ---
    private int idPieza;
    private String nombre;

    // --- Constructores ---
    /**
     * Constructor por defecto vacío.
     */
    public PiezasRepuesto() {
    }

    /**
     * Constructor con parámetros mínimos.
     * @param idPieza ID único de la pieza de inventario.
     * @param nombre Nombre o descripción de la pieza.
     */
    public PiezasRepuesto(int idPieza, String nombre) {
        this.idPieza = idPieza;
        this.nombre = nombre;
    }

    // --- Getters y Setters ---
    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}