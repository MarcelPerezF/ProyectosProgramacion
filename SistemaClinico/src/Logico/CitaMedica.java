package Logico;

import java.util.Date;

public class CitaMedica {
	
	private String codigoCita;
	private Date fechaCita;
	private String nombrePersona;
	private String telefonoPersona;
	private String cedulaPersona;
	private String estadoCita;
	private Usuario medico;
	private Usuario creadorCita;
	
	public CitaMedica(String codigoCita, String nombrePersona, String cedulaPersona, String telefonoPersona, Usuario medico,
			Usuario creadorCita, Date fechaCita) {
		super();
		this.codigoCita = codigoCita;
		this.fechaCita = fechaCita;
		this.nombrePersona = nombrePersona;
		this.telefonoPersona = telefonoPersona;
		this.cedulaPersona = cedulaPersona;
		this.estadoCita = "En espera";
		this.medico = medico;
		this.creadorCita = creadorCita;
	}

	public String getCodigoCita() {
		return codigoCita;
	}

	public void setCodigoCita(String codigoCita) {
		this.codigoCita = codigoCita;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public String getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}

	public Usuario getMedico() {
		return medico;
	}

	public void setMedico(Usuario medico) {
		this.medico = medico;
	}

	public Usuario getCreadorCita() {
		return creadorCita;
	}

	public void setCreadorCita(Usuario creadorCita) {
		this.creadorCita = creadorCita;
	}

	public String getTelefonoPersona() {
		return telefonoPersona;
	}

	public void setTelefonoPersona(String telefonoPersona) {
		this.telefonoPersona = telefonoPersona;
	}
		
}
