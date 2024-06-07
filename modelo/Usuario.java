package modelo;

import java.sql.SQLException;


import dao.DaoUsuario;

public class Usuario {
	
	private int idUsuario;
	private String nombre;
	private String contrasena;
	private int puntuacion;
	private int permiso;
	
	
	/**
	 * Constructor vacio
	 */
	public Usuario () {
		
	}

   
	/**
	 * Constructor sin id ni permiso.
	 */
	public Usuario(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		
	}


	
	/**
	 * Constructor completo
	 */
	public Usuario(int idUsuario, String nombre, String contrasena, int puntuacion, int permiso) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.puntuacion = puntuacion;
		this.permiso = permiso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}
	
	/**
	 * metodo para insertar en la base de datos
	 */
	public void insertar () throws SQLException {
			DaoUsuario dao = new DaoUsuario();
			dao.insertar(this);
	}
	
	/**
	 * metodo para editar en la base de datos
	 */
	public void editar() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.editar(this);
	}
	
	public void obtenerPorId(int id) throws SQLException {
		
		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.obtenerPorId (idUsuario);
		
			this.setIdUsuario(aux.getIdUsuario());
			this.setNombre(aux.getNombre());
			this.setContrasena(aux.getContrasena());
			this.setPermiso(aux.getPermiso());
			this.setPuntuacion(aux.getPuntuacion());
		
	}
	//metodo para devolver el IdUsuario para editar usuarios.
	
	public Integer getId() {
	    return idUsuario; 
	}
	
	public boolean logeo(String contrasena) throws SQLException {
		
		boolean ok = false;
		
		DaoUsuario dao = new DaoUsuario();
		Usuario aux = dao.logeando(this, contrasena);
		
		if (aux != null && aux.getContrasena().equals(contrasena)){
			this.setIdUsuario(aux.getIdUsuario());
			this.setNombre(aux.getNombre());
			this.setContrasena(aux.getContrasena());
			this.setPermiso(aux.getPermiso());
			this.setPuntuacion(aux.getPuntuacion());
			ok=true;
		}
		
		
		return ok;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", contrasena=" + contrasena + ", puntuacion="
				+ puntuacion + ", permiso=" + permiso + "]";
	}	

}
