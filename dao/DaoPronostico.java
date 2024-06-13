package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
