package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		ps.close();
		
	}

	public void editar(Usuario u) throws SQLException {
		
		String sql = "UPDATE usuario SET (nombre=?, permiso=?)WHERE idUsuario=?";
		 PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getContrasena());
			ps.setInt(3,  u.getPermiso());
			ps.setInt(4, u.getIdUsuario());
			
			int filas = ps.executeUpdate();
	}


	public Usuario obtenerPorId(int idUsuario) throws SQLException {
		
		String sql = "SELECT * FROM usuario WHERE idUsuario=?";
		PreparedStatement ps  = con.prepareStatement(sql);
		ps.setInt(1, idUsuario);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getInt(4), rs.getInt(5));
		return null;
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
}
