package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.AdminPartido;
import modelo.Partido;


public class DaoAdminPartido {
	
private Connection con = null;
	
	public DaoAdminPartido () throws SQLException {
		
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
			
			partidos.add(new Partido(result.getInt("idPartido"),result.getInt("equipoA_id"),result.getInt("equipoB_id"),result.getInt("resultEquipoA"),result.getInt("resultEquipoB"),result.getString("nomEquipoA"),result.getString("nomEquipoB")));
					
		}
	
		return partidos;
	
	}
	
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		return txtJSON;
		

	}
	
	public Partido obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM partido WHERE idPartido = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        Partido partido = null;

        if (result.next()) {
            partido = new Partido(result.getInt("idPartido"), result.getInt("equipoA_id"), result.getInt("equipoB_id"), result.getInt("resultEquipoA"), result.getInt("resultEquipoB"), result.getString("nomEquipoA"), result.getString("nomEquipoB"));
        }

        return partido;
	}
	
	public void editar (Partido partido) throws SQLException {
		
		String sql = "UPDATE partido SET nomEquipoA=?, resultEquipoA=?, nomEquipoB=?, resultEquipoB=? WHERE idPartido=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, partido.getNomEquipoA());
		ps.setInt(2, partido.getResultEquipoA());
		ps.setString(3, partido.getNomEquipoB());
		ps.setInt(4, partido.getResultEquipoB());
		ps.setInt(5, partido.getIdPartido());
		
		int filas = ps.executeUpdate();
	}
	

}