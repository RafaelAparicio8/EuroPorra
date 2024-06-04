
package modelo;

import java.sql.SQLException;

import dao.DaoAdminPartido;

public class AdminPartido {
	
	private int idPartido;
	private int equipoA_id;
	private int equipoB_id;
	private int resultEquipoA;
	private int resultEquipoB;
	private String nomEquipoA;
	private String nomEquipoB;
	
	public AdminPartido() {
		
	}

	public AdminPartido(int idPartido, int equipoA_id, int equipoB_id, int resultEquipoA, int resultEquipoB,
			String nomEquipoA, String nomEquipoB) {
		super();
		this.idPartido = idPartido;
		this.equipoA_id = equipoA_id;
		this.equipoB_id = equipoB_id;
		this.resultEquipoA = resultEquipoA;
		this.resultEquipoB = resultEquipoB;
		this.nomEquipoA = nomEquipoA;
		this.nomEquipoB = nomEquipoB;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public int getEquipoA_id() {
		return equipoA_id;
	}

	public void setEquipoA_id(int equipoA_id) {
		this.equipoA_id = equipoA_id;
	}

	public int getEquipoB_id() {
		return equipoB_id;
	}

	public void setEquipoB_id(int equipoB_id) {
		this.equipoB_id = equipoB_id;
	}

	public int getResultEquipoA() {
		return resultEquipoA;
	}

	public void setResultEquipoA(int resultEquipoA) {
		this.resultEquipoA = resultEquipoA;
	}

	public int getResultEquipoB() {
		return resultEquipoB;
	}

	public void setResultEquipoB(int resultEquipoB) {
		this.resultEquipoB = resultEquipoB;
	}

	public String getNomEquipoA() {
		return nomEquipoA;
	}

	public void setNomEquipoA(String nomEquipoA) {
		this.nomEquipoA = nomEquipoA;
	}

	public String getNomEquipoB() {
		return nomEquipoB;
	}

	public void setNomEquipoB(String nomEquipoB) {
		this.nomEquipoB = nomEquipoB;
	}
	
	public void editar() throws SQLException {
		
		/*DaoAdminPartido dao = new DaoAdminPartido();
		dao.editar(this);*/
		
		
	}

	@Override
	public String toString() {
		return "AdminPartido [idPartido=" + idPartido + ", equipoA_id=" + equipoA_id + ", equipoB_id=" + equipoB_id
				+ ", resultEquipoA=" + resultEquipoA + ", resultEquipoB=" + resultEquipoB + ", nomEquipoA=" + nomEquipoA
				+ ", nomEquipoB=" + nomEquipoB + "]";
	}
	
	

}