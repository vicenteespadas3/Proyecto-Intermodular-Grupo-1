/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.PedidosCompra;
import Modelo.PedidosDetalle;
import PckConexionBD.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <h2>Clase PedidosCompraDAO</h2>
 * Clase encargada de ser la intermediaria entre el modelo(PedidosCompra) y la base de datos.
 * Proporciona métodos para gestionar la persistencia de los pedidos de compra.
 * 
 * @see Modelo.PedidosCompra
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
    
    public boolean insertar(PedidosCompra pedido) {
        Connection con = ConexionBD.getConnection();
        String sqlPedido = "INSERT INTO pedidos_compra (fecha_pedido, id_proveedor, id_estado, observaciones) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO pedidos_detalle (id_pedido, id_pieza, cantidad_solicitada, precio_unitario) VALUES (?, ?, ?, ?)";

        try {
            con.setAutoCommit(false); // Iniciamos transacción

            // 1. Insertar la cabecera del Pedido
            PreparedStatement psPedido = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            psPedido.setTimestamp(1, java.sql.Timestamp.valueOf(pedido.getFecha_pedido())); // Usando LocalDateTime
            psPedido.setInt(2, pedido.getProveedor().getIdProveedor());
            psPedido.setInt(3, pedido.getEstado().getIdDefinicion());
            psPedido.setString(4, pedido.getObservaciones());

            int filasPedido = psPedido.executeUpdate();

            // 2. Obtener el ID generado para el pedido
            if (filasPedido > 0) {
                ResultSet rs = psPedido.getGeneratedKeys();
                if (rs.next()) {
                    int idPedidoGenerado = rs.getInt(1);

                    // 3. Insertar los detalles usando un bucle
                    PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
                    for (PedidosDetalle det : pedido.getDetalles()) {
                        psDetalle.setInt(1, idPedidoGenerado);
                        psDetalle.setInt(2, det.getPieza().getIdPieza());
                        psDetalle.setInt(3, det.getCantidad_solicitada());
                        psDetalle.setDouble(4, det.getPrecio_unitario());
                        psDetalle.addBatch(); // Los acumulamos para enviarlos juntos
                    }
                    psDetalle.executeBatch(); // Ejecución masiva de detalles
                }
            }

            con.commit(); // Si todo fue bien, guardamos cambios permanentemente
            return true;

        } catch (SQLException e) {
            try {
                con.rollback(); // Si algo falló, deshacemos todo
                System.err.println("Error en transacción: " + e.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                con.setAutoCommit(true); // Restauramos el estado por defecto
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
