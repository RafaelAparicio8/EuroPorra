package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Usuario;

public class DaoUsuario {
	
	private Connection con = null;
	
	public DaoUsuario () throws SQLException {
		
		con = DBConnection.getConnection();
	}
	

	public void insertar(Usuario u) throws SQLException {
		
		
		String sql = "INSERT  usuario (nombre, contrasena,permiso) VALUES (?,?,?)";
	    PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getNombre());
		ps.setString(2, u.getContrasena());
		ps.setInt(3,  u.getPermiso());
		
		
		int filas =  ps.executeUpdate();
		System.out.println(filas);
		ps.close();
		
	}
	
	/**
	 * Metodo para listar usuarios:
	 */
public ArrayList<Usuario> listar() throws SQLException {
		
		
		String sql =   "SELECT  * FROM usuario";
		
		PreparedStatement ps = con.prepareStatement(sql);
		  
		ResultSet result = ps.executeQuery();
		
		ArrayList<Usuario> usuarios = null;
		
		
		while(result.next()) {
			if (usuarios == null) {
				
				usuarios = new ArrayList<Usuario>();	
			}
			
			usuarios.add(new Usuario(result.getInt("idUsuario"),result.getString("nombre"),result.getString("contrasena"),result.getInt("puntuacion"),result.getInt("permiso")));
					
		}
	
		return usuarios;
	
	}
	
	public String listarJson() throws SQLException {
		
		String txtJSON = "";
		
		Gson gson = new Gson();
		
		txtJSON = gson.toJson(this.listar());
		
		return txtJSON;
		

	}

	public void editar(Usuario u) throws SQLException {
		
		 String sql = "UPDATE usuario SET nombre=?,contrasena=?, puntuacion=?, permiso=? WHERE idUsuario=?";
		    PreparedStatement ps = con.prepareStatement(sql);
		    ps.setString(1, u.getNombre());
		    ps.setString(2, u.getContrasena());
		    ps.setInt(3, u.getPuntuacion());
		    ps.setInt(4, u.getPermiso());
		    ps.setInt(5, u.getIdUsuario());
		    
		    System.out.println("ID Usuario: " + u.getIdUsuario());
		    System.out.println("Nombre: " + u.getNombre());
            System.out.println("contrasena: " + u.getContrasena());
            System.out.println("Puntuacion: " + u.getPuntuacion());
            System.out.println("Permiso: " + u.getPermiso());
            
			
			int filas = ps.executeUpdate();
			System.out.println("Filas actualizadas: " + filas);

	        ps.close();
	}


	public Usuario obtenerPorId(int idUsuario) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE idUsuario=?";
		PreparedStatement ps  = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4), rs.getInt(5));
		return u;
	}
	
	public  Usuario logeando(Usuario u, String contrasena) throws SQLException{
		
		 String sql = "SELECT * FROM usuario WHERE nombre=? AND contrasena=?";
		    try (PreparedStatement ps = con.prepareStatement(sql)) {
		        ps.setString(1, u.getNombre());
		        ps.setString(2, contrasena);

		        try (ResultSet rs = ps.executeQuery()) {
		            if (rs.next()) {
		                return new Usuario(
		                    rs.getInt("idUsuario"),
		                    rs.getString("nombre"),
		                    rs.getString("contrasena"),
		                    rs.getInt("puntuacion"),
		                    rs.getInt("permiso")
		                );
		            }
		        }
		    }
		    return null;

	}
	public Usuario obtenerPorDatos(String nombre, String contrasena) throws SQLException {
	    String sql = "SELECT * FROM usuario WHERE nombre=? AND contrasena=?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, nombre);
	        ps.setString(2, contrasena);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return new Usuario(
	                    rs.getInt("idUsuario"),
	                    rs.getString("nombre"),
	                    rs.getString("contrasena"),
	                    rs.getInt("puntuacion"),
	                    rs.getInt("permiso")
	                );
	            }
	        }
	    }
	    return null;
	}
	
	/**
	 * Metodo para listar usuarios de mayor a menor puntuación (clasificación):
	 */
	public ArrayList<Usuario> listarPuntuacion() throws SQLException {
	    String sql = "SELECT * FROM usuario ORDER BY puntuacion DESC";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet result = ps.executeQuery();
	    
	    ArrayList<Usuario> usuarios = new ArrayList<>();
	    
	    while (result.next()) {
	        int idUsuario = result.getInt("idUsuario");
	        String nombre = result.getString("nombre");
	        String contrasena = result.getString("contrasena");
	        int puntuacion = result.getInt("puntuacion");
	        int permiso = result.getInt("permiso");
	        
	        Usuario usuario = new Usuario(idUsuario, nombre, contrasena, puntuacion, permiso);
	        usuarios.add(usuario);
	        System.out.println("ID: " + result.getInt("idUsuario") + ", Puntuacion: " + result.getInt("puntuacion"));

	    }
	    
	    return usuarios;
	}

public void eliminar(int idUsuario) throws SQLException {
      
         String sql = "DELETE FROM usuario WHERE idUsuario=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, idUsuario);
         
         int filas = ps.executeUpdate();
         System.out.println("Usuarios eliminados: "+ filas);
         ps.close();
      }
         
}
