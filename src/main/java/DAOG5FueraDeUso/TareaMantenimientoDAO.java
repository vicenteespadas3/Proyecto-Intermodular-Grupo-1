package DAOG5FueraDeUso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloG5FueraDeUso.TareaMantenimiento;
import ModeloG5FueraDeUso.PlanMantenimiento;
import PckConexionBD.ConexionBD;

/**
 *
 * @author Eric
 */
public class TareaMantenimientoDAO {
// --- 1. LEER DATOS (SELECT) ---
    public List<TareaMantenimiento> obtenerTodas() {
        List<TareaMantenimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas_mantenimiento ORDER BY id_plan, orden";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TareaMantenimiento tarea = new TareaMantenimiento();
                
                // Rellenamos datos básicos
                tarea.setIdTarea(rs.getInt("id_tarea"));
                tarea.setDescripcion(rs.getString("descripcion"));
                tarea.setOrden(rs.getInt("orden"));
                tarea.setInstruccionesDetalladas(rs.getString("instrucciones_detalladas"));
                tarea.setRequiereEpi(rs.getBoolean("requiere_epi"));
                tarea.setEpisNecesarios(rs.getString("epis_necesarios"));
                tarea.setReglasSeguridad(rs.getString("reglas_seguridad"));
                tarea.setReglasMedioAmbiente(rs.getString("reglas_medio_ambiente"));

                // Clase Plan
                PlanMantenimiento plan = new PlanMantenimiento();
                plan.setIdPlan(rs.getInt("id_plan"));
                tarea.setPlan(plan);
                
                lista.add(tarea);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las tareas: " + e.getMessage());
        }
        return lista;
    }

    // --- 2. GUARDAR DATOS (INSERT) ---
    public boolean insertar(TareaMantenimiento tarea) {
        String sql = "INSERT INTO tareas_mantenimiento (id_plan, descripcion, orden, instrucciones_detalladas, requiere_epi, epis_necesarios, reglas_seguridad, reglas_medio_ambiente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Sacamos el ID del Plan usando el objeto
            ps.setInt(1, tarea.getPlan().getIdPlan());
            ps.setString(2, tarea.getDescripcion());
            ps.setInt(3, tarea.getOrden());
            ps.setString(4, tarea.getInstruccionesDetalladas());
            ps.setBoolean(5, tarea.isRequiereEpi());
            ps.setString(6, tarea.getEpisNecesarios());
            ps.setString(7, tarea.getReglasSeguridad());
            ps.setString(8, tarea.getReglasMedioAmbiente());
            
            //Comprobamos que se ha ejecutado bien la SQL y ha modificado filas
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar la tarea: " + e.getMessage());
            return false;
        }
    }
    
    // --- 3. BORRAR DATOS (DELETE) ---
    public boolean eliminar(int idTarea) {
        String sql = "DELETE FROM tareas_mantenimiento WHERE id_tarea = ?";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            
             ps.setInt(1, idTarea);
             int filasAfectadas = ps.executeUpdate();
             return filasAfectadas > 0;
             
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tarea: " + e.getMessage());
            return false;
        }
    }    
}
