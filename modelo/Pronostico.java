package modelo;

import java.sql.SQLException;

public class Pronostico {
	 
    private int idPronostico;
    private int idUsuario;
    private int idPartido;
    private String nomEquipoA;
    private int resultPronA;
    private String nomEquipoB;
    private int resultPronB;
    private String ganador;
    
	public Pronostico() {
	}

	
	public Pronostico(int idPronostico, int idUsuario, int idPartido, String nomEquipoA, int resultPronA,
			String nomEquipoB, int resultPronB, String ganador) {
		super();
		this.idPronostico = idPronostico;
		this.idUsuario = idUsuario;
		this.idPartido = idPartido;
		this.nomEquipoA = nomEquipoA;
		this.resultPronA = resultPronA;
		this.nomEquipoB = nomEquipoB;
		this.resultPronB = resultPronB;
		this.ganador = ganador;
	}


	public int getIdPronostico() {
		return idPronostico;
	}


	public void setIdPronostico(int idPronostico) {
		this.idPronostico = idPronostico;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public int getIdPartido() {
		return idPartido;
	}


	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}


	public String getNomEquipoA() {
		return nomEquipoA;
	}


	public void setNomEquipoA(String nomEquipoA) {
		this.nomEquipoA = nomEquipoA;
	}


	public int getResultPronA() {
		return resultPronA;
	}


	public void setResultPronA(int resultPronA) {
		this.resultPronA = resultPronA;
	}


	public String getNomEquipoB() {
		return nomEquipoB;
	}


	public void setNomEquipoB(String nomEquipoB) {
		this.nomEquipoB = nomEquipoB;
	}


	public int getResultPronB() {
		return resultPronB;
	}


	public void setResultPronB(int resultPronB) {
		this.resultPronB = resultPronB;
	}


	public String getGanador() {
		return ganador;
	}


	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	

	@Override
	public String toString() {
		return "Pronostico [idPronostico=" + idPronostico + ", idUsuario=" + idUsuario + ", idPartido=" + idPartido
				+ ", nomEquipoA=" + nomEquipoA + ", resultPronA=" + resultPronA + ", nomEquipoB=" + nomEquipoB
				+ ", resultPronB=" + resultPronB + ", ganador=" + ganador + "]";
	}


	public void obtenerGanador() {
        if (this.resultPronA > this.resultPronB) {
            this.ganador = this.nomEquipoA;
        } else if (this.resultPronA < this.resultPronB) {
            this.ganador = this.nomEquipoB;
        } else {
            this.ganador = "Empate";
        }
    }

    
}
	
