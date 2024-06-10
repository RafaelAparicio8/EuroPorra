package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Partido;


public class DaoPartido {
	
	private Connection con = null;
	
	public DaoPartido () throws SQLException {
		
		con = DBConnection.getConnection();
	}
	
public ArrayList<Partido> listar() throws SQLException {
		
		
		String sql =   "SELECT  * FROM partido";
		
		PreparedStatement ps = con.prepareStatement(sql);
		  
		ResultSet result = ps.executeQuery();
		
		ArrayList<Partido> partidos = null;
		
		
		while(result.next()) {
			if (partidos == null) {
				
				partidos= new ArrayList<Partido>();	
			}
			
			partidos.add(new Partido(result.getInt("idPartido"),result.getInt("equipoA_id"),result.getInt("equipoB_id"),result.getInt("resultEquipoA"),result.getInt("resultEquipoB"),result.getString("nomEquipoA"),result.getString("nomEquipoB"),result.getString("ganador")));
					
		}
	
		return partidos;
	
	}
	
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		return txtJSON;
		

	}
	

}
