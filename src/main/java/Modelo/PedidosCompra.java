/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Objects;

/**
 * <h2>Clase PedidosCompra</h2>
 * Representa los pedidos de compra del sistema
 * Esta clase se relaciona con la tabla pedidos_compra de la base de datos
 * Tiene los siguientes atributos:
 * <ul>
 *  <li>id_pedido</li>
 *  <li>fecha_pedido</li>
 *  <li>proveedor</li>
 *  <li>estado</li>
 *  <li>fecha_entrega_esperada</li>
 *  <li>fecha_entrega_real</li>
 *  <li>observaciones</li>
 * </ul>
 * @see PedidosCompraDAO
 * @author Miguel Ángel Sánchez Martínez
 * @version 0.0.1
 */
public class PedidosCompra {
    //Atributos
    /**
     * Es la ID de esta clase y no puede ser nulo
     */
    private int id_pedido = 0;
    /**
     * Es la fecha del pedido y no puede ser nulo
     */
    private DateTime fecha_pedido;
    /**
     * Es un objeto simple de la clase ProveedoresMaquinas y no puede ser nulo
     */
    private ProveedoresMaquinas proveedor;
    /**
     * Es un objeto simple de la clase Definiciones y no puede ser nulo
     */
    private Definiciones estado;
    /**
     * Es la fecha esperada en la que entregan el pedido
     */
    private LocalDate fecha_entrega_esperada;
    /**
     * Es la fecha de entrega real del pedido
     */
    private LocalDate fecha_entrega_real;
    /**
     * Aquí se añaden las observaciones del pedido o al pedido
     */
    private String observaciones = "";
    
    //Constructores
    /**
     * Constructor por defecto
     * Crea una instancia de la clase PedidosCompra
     * @param fecha_pedido
     * @param proveedor
     * @param estado
     * @param fecha_entrega_esperada
     * @param fecha_entrega_real 
     */
    public PedidosCompra(DateTime fecha_pedido, ProveedoresMaquinas proveedor, Definiciones estado, LocalDate fecha_entrega_esperada, LocalDate fecha_entrega_real) {
        this.fecha_pedido = fecha_pedido;
        this.proveedor = proveedor;
        this.estado = estado;
        this.fecha_entrega_esperada = fecha_entrega_esperada;
        this.fecha_entrega_real = fecha_entrega_real;
    }
    
    //Métodos Públicos
    
    //Getters
    public int getId_pedido() {
        return id_pedido;
    }

    public DateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public ProveedoresMaquinas getProveedor() {
        return proveedor;
    }

    public Definiciones getEstado() {
        return estado;
    }

    public LocalDate getFecha_entrega_esperada() {
        return fecha_entrega_esperada;
    }

    public LocalDate getFecha_entrega_real() {
        return fecha_entrega_real;
    }

    public String getObservaciones() {
        return observaciones;
    }
    
    //Setters
    public void setProveedor(ProveedoresMaquinas proveedor) {
        this.proveedor = proveedor;
    }

    public void setEstado(Definiciones estado) {
        this.estado = estado;
    }

    public void setFecha_entrega_esperada(LocalDate fecha_entrega_esperada) {
        this.fecha_entrega_esperada = fecha_entrega_esperada;
    }

    public void setFecha_entrega_real(LocalDate fecha_entrega_real) {
        this.fecha_entrega_real = fecha_entrega_real;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    //toString
    /**
     * Devuelve una representación en cadena del pedido
     * Incluye la ID, la fecha, proveedor, estado, fecha entrega esperada y real e observaciones
     * @return String con todos los atributos del objeto/clase
     */
    @Override
    public String toString() {
        return "PedidosCompra{" + "id_pedido=" + id_pedido + ", fecha_pedido=" + fecha_pedido + ", proveedor=" + proveedor + ", estado=" + estado + ", fecha_entrega_esperada=" + fecha_entrega_esperada + ", fecha_entrega_real=" + fecha_entrega_real + ", observaciones=" + observaciones + '}';
    }
    //equals y hashCode
    /**
     * Genera un código hash sobre la clase PedidosCompra
     * Es indispensable mantener la consistencia con {@link #equals(Object)}
     * @return código hash del objeto
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_pedido;
        hash = 79 * hash + Objects.hashCode(this.fecha_pedido);
        hash = 79 * hash + Objects.hashCode(this.proveedor);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + Objects.hashCode(this.fecha_entrega_esperada);
        hash = 79 * hash + Objects.hashCode(this.fecha_entrega_real);
        hash = 79 * hash + Objects.hashCode(this.observaciones);
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
        final PedidosCompra other = (PedidosCompra) obj;
        if (this.id_pedido != other.id_pedido) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.fecha_pedido, other.fecha_pedido)) {
            return false;
        }
        if (!Objects.equals(this.proveedor, other.proveedor)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.fecha_entrega_esperada, other.fecha_entrega_esperada)) {
            return false;
        }
        return Objects.equals(this.fecha_entrega_real, other.fecha_entrega_real);
    }
    
    //Métodos Privados
    
}
