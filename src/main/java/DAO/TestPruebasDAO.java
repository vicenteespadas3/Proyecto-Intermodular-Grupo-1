package DAO;

import java.time.LocalDateTime;
import java.util.List;

import Modelo.Definiciones;
import Modelo.PedidosCompra;
import Modelo.PedidosDetalle;
import Modelo.PiezasRepuesto;
import Modelo.Proveedor;
import PckConexionBD.ConexionBD;

public class TestPruebasDAO {
    public static void main(String[] args) {
        /*
         * // 1. Instanciar los DAOs necesarios
         * PedidosCompraDAO pedidoDAO = new
         * PedidosCompraDAO(ConexionBD.getConnection());
         * 
         * // 2. Crear objetos de apoyo (necesitas IDs que existan en tu BBDD externa)
         * // Nota: Asegúrate de que el ID 1 de proveedor y el ID 1 de pieza existan en
         * tu
         * // tabla
         * Proveedor proveedor = new Proveedor();
         * proveedor.setId_proveedor(1);
         * 
         * Definiciones estado = new Definiciones();
         * estado.setIdDefinicion(1);
         * 
         * // 3. Crear el Pedido (Cabecera)
         * PedidosCompra nuevoPedido = new PedidosCompra();
         * nuevoPedido.setFecha_pedido(LocalDateTime.now());
         * nuevoPedido.setProveedor(proveedor);
         * nuevoPedido.setEstado(estado);
         * nuevoPedido.setObservaciones("Pedido de prueba desde Java");
         * 
         * // 4. Crear los Detalles (Hijos)
         * // pieza1.setId_pieza(1); // Debe existir en tu BBDD
         * PiezasRepuesto pieza = new PiezasRepuesto(1, "Filtro de Aceite");
         * PedidosDetalle linea1 = new PedidosDetalle();
         * linea1.setPieza(pieza);
         * linea1.setCantidad_solicitada(10);
         * linea1.setPrecio_unitario(25.50);
         * 
         * // Añadir la linea al pedido (usando el método que creamos antes)
         * nuevoPedido.getDetalles().add(linea1);
         * 
         * // 5. PROBAR EL INSERT (CREATE)
         * System.out.println("Intentando insertar pedido...");
         * if (pedidoDAO.insertar(nuevoPedido)) {
         * System.out.println("✅ Pedido e hilos insertados con éxito.");
         * } else {
         * System.out.println("❌ Fallo en la inserción.");
         * }
         */
        // --- PROBAR EL LISTADO (READ) ---
        System.out.println("\n--- LISTADO DE PEDIDOS COMPLETO ---");
        PedidosCompraDAO pedidoDAO = new PedidosCompraDAO(ConexionBD.getConnection());
        // Llamamos al método listar del DAO
        List<PedidosCompra> lista = pedidoDAO.listar();

        if (lista.isEmpty()) {
            System.out.println("No se encontraron pedidos en la base de datos.");
        } else {
            for (PedidosCompra p : lista) {
                System.out.println("Pedido ID: " + p.getId_pedido());

                // Validamos si el proveedor se cargó correctamente
                if (p.getProveedor() != null) {
                    System.out.println("Proveedor: " + p.getProveedor().getNombre());
                } else {
                    System.out.println(
                            "Proveedor: [ERROR] El ID de proveedor del pedido no existe en la tabla proveedores.");
                }
            }
        }
    }
}
