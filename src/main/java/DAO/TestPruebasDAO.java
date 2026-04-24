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
         * tu tabla
         * Proveedor proveedor = new Proveedor();
         * proveedor.setIdProveedor(1);
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
        /*
         * // --- PROBAR EL DELETE ---
         * // 1. Conectamos (usa tu clase de conexión habitual)
         * Connection cn = ConexionBD.getConnection();
         * PedidosCompraDAO dao = new PedidosCompraDAO(cn);
         * 
         * System.out.println("--- INICIANDO TEST DE BORRADO SEGURO ---");
         * 
         * // 2. CREACIÓN: Insertamos un pedido "fantasma"
         * // TIP: Usa un ID muy alto (ej. 999) o deja que la BD te dé el siguiente
         * // int idSacrificio = 999;
         * System.out.println("Paso 1: Creando pedido de prueba con ID " + "8" + "...");
         * Proveedor proveedor = new Proveedor();
         * proveedor.setIdProveedor(1);
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
         * if (dao.insertar(nuevoPedido)) {
         * System.out.println("✅ Pedido e hilos insertados con éxito.");
         * } else {
         * System.out.println("❌ Fallo en la inserción.");
         * }
         * // 3. ELIMINACIÓN: Probamos tu lógica de composición
         * System.out.println("Paso 2: Ejecutando eliminarPedidoCompleto()...");
         * boolean exito = dao.eliminarPedidoCompleto(9);
         * 
         * // 4. VERIFICACIÓN
         * if (exito) {
         * System.out.
         * println("RESULTADO: [OK] El pedido y sus detalles han muerto con honor.");
         * } else {
         * System.out.
         * println("RESULTADO: [FALLO] No se pudo borrar (¿quizás el ID no existía?)");
         * }
         */
        // --- PROBAR ACTUALIZACIONES ---
        // Primer Listado
        System.out.println("\n--- PRIMER LISTADO DE PEDIDOS ---");
        PedidosCompraDAO dao = new PedidosCompraDAO(ConexionBD.getConnection());
        List<PedidosCompra> lista = dao.listar();

        if (lista.isEmpty()) {
            System.out.println("No se encontraron pedidos en la base de datos.");
        } else {
            for (PedidosCompra p : lista) {
                System.out.println("Pedido ID: " + p.getId_pedido());
                System.out.println("Proveedor: " + p.getProveedor().getNombre());

                for (PedidosDetalle det : p.getDetalles()) {
                    PiezasRepuesto pieza1 = det.getPieza();
                    System.out.println("   -> Pieza: " + pieza1.getNombre());
                    System.out.println("      Cantidad Solicitada: " + det.getCantidad_solicitada());
                    System.out.println("      Cantidad Recibida: " + det.getCantidad_recibida());
                    System.out.println("      Precio Unidad: " + det.getPrecio_unitario() + "EUR");
                }
            }
        }
        // 1. Cambiar estado de un pedido a "ENVIADO" (ID 2)
        // Connection cn = ConexionBD.getConnection();
        // PedidosCompraDAO dao = new PedidosCompraDAO(cn);
        // Crear un pedido a modificar
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1);

        Definiciones estado = new Definiciones();
        estado.setIdDefinicion(1);

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
        if (dao.insertar(nuevoPedido)) {
            System.out.println("✅ Pedido e hilos insertados con éxito.");
            // --- RECOGER DATOS REALES DE LA BD ---
            List<PedidosCompra> listaTemporal = dao.listar();
            PedidosCompra pedidoRecienCreado = listaTemporal.get(listaTemporal.size() - 1);

            int idGenerado = pedidoRecienCreado.getId_pedido();

            // 3. ACTUALIZAR ESTADO del que acabamos de crear
            if (dao.actualizarEstado(idGenerado, 2)) {
                System.out.println("✅ Estado del pedido " + idGenerado + " actualizado a ENVIADO.");
            } else {
                System.out.println("❌ Error al actualizar estado.");
            }
            // 4. ACTUALIZAR RECEPCIÓN de su línea
            if (!pedidoRecienCreado.getDetalles().isEmpty()) {
                int idDetalleReal = pedidoRecienCreado.getDetalles().get(0).getId_detalle(); // ID dinámico del detalle
                System.out.println("DEBUG: Actualizando detalle ID " + idDetalleReal + " del pedido " + idGenerado);
                if (dao.actualizarRecepcion(idDetalleReal, 5)) {
                    System.out.println("⭐ ¡EXITO! Ahora el listado debería mostrar 5 unidades.");
                } else {
                    System.out.println("❌ El DAO devolvió false (no encontró el ID " + idDetalleReal + ")");
                }
                System.out.println("\n--- ACTUALIZANDO RECEPCIÓN ---");
                if (dao.actualizarRecepcion(idDetalleReal, 5)) { // Usamos el ID real de la línea
                    System.out.println("✅ Recibidas 5 unidades en la línea " + idDetalleReal + ".");
                } else {
                    System.out.println("❌ Error al actualizar recepción.");
                }
            }
        }

        // --- PROBAR EL LISTADO (READ) ---
        System.out.println("\n--- LISTADO DE PEDIDOS COMPLETO ---");

        // PedidosCompraDAO pedidoDAO2 = new
        // PedidosCompraDAO(ConexionBD.getConnection());
        // Llamamos al método listar del DAO
        List<PedidosCompra> lista2 = dao.listar();

        if (lista2.isEmpty()) {
            System.out.println("No se encontraron pedidos en la base de datos.");
        } else {
            for (PedidosCompra p : lista2) {
                System.out.println("Pedido ID: " + p.getId_pedido());
                System.out.println("Proveedor: " + p.getProveedor().getNombre());

                // Recorremos los detalles del pedido
                for (PedidosDetalle det : p.getDetalles()) {
                    // Accedemos al atributo 'pieza' que tienes en el detalle
                    PiezasRepuesto pieza1 = det.getPieza();

                    System.out.println("   -> Pieza: " + pieza1.getNombre());
                    System.out.println("      Cantidad Solicitada: " + det.getCantidad_solicitada());
                    System.out.println("      Cantidad Recibida: " + det.getCantidad_recibida());
                    System.out.println("      Precio Unidad: " + det.getPrecio_unitario() + "EUR");
                }
                System.out.println("-----------------------------------");
            }
        }
    }
}
