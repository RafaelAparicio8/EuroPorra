package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Jugador;

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

}
