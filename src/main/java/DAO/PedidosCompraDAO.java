/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;

/**
 * <h2>Clase PedidosCompraDAO</h2>
 * Clase encargada de ser la intermediaria entre el modelo(PedidosCompra) y la base de datos.
 * Proporciona métodos para gestionar la persistencia de los pedidos de compra.
 * 
 * @see PedidosCompra
 * @author Miguel Ángel Sánchez Martínez
 * @version 0.0.1
 */
public class PedidosCompraDAO {
    
    private Connection con;

    /**
     * Constructor que recibe una conexión a la base de datos.
     * @param con Conexión activa.
     */
    public PedidosCompraDAO(Connection con) {
        this.con = con;
    }
}
