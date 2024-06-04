package modelo;

import java.sql.SQLException;


import dao.DaoSelecciones;

public class Seleccion {
	
	private int idSeleccion;//no mostrar
	private String nombre;
	private String grupo;// no mostrar
	private int gf;
	private int gc;
	private int dif;
	private int puntos;
	
	
	public Seleccion() {
		
	}


	public Seleccion(int idSeleccion, String nombre, String grupo, int gf, int gc, int dif, int puntos) {
		super();
		this.idSeleccion = idSeleccion;
		this.nombre = nombre;
		this.grupo = grupo;
		this.gf = gf;
		this.gc = gc;
		this.dif = dif;
		this.puntos = puntos;
	}


	public int getIdSeleccion() {
		return idSeleccion;
	}


	public void setIdSeleccion(int idSeleccion) {
		this.idSeleccion = idSeleccion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public int getGf() {
		return gf;
	}


	public void setGf(int gf) {
		this.gf = gf;
	}


	public int getGc() {
		return gc;
	}


	public void setGc(int gc) {
		this.gc = gc;
	}


	public int getDif() {
		return dif;
	}


	public void setDif(int dif) {
		this.dif = dif;
	}


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	@Override
	public String toString() {
		return "Seleccion [idSeleccion=" + idSeleccion + ", nombre=" + nombre + ", grupo=" + grupo + ", gf=" + gf
				+ ", gc=" + gc + ", dif=" + dif + ", puntos=" + puntos + "]";
	}
	
	
	
	
}
	
	
	
	

