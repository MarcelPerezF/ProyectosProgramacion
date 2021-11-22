package Logico;

public class Vacuna {
	private String codigoVacunacion;
	private String nombreVacunacion;
	private int cantidadVacunas;
	private String tipoVacuna;
	private String descripcionVacuna;
	
	public Vacuna(String codigoVacunacion, String nombreVacunacion, int cantidadVacunas, String tipoVacuna,
			String descripcionVacuna) {
		super();
		this.codigoVacunacion = codigoVacunacion;
		this.nombreVacunacion = nombreVacunacion;
		this.cantidadVacunas = cantidadVacunas;
		this.tipoVacuna = tipoVacuna;
		this.descripcionVacuna = descripcionVacuna;
	}
	
	public String getCodigoVacunacion() {
		return codigoVacunacion;
	}
	public void setCodigoVacunacion(String codigoVacunacion) {
		this.codigoVacunacion = codigoVacunacion;
	}
	public String getNombreVacunacion() {
		return nombreVacunacion;
	}
	public void setNombreVacunacion(String nombreVacunacion) {
		this.nombreVacunacion = nombreVacunacion;
	}
	public int getCantidadVacunas() {
		return cantidadVacunas;
	}
	public void setCantidadVacunas(int cantidadVacunas) {
		this.cantidadVacunas = cantidadVacunas;
	}
	public String getTipoVacuna() {
		return tipoVacuna;
	}
	public void setTipoVacuna(String tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}
	public String getDescripcionVacuna() {
		return descripcionVacuna;
	}
	public void setDescripcionVacuna(String descripcionVacuna) {
		this.descripcionVacuna = descripcionVacuna;
	}
	
}
