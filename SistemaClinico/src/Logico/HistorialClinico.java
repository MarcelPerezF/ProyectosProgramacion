package Logico;

import java.util.ArrayList;

public class HistorialClinico {
	private String codigoHistorial;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	
	public HistorialClinico(String codigoHistorial) {
		super();
		this.codigoHistorial = codigoHistorial;
		this.misConsultas = new ArrayList<Consulta>();
		this.misVacunas = new ArrayList<Vacuna>();
	}

	public String getCodigoHistorial() {
		return codigoHistorial;
	}

	public void setCodigoHistorial(String codigoHistorial) {
		this.codigoHistorial = codigoHistorial;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public void ingresarVacuna(Vacuna vacuna) {
		misVacunas.add(vacuna);
	}
	
	public void ingresarConsulta(Consulta consulta) {
		misConsultas.add(consulta);
	}

}
