package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Paciente {
	private String codigoPaciente;
	private String cedula;
	private String nombre;
	private String genero;
	private Date diaNacimiento;
	private String direccion;
	private String telefono;
	private String email;
	private String alergia;
	private String nacionalidad;
	private String estadoCivil;
	private String religion;
	private String tipoSangre;
	private String profesion;
	private HistorialClinico historial;
	private ArrayList<Consulta> misConsultas;
	
	public Paciente(String codigoPaciente, String cedula, String nombre, String genero, Date diaNacimiento, String direccion, String telefono,
			String email, String alergia, String nacionalidad, String estadoCivil, String religion, String tipoSangre,
			String profesion) {
		super();
		this.codigoPaciente = codigoPaciente;
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.diaNacimiento = diaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.alergia = alergia;
		this.nacionalidad = nacionalidad;
		this.estadoCivil = estadoCivil;
		this.religion = religion;
		this.tipoSangre = tipoSangre;
		this.profesion = profesion;
		this.historial = new HistorialClinico("H-"+codigoPaciente);
		//Ejemplo: H-P-2//Historial Paciente 2		
		this.misConsultas = new ArrayList<Consulta>();
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getDiaNacimiento() {
		return diaNacimiento;
	}
	public void setDiaNacimiento(Date diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlergia() {
		return alergia;
	}
	public void setAlergia(String alergia) {
		this.alergia = alergia;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}
	public HistorialClinico getHistorial() {
		return historial;
	}
	public void setHistorial(HistorialClinico historial) {
		this.historial = historial;
	}
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	public String getCodigoPaciente() {
		return codigoPaciente;
	}
	public void setCodigoPaciente(String codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}
	
	public void insertarConsulta(Consulta consulta) {
		misConsultas.add(consulta);
	}
	
}
