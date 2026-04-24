/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Objects;

/**
 * <h2>Clase PedidosDetalle</h2>
 * Representa los detalles de los pedidos en el sistema
 * Esta clase se relaciona con la tabla pedidos_detalle de la base de datos
 * Esta clase se compone de los siguientes atributos:
 * <ul>
 * <li>id_detalle</li>
 * <li>pedido</li>
 * <li>pieza</li>
 * <li>cantidad_solicitada</li>
 * <li>cantidad_recibida</li>
 * <li>precio_unitario</li>
 * </ul>
 * <<<<<<< HEAD
 * 
 * @see PedidosDetalleDAO
 *      =======
 * @see DAO.PedidosDetalleDAO
 *      >>>>>>> 7a2d9ffd760b2b1bd68800cc929c95e4f50be022
 * @author Miguel Ángel Sánchez Martínez
 * @version 0.0.1
 */
public class PedidosDetalle {
    // Atributos
    /**
     * Es la ID de la clase y no puede ser nulo
     */
    private int id_detalle;
    /**
     * Es un objeto de la clase PedidosCompra y no puede ser nulo
     */
    private PedidosCompra pedido;
    /**
     * Es un objeto de la clase PiezasRepuesto y no puede ser nulo
     */
    private PiezasRepuesto pieza;
    /**
     * Es la cantidad solicitada y no puede ser nulo
     */
    private int cantidad_solicitada;
    /**
     * Es la cantidad recibida y no puede ser nulo
     */
    private int cantidad_recibida;
    /**
     * Es el precio unitario por pedido
     */
    private double precio_unitario;

    // Constructores
    /**
     * Constructor por defecto
     * Crea una instancia de la clase PedidosDetalle
     * 
     * @param pedido
     * @param pieza
     * @param cantidad_solicitada
     * @param cantidad_recibida
     * @param precio_unitario
     */
    public PedidosDetalle(// int id_detalle,
            PedidosCompra pedido, PiezasRepuesto pieza, int cantidad_solicitada, int cantidad_recibida,
            double precio_unitario) {
        // this.id_detalle = id_detalle;
        this.pedido = pedido;
        this.pieza = pieza;
        this.cantidad_solicitada = cantidad_solicitada;
        this.cantidad_recibida = cantidad_recibida;
        this.precio_unitario = precio_unitario;
    }

    public PedidosDetalle() {
    }

    // Métodos Públicos

    // Getters
    /**
     * Obtiene el ID del detalle del pedido.
     * 
     * @return ID del detalle.
     */
    public int getId_detalle() {
        return id_detalle;
    }

    public void setIdDetalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    /**
     * Obtiene el pedido asociado.
     * 
     * @return Objeto PedidosCompra.
     */
    public PedidosCompra getPedido() {
        return pedido;
    }

    /**
     * Obtiene la pieza de repuesto.
     * 
     * @return Objeto PiezasRepuesto.
     */
    public PiezasRepuesto getPieza() {
        return pieza;
    }

    /**
     * Obtiene la cantidad solicitada.
     * 
     * @return Cantidad solicitada.
     */
    public int getCantidad_solicitada() {
        return cantidad_solicitada;
    }

    /**
     * Obtiene la cantidad recibida.
     * 
     * @return Cantidad recibida.
     */
    public int getCantidad_recibida() {
        return cantidad_recibida;
    }

    /**
     * Obtiene el precio unitario.
     * 
     * @return Precio unitario.
     */
    public double getPrecio_unitario() {
        return precio_unitario;
    }

    // Setters
    /**
     * Establece el pedido
     * 
     * @param pedido Objeto PedidosCompra
     */
    public void setPedido(PedidosCompra pedido) {
        this.pedido = pedido;
    }

    /**
     * Establece la pieza de repuesto
     * 
     * @param pieza Objeto PiezasRepuesto
     */
    public void setPieza(PiezasRepuesto pieza) {
        this.pieza = pieza;
    }

    /**
     * Establece la cantidad solicitada.
     * 
     * @param cantidad_solicitada Cantidad solicitada.
     */
    public void setCantidad_solicitada(int cantidad_solicitada) {
        this.cantidad_solicitada = cantidad_solicitada;
    }

    /**
     * Establece la cantidad recibida.
     * 
     * @param cantidad_recibida Cantidad recibida.
     */
    public void setCantidad_recibida(int cantidad_recibida) {
        this.cantidad_recibida = cantidad_recibida;
    }

    /**
     * Establece el precio unitario.
     * 
     * @param precio_unitario Precio unitario.
     */
    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    // toString
    /**
     * Devuelve una representación en cadena de los detalles de un pedido
     * Incluye la ID, pedido, pieza, cantidad solicitada, cantidad recibida, precio
     * unitario
     * 
     * @return String con todos los atributos del objeto/clase
     */
    @Override
    public String toString() {
        return "PedidosDetalle{" + "id_detalle=" + id_detalle + ", pedido=" + pedido + ", pieza=" + pieza
                + ", cantidad_solicitada=" + cantidad_solicitada + ", cantidad_recibida=" + cantidad_recibida
                + ", precio_unitario=" + precio_unitario + '}';
    }

    // Equals y hashCode
    /**
     * Genera un código hash sobre la clase PedidosDetalle
     * Es indispensable mantener la consistencia con {@link #equals(Object)}
     * 
     * @return código hash del objeto
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id_detalle;
        hash = 89 * hash + Objects.hashCode(this.pedido);
        hash = 89 * hash + Objects.hashCode(this.pieza);
        hash = 89 * hash + this.cantidad_solicitada;
        hash = 89 * hash + this.cantidad_recibida;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.precio_unitario)
                ^ (Double.doubleToLongBits(this.precio_unitario) >>> 32));
        return hash;
    }

    /**
     * Compara este objeto con otro objeto para verificar igualdad
     * Dos usuarios se consideran iguales si y solo si comparten el mismo ID único
     * 
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
        final PedidosDetalle other = (PedidosDetalle) obj;
        if (this.id_detalle != other.id_detalle) {
            return false;
        }
        if (this.cantidad_solicitada != other.cantidad_solicitada) {
            return false;
        }
        if (this.cantidad_recibida != other.cantidad_recibida) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio_unitario) != Double.doubleToLongBits(other.precio_unitario)) {
            return false;
        }
        if (!Objects.equals(this.pedido, other.pedido)) {
            return false;
        }
        return Objects.equals(this.pieza, other.pieza);
    }

    // Métodos Privados
}
