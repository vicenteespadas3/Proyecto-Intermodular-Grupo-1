/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package principal;

import Modelo.HorarioTrabajo;

/**
 *
 * @author
 */
//public class Main {
//
//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//    }
//}

import Modelo.Usuario;

import java.time.LocalTime;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // =========================
        // CREAR USUARIOS
        // =========================

        Usuario u1 = new Usuario(1, "Ana López", "ana@mail.com", "admin");
        Usuario u2 = new Usuario(2, "Carlos Pérez", "carlos@mail.com", "tecnico");

        // Lista para simular BD
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(u1);
        usuarios.add(u2);

        // =========================
        // CREAR HORARIOS
        // =========================

        HorarioTrabajo h1 = new HorarioTrabajo(
                1,
                2,
                "Lunes",
                LocalTime.of(8, 0),
                LocalTime.of(14, 0)
        );

        HorarioTrabajo h2 = new HorarioTrabajo(
                2,
                2,
                "Martes",
                LocalTime.of(8, 0),
                LocalTime.of(16, 0)
        );

        ArrayList<HorarioTrabajo> horarios = new ArrayList<>();
        horarios.add(h1);
        horarios.add(h2);

        // =========================
        // MOSTRAR DATOS
        // =========================

        System.out.println("=== USUARIOS ===");
        for (Usuario u : usuarios) {
            System.out.println(u);

            if (u.esAdmin()) {
                System.out.println(" -> Es administrador");
            }
        }

        System.out.println("\n=== HORARIOS ===");
        for (HorarioTrabajo h : horarios) {
            System.out.println(h);
            System.out.println("Horas: " + h.duracionHoras());

            if (h.esTurnoLargo()) {
                System.out.println(" -> Turno largo");
            }
        }
    }
}