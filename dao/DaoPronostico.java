package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import modelo.Partido;
import modelo.Pronostico;

public class DaoPronostico {

    private Connection con = null;

    public DaoPronostico() throws SQLException {
        con = DBConnection.getConnection();
    }

    public ArrayList<Partido> listarPartidos() throws SQLException {
        String sql = "SELECT * FROM partido";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet result = ps.executeQuery();
        ArrayList<Partido> partidos = new ArrayList<>();

        while (result.next()) {
            partidos.add(new Partido(result.getInt("idPartido"), result.getInt("equipoA_id"),
                    result.getInt("equipoB_id"), result.getInt("resultEquipoA"), result.getInt("resultEquipoB"),
                    result.getString("nomEquipoA"), result.getString("nomEquipoB"), result.getString("ganador")));
        }
        return partidos;
    }

    public void insertarPronostico(Pronostico pronostico) throws SQLException {
        String sql = "INSERT INTO pronostico (idUsuario, idPartido, resultPronA, resultPronB, ganador) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, pronostico.getIdUsuario());
        ps.setInt(2, pronostico.getIdPartido());
        ps.setInt(3, pronostico.getResultPronA());
        ps.setInt(4, pronostico.getResultPronB());
        ps.setString(5, pronostico.getGanador());

        ps.executeUpdate();
    }
    
    
    public List<Pronostico> obtenerPronosticosDeUsuario(int idUsuario) throws SQLException {
        String sql =  "SELECT p.*, sa.nombre AS nomEquipoA, sb.nombre AS nomEquipoB " +
                      "FROM pronostico p " +
                      "JOIN seleccion sa ON p.nomEquipoA = sa.idSeleccion " +
                      "JOIN seleccion sb ON p.nomEquipoB = sb.idSeleccion " +
                      "WHERE p.idUsuario = ?";
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet result = ps.executeQuery();
        
        List<Pronostico> pronosticos = new ArrayList<>();

        while (result.next()) {
            Pronostico pronostico = new Pronostico();
            pronostico.setIdPronostico(result.getInt("idPronostico"));
            pronostico.setIdUsuario(result.getInt("idUsuario"));
            pronostico.setIdPartido(result.getInt("idPartido"));
            pronostico.setNomEquipoA(result.getString("nomEquipoA"));
            pronostico.setResultPronA(result.getInt("resultPronA"));
            pronostico.setNomEquipoB(result.getString("nomEquipoB"));
            pronostico.setResultPronB(result.getInt("resultPronB"));
            pronostico.obtenerGanador();
            pronosticos.add(pronostico);
        }
        return pronosticos;
    }
    
 // actualizar la tabla pronostico
    
    public void puntuacion() throws SQLException {
        String sql = "SELECT p.idPronostico, p.idUsuario, p.idPartido, p.resultPronA, p.resultPronB, pr.resultEquipoA, pr.resultEquipoB " +
                     "FROM pronostico p " +
                     "JOIN partido pr ON p.idPartido = pr.idPartido";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet result = ps.executeQuery();

        while (result.next()) {
            int idPronostico = result.getInt("idPronostico");
            int idUsuario = result.getInt("idUsuario");
            int resultPronA = result.getInt("resultPronA");
            int resultPronB = result.getInt("resultPronB");
            int resultEquipoA = result.getInt("resultEquipoA");
            int resultEquipoB = result.getInt("resultEquipoB");

            int puntuacion = 0;
            if (resultPronA == resultEquipoA && resultPronB == resultEquipoB) {
                puntuacion = 5; // Resultado exacto
            } else if ((resultPronA > resultPronB && resultEquipoA > resultEquipoB) ||
                       (resultPronA < resultPronB && resultEquipoA < resultEquipoB) ||
                       (resultPronA == resultPronB && resultEquipoA == resultEquipoB)) {
                puntuacion = 10; // Acierto del resultado (ganador/empate)
            }

            // Actualizar puntos del usuario en la tabla de pronósticos
            actualizarPuntuacion(idPronostico, puntuacion);
        }
    }
    private void actualizarPuntuacion(int idPronostico, int puntuacion) throws SQLException {
        String sql = "UPDATE pronostico SET puntuacion = ? WHERE idPronostico = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, puntuacion);
        ps.setInt(2, idPronostico);
        ps.executeUpdate();
        
        actualizarPuntuacionUsuario(idPronostico, puntuacion);
    }
    
    private void actualizarPuntuacionUsuario(int idPronostico, int puntuacion) throws SQLException {
        // Obtener el idUsuario correspondiente al pronóstico
        String sqlUsuario = "SELECT idUsuario FROM pronostico WHERE idPronostico = ?";
        PreparedStatement psUsuario = con.prepareStatement(sqlUsuario);
        psUsuario.setInt(1, idPronostico);
        ResultSet rs = psUsuario.executeQuery();
        
        if (rs.next()) {
            int idUsuario = rs.getInt("idUsuario");
            
            // Actualizar los puntos totales del usuario en la tabla usuario
            String sqlActualizarUsuario = "UPDATE usuario SET puntuacion = puntuacion + ? WHERE idUsuario = ?";
            PreparedStatement psActualizarUsuario = con.prepareStatement(sqlActualizarUsuario);
            psActualizarUsuario.setInt(1, puntuacion);
            psActualizarUsuario.setInt(2, idUsuario);
            psActualizarUsuario.executeUpdate();
        }
        
        // Cerrar recursos
        psUsuario.close();
    }

}
