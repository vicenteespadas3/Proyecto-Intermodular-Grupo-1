package DAO;

import Modelo.Definiciones;
// Importa también el DAO si lo vas a usar
// import DAO.DefinicionesDAO; 

public class PRUEBASGM {
    public static void main(String[] args) {
        System.out.println("=== TEST DEFINICIONES GUADALUPE ===");
        
        Definiciones def = new Definiciones(1, "Prueba", "TEST", "T01", "Desc", 1, true, "OK");
        System.out.println("Objeto creado: " + def.getNombre());
    }
}