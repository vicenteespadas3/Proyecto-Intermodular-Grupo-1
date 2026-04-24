/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;

/**
 * <h2>Clase TareasHerramientasDAO</h2>
 * Clase encargada de ser la intermediaria entre el modelo(TareasHerramientas) y
 * la base de datos.
 * Gestiona la relación entre tareas de mantenimiento y herramientas utilizadas.
 * 
 * @see Modelo.TareasHerramientas
 * @author Miguel Ángel Sánchez Martínez
 * @version 0.0.1
 */
public class TareasHerramientasDAO {

    /**
     * Constructor que recibe una conexión a la base de datos.
     * 
     * @param con Conexión activa.
     */
    public TareasHerramientasDAO(Connection con) {
    }
}
