/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

//package principal;
//
//import Modelo.Usuario;
//import Modelo.HorarioTrabajo;
//
//import DAO.UsuarioDAO;
//import DAO.HorarioTrabajoDAO;
//
//import PckConexionBD.ConexionBD;
//
//import java.sql.Connection;
//import java.time.LocalTime;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        // =========================
//        // CONEXIÓN BD
//        // =========================
//        Connection con = ConexionBD.getConnection();
//
//        // =========================
//        // DAOS
//        // =========================
//        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
//        HorarioTrabajoDAO horarioDAO = new HorarioTrabajoDAO(con);
//
//        // =========================
//        // CREATE → USUARIOS
//        // =========================
//        Usuario u1 = new Usuario(0, "Ana López", "ana@mail.com", "admin");
//        Usuario u2 = new Usuario(0, "Carlos Pérez", "carlos@mail.com", "tecnico");
//
//        usuarioDAO.insertar(u1);
//        usuarioDAO.insertar(u2);
//
//        // =========================
//        // CREATE → HORARIOS
//        // =========================
//        HorarioTrabajo h1 = new HorarioTrabajo(
//                0,
//                1,
//                "Lunes",
//                LocalTime.of(8, 0),
//                LocalTime.of(14, 0)
//        );
//
//        HorarioTrabajo h2 = new HorarioTrabajo(
//                0,
//                1,
//                "Martes",
//                LocalTime.of(8, 0),
//                LocalTime.of(16, 0)
//        );
//
//        horarioDAO.insertar(h1);
//        horarioDAO.insertar(h2);
//
//        // =========================
//        // READ → USUARIOS
//        // =========================
//        System.out.println("=== USUARIOS ===");
//
//        for (Usuario u : usuarioDAO.listar()) {
//
//            System.out.println(u);
//
//            if (u.esAdmin()) {
//                System.out.println(" -> Es administrador");
//            }
//        }
//
//        // =========================
//        // READ → HORARIOS
//        // =========================
//        System.out.println("\n=== HORARIOS ===");
//
//        for (HorarioTrabajo h : horarioDAO.listar()) {
//
//            System.out.println(h);
//            System.out.println("Horas: " + h.duracionHoras());
//
//            if (h.esTurnoLargo()) {
//                System.out.println(" -> Turno largo");
//            }
//        }
//
//        // =========================
//        // UPDATE EJEMPLO
//        // =========================
//        u1.setNombre("Ana López Modificada");
//        usuarioDAO.actualizar(u1);
//
//        // =========================
//        // DELETE EJEMPLO
//        // =========================
//        usuarioDAO.eliminar(2);
//
//        System.out.println("\n✔ Sistema CMMS ejecutado correctamente");
//    }
//}