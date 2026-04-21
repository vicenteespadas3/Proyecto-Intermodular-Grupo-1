package DAOG5FueraDeUso;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Importamos todas las clases modelo necesarias
import ModeloG5FueraDeUso.PlanMantenimiento;
import ModeloG5FueraDeUso.GamaMantenimiento;
//import modelo.Definicion;
import PckConexionBD.ConexionBD;

public class PlanMantenimientoDAO {

    // --- 1. MÉTODO PARA LEER TODOS LOS DATOS (SELECT) ---
    public List<PlanMantenimiento> obtenerTodos() {
        List<PlanMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM planes_mantenimiento";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PlanMantenimiento plan = new PlanMantenimiento();
                
                // 1. Asignamos los datos básicos del plan
                plan.setIdPlan(rs.getInt("id_plan"));
                plan.setHorasIntervalo((Integer) rs.getObject("horas_intervalo")); 
                plan.setDuracionEstimada((Integer) rs.getObject("duracion_estimada"));
                plan.setActivo(rs.getBoolean("activo"));
                
                // --- NUEVO: Extraer la fecha ---
                // 1. Sacamos la fecha de SQL como un "Timestamp"
                java.sql.Timestamp fechaSQL = rs.getTimestamp("fecha_creacion");
                
                // 2. Si la fecha no es nula, la convertimos al LocalDateTime de Java
                if (fechaSQL != null) {
                    plan.setFechaCreacion(fechaSQL.toLocalDateTime());
                }
                

                /* 2. Construimos la Máquina y se la asignamos al plan
                Maquina maquina = new Maquina();
                maquina.setIdMaquina(rs.getInt("id_maquina"));
                plan.setMaquina(maquina);
                */

                // 3. Construimos la Gama y se la asignamos al plan
                GamaMantenimiento gama = new GamaMantenimiento();
                gama.setIdGama(rs.getInt("id_gama"));
                plan.setGama(gama);

                /* 4. Construimos la Frecuencia (Definicion) y se la asignamos
                Definicion frecuencia = new Definicion();
                frecuencia.setIdDefinicion(rs.getInt("id_frecuencia"));
                plan.setFrecuencia(frecuencia);
                */
                
                /* 5. Construimos la Dificultad (Definicion) y se la asignamos
                Definicion dificultad = new Definicion();
                dificultad.setIdDefinicion(rs.getInt("id_dificultad"));
                plan.setDificultad(dificultad);
                */
                
                // 3.Clases Aisladas temporalmente
                plan.setIdMaquina(rs.getInt("id_maquina"));
                plan.setIdFrecuencia(rs.getInt("id_frecuencia"));
                plan.setIdDificultad(rs.getInt("id_dificultad"));
                
                // Finalmente, añadimos el plan completamente armado a la lista
                lista.add(plan);
                
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los planes: " + e.getMessage());
        }
        return lista;
    }

    // --- 2. MÉTODO PARA GUARDAR UN DATO NUEVO (INSERT) ---
    public boolean insertar(PlanMantenimiento plan) {
        String sql = "INSERT INTO planes_mantenimiento (id_maquina, id_gama, id_frecuencia, horas_intervalo, id_dificultad, duracion_estimada, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // OJO AQUÍ: Para sacar el ID que va a la BBDD, tenemos que "entrar" al objeto primero
            ps.setInt(1, plan.getIdMaquina());
            ps.setInt(2, plan.getGama().getIdGama());
            ps.setInt(3, plan.getIdFrecuencia());
            ps.setObject(4, plan.getHorasIntervalo());
            ps.setInt(5, plan.getIdDificultad());
            ps.setObject(6, plan.getDuracionEstimada());
            ps.setBoolean(7, plan.isActivo());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar el plan: " + e.getMessage());
            return false;
        }
    }
}