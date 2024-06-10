package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import modelo.Jugador;
import modelo.Usuario;

public class DaoJugadores {
	
	private Connection con = null;
	
	public DaoJugadores () throws SQLException {
		
		con = DBConnection.getConnection();
	}
	

	public void insertar(Jugador j) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement
				("INSERT jugador (nombreJugador, idSeleccion, goles) VALUES (?,?,?)");
		
		ps.setString(1, j.getNombre());
		ps.setInt(2, j.getSeleccion());
		ps.setInt(3, j.getGol());
		
		int filas =  ps.executeUpdate();
		
		ps.close();
		
	}
	
	/**
	 * Metodo para listar jugadores de mas a menos goles (tabla goledores):
	 */
	public ArrayList<Jugador> listar() throws SQLException {
	    String sql = "SELECT * FROM jugador ORDER BY goles DESC";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<Jugador> jugadores = new ArrayList<>();
	    
	    while (result.next()) {
	        int id = result.getInt("idJugador");
	        String nombre = result.getString("nombreJugador");
	        String seleccion = result.getString("seleccion");
	        int goles = result.getInt("goles");
	        
	        
	        Jugador jugador = new Jugador();
	        jugadores.add(jugador);
	        System.out.println("ID: " + result.getInt("idJugador") + ", Goles: " + result.getInt("goles"));

	    }
	    
	    return jugadores;
	}
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		return txtJSON;
		

	}
	
	//obtener por id:
	
public Jugador obtenerPorId(int id) throws SQLException {
		
		String sql = "SELECT * FROM jugador WHERE idJugador=?";
		PreparedStatement ps  = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Jugador j = new Jugador(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getInt(4));
		return j;
	}


public List<Jugador> obtenerJugadoresPorSeleccion(int seleccion) throws SQLException {
    List<Jugador> jugadores = new ArrayList<>();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM jugador WHERE idSeleccion = ?");
    ps.setInt(1, seleccion);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        Jugador jugador = new Jugador(rs.getInt("idJugador"), rs.getString("nombreJugador"), rs.getInt("idSeleccion"), rs.getInt("goles"));
        jugadores.add(jugador);
    }
    
    rs.close();
    ps.close();
    return jugadores;

}

}
