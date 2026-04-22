/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <h2>Clase PedidosCompra</h2>
 * <b>¿Por que se ha modificado/arreglado?</b><br>
 * Esta clase representa la cabecera de un pedido de compra al proveedor.
 * Se han corregido los tipos de datos de fecha (de DateTime a LocalDateTime) y se ha 
 * vinculado correctamente con la clase 'Proveedor' de Vicente y la clase 'Definiciones' 
 * para el estado del pedido. Sin estos cambios, el proyecto no compilaba por tipos 
 * de datos incompatibles.
 * * @author Miguel Angel / Corregido por Vicente (Tech Lead)
 * @version 1.0.1
 */
public class PedidosCompra {

    // --- Atributos ---
    
    /** Identificador unico del pedido (Auto-incremental en BD). */
    private int id_pedido;
    
    /** Fecha y hora en la que se realiza el pedido. */
    private LocalDateTime fecha_pedido;
    
    /** Objeto Proveedor al que se le realiza la compra (Modulo de Vicente). */
    private Proveedor proveedor;
    
    /** Objeto que define el estado actual (ej: Pendiente, Recibido, Cancelado). */
    private Definiciones estado;
    
    /** Fecha en la que el proveedor estima la entrega. */
    private LocalDateTime fecha_entrega_esperada;
    
    /** Fecha real en la que el pedido entro en el almacen del taller. */
    private LocalDateTime fecha_entrega_real;
    
    /** Notas adicionales sobre el pedido. */
    private String observaciones;

    // --- Constructores ---

    /**
     * Constructor vacio por defecto (Requisito POJO).
     */
    public PedidosCompra() {
    }

    /**
     * Constructor completo para instanciar pedidos desde el DAO.
     */
    public PedidosCompra(int id_pedido, LocalDateTime fecha_pedido, Proveedor proveedor, 
                         Definiciones estado, LocalDateTime fecha_entrega_esperada, 
                         LocalDateTime fecha_entrega_real, String observaciones) {
        this.id_pedido = id_pedido;
        this.fecha_pedido = fecha_pedido;
        this.proveedor = proveedor;
        this.estado = estado;
        this.fecha_entrega_esperada = fecha_entrega_esperada;
        this.fecha_entrega_real = fecha_entrega_real;
        this.observaciones = observaciones;
    }

    // --- Metodos Getter y Setter ---

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public LocalDateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Definiciones getEstado() {
        return estado;
    }

    public void setEstado(Definiciones estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_entrega_esperada() {
        return fecha_entrega_esperada;
    }

    public void setFecha_entrega_esperada(LocalDateTime fecha_entrega_esperada) {
        this.fecha_entrega_esperada = fecha_entrega_esperada;
    }

    public LocalDateTime getFecha_entrega_real() {
        return fecha_entrega_real;
    }

    public void setFecha_entrega_real(LocalDateTime fecha_entrega_real) {
        this.fecha_entrega_real = fecha_entrega_real;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // --- Metodos Sobrescritos ---

    @Override
    public String toString() {
        String nombreProv = (proveedor != null) ? proveedor.getNombre() : "Sin proveedor";
        String nombreEstado = (estado != null) ? estado.getNombre() : "Sin estado";
        
        return "Pedido #" + id_pedido + " [" + nombreProv + "] - Estado: " + nombreEstado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PedidosCompra other = (PedidosCompra) obj;
        return id_pedido == other.id_pedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pedido);
    }
}