package DAO;

import java.time.LocalDateTime;

import Modelo.Definiciones;
import Modelo.PedidosCompra;
import Modelo.PedidosDetalle;
import Modelo.PiezasRepuesto;
import Modelo.Proveedor;
import PckConexionBD.ConexionBD;

public class TestPruebasDAO {
    public static void main(String[] args) {
        // 1. Instanciar los DAOs necesarios
        PedidosCompraDAO pedidoDAO = new PedidosCompraDAO(ConexionBD.getConnection());

        // 2. Crear objetos de apoyo (necesitas IDs que existan en tu BBDD externa)
        // Nota: Asegúrate de que el ID 1 de proveedor y el ID 1 de pieza existan en tu
        // tabla
        Proveedor proveedor = new Proveedor("7342729F", "OicaSL", "600000000", "OicaSL@famail.com");
        // proveedor.setId_proveedor(1); -- Autoincrementado por defecto

        Definiciones estado = new Definiciones(1, "Pendientes", "Pendiente");
        // estado.setId_definicion(1); // Ejemplo: 'Pendiente'

        // 3. Crear el Pedido (Cabecera)
        PedidosCompra nuevoPedido = new PedidosCompra();
        nuevoPedido.setFecha_pedido(LocalDateTime.now());
        nuevoPedido.setProveedor(proveedor);
        nuevoPedido.setEstado(estado);
        nuevoPedido.setObservaciones("Pedido de prueba desde Java");

        // 4. Crear los Detalles (Hijos)
        // pieza1.setId_pieza(1); // Debe existir en tu BBDD
        PiezasRepuesto pieza = new PiezasRepuesto(1, "Filtro de Aceite");
        PedidosDetalle linea1 = new PedidosDetalle();
        linea1.setPieza(pieza);
        linea1.setCantidad_solicitada(10);
        linea1.setPrecio_unitario(25.50);

        // Añadir la linea al pedido (usando el método que creamos antes)
        nuevoPedido.getDetalles().add(linea1);

        // 5. PROBAR EL INSERT (CREATE)
        System.out.println("Intentando insertar pedido...");
        if (pedidoDAO.insertar(nuevoPedido)) {
            System.out.println("✅ Pedido e hilos insertados con éxito.");
        } else {
            System.out.println("❌ Fallo en la inserción.");
        }
        /*
         * // 6. PROBAR EL LISTAR (READ)
         * System.out.println("\nListando pedidos de la base de datos:");
         * // Suponiendo que tienes un método listar() que devuelve List<PedidosCompra>
         * for (PedidosCompra p : pedidoDAO.listar()) {
         * System.out.println("ID: " + p.getId_pedido() + " | Prov: " +
         * p.getProveedor().getNombre());
         * // Probar si trajo los detalles
         * System.out.println("   Líneas: " + p.getDetalles().size());
         * }
         */
    }
}
