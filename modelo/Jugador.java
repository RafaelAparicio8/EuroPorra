package modelo;

import java.sql.SQLException;

import dao.DaoJugadores;
import dao.DaoUsuario;

public class Jugador {
	
	private int id;
	private String nombre;
	private int seleccion;
	private int gol;
	
	//constructor vacio
	public Jugador (){
		
	}
	//constructor sin ID
	public Jugador(String nombre, int seleccion, int gol) {
		super();
		this.nombre = nombre;
		this.seleccion = seleccion;
		this.gol = gol;
	}
	//constructor completo
	public Jugador(int id, String nombre, int seleccion, int gol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.seleccion = seleccion;
		this.gol = gol;
	}
	
	//getters % setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSeleccion() {
		return seleccion;
	}
	public void setSeleccion(int seleccion) {
		this.seleccion = seleccion;
	}
	public int getGol() {
		return gol;
	}
	public void setGol(int gol) {
		this.gol = gol;
	}
	
	
	// metodo toString
	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", seleccion=" + seleccion + ", gol=" + gol + "]";
	}
	//metodo para insertar a BD
		public void insertar () throws SQLException {
			DaoJugadores dao = new DaoJugadores();
			dao.insertar(this);
		}
	//obtener por id:
	
		public void obtenerPorId(int id) throws SQLException {
			
			DaoJugadores dao = new DaoJugadores();
			Jugador aux = dao.obtenerPorId (id);
			
				this.setId(aux.getId());
				this.setNombre(aux.getNombre());
				this.setSeleccion(aux.getSeleccion());
				this.setGol(aux.getGol());
				
				
			
		}
	//metodo para editar jugadores:
		
		    
		



	
}
