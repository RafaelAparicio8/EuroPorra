package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import modelo.Seleccion;

public class DaoSelecciones {
	
	private Connection con = null;
	
	public DaoSelecciones () throws SQLException {
		
		con = DBConnection.getConnection();

}
	
	//metodo par listar las selecciones para la tabla de clasificacion por grupos
	
	public ArrayList<Seleccion> listar() throws SQLException {
		
		
		String sql =   "SELECT  * FROM seleccion";
		
		PreparedStatement ps = con.prepareStatement(sql);
		  
		ResultSet result = ps.executeQuery();
		
		ArrayList<Seleccion> selecciones = null;
		
		
		while(result.next()) {
			if (selecciones == null) {
				
				selecciones= new ArrayList<Seleccion>();	
			}
			
			selecciones.add(new Seleccion(result.getInt("idSeleccion"),result.getString("nombre"),result.getString("grupo"),result.getInt("gf"),result.getInt("gc"),result.getInt("dif"),result.getInt("puntos")));
					
		}
	
		return selecciones;
	
	}
	
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		return txtJSON;
		

	}
	

}


