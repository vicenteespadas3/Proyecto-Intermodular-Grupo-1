/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.PedidosCompra;
import Modelo.PedidosDetalle;
import Modelo.Proveedor;
import PckConexionBD.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

/**
 * <h2>Clase PedidosCompraDAO</h2>
 * Clase encargada de ser la intermediaria entre el modelo(PedidosCompra) y la
 * base de datos.
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
     * 
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lista todos los pedidos de compra
     * 
     * @return Devuelve una lista de pedidos de compra
     */
    public List<PedidosCompra> listar() {
        List<PedidosCompra> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_compra";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // 1. Datos básicos
                int idPedido = rs.getInt("id_pedido");
                LocalDateTime fechaPedido = rs.getTimestamp("fecha_pedido").toLocalDateTime();
                String observaciones = rs.getString("observaciones");

                // 2. Extraer fechas de entrega (Tratando posibles nulos en la DB)
                Timestamp tsEsperada = rs.getTimestamp("fecha_entrega_esperada");
                LocalDateTime fechaEsperada = (tsEsperada != null) ? tsEsperada.toLocalDateTime() : null;

                Timestamp tsReal = rs.getTimestamp("fecha_entrega_real");
                LocalDateTime fechaReal = (tsReal != null) ? tsReal.toLocalDateTime() : null;

                // 3. Cargar el objeto Proveedor completo
                int idProv = rs.getInt("id_proveedor");
                ProveedorDAO provDAO = new ProveedorDAO(this.con);
                Proveedor proveedor = provDAO.buscarPorId(idProv);

                // 4. Crear el objeto con el constructor de 6 parámetros de tus compañeros
                // Orden: fecha, proveedor, detalles, entrega_esp, entrega_real, observaciones
                PedidosCompra pedido = new PedidosCompra(
                        fechaPedido,
                        proveedor,
                        new ArrayList<>(), // Se llenará con setDetalles abajo
                        fechaEsperada,
                        fechaReal,
                        observaciones);

                // 5. Asignar el ID (ya que el constructor lo tiene comentado)
                pedido.setId_pedido(idPedido);

                // 6. Cargar los hijos (Detalles)
                pedido.setDetalles(this.obtenerDetalles(idPedido));

                listaPedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pedidos: " + e.getMessage());
        }
        return listaPedidos;
    }

    public boolean eliminarPedidoCompleto(int idPedido) {
        // Consultas necesarias
        String sqlDetalles = "DELETE FROM pedidos_detalle WHERE id_pedido = ?";
        String sqlPedido = "DELETE FROM pedidos_compra WHERE id_pedido = ?";

        try {
            // 1. Borramos los detalles primero para evitar el RESTRICT
            try (PreparedStatement psDet = con.prepareStatement(sqlDetalles)) {
                psDet.setInt(1, idPedido);
                psDet.executeUpdate();
                System.out.println("Detalles del pedido " + idPedido + " eliminados.");
            }

            // 2. Ahora que el pedido está "solo", lo borramos a él
            try (PreparedStatement psPed = con.prepareStatement(sqlPedido)) {
                psPed.setInt(1, idPedido);
                int filas = psPed.executeUpdate();
                if (filas > 0) {
                    System.out.println("Pedido " + idPedido + " eliminado correctamente.");
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // --------Métodos de Actualizar-----------
    public boolean actualizarEstado(int idPedido, int nuevoEstadoId) {
        String sql = "UPDATE pedidos_compra SET id_estado = ? WHERE id_pedido = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoEstadoId);
            ps.setInt(2, idPedido);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarRecepcion(int idDetalle, int cantRecibida) {
        String sql = "UPDATE pedidos_detalle SET cantidad_recibida = ? WHERE id_detalle = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cantRecibida);
            ps.setInt(2, idDetalle);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cantidad: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método auxiliar para obtener las líneas de un pedido específico
     */
    private List<PedidosDetalle> obtenerDetalles(int idPedido) {
        List<PedidosDetalle> detalles = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_detalle WHERE id_pedido = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PedidosDetalle linea = new PedidosDetalle();
                linea.setIdDetalle(rs.getInt("id_detalle"));
                linea.setCantidad_solicitada(rs.getInt("cantidad_solicitada"));
                linea.setCantidad_recibida(rs.getInt("cantidad_recibida"));
                linea.setPrecio_unitario(rs.getDouble("precio_unitario"));

                // Cargar la pieza (necesitas el objeto Pieza completo para la tabla)
                int idPieza = rs.getInt("id_pieza");
                PiezasRepuestoDAO piezaDAO = new PiezasRepuestoDAO(this.con);
                linea.setPieza(piezaDAO.buscarPorId(idPieza));

                detalles.add(linea);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener detalles: " + e.getMessage());
        }
        return detalles;
    }
}
