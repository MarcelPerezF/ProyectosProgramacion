package Logico;

import java.util.ArrayList;

public class Clinica {
	
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<CitaMedica> misCitasMedicas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	public static Clinica instanciaGlobal = null;

	
	private Clinica() {
		super();
		this.misPacientes = new ArrayList<Paciente>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.misCitasMedicas = new ArrayList<CitaMedica>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
	}
	
	//Metodo del patron Singleton
	public static Clinica getInstance() {
		if(instanciaGlobal==null) {
			instanciaGlobal=new Clinica();
		}		
		return instanciaGlobal;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public ArrayList<CitaMedica> getMisCitasMedicas() {
		return misCitasMedicas;
	}

	public void setMisCitasMedicas(ArrayList<CitaMedica> misCitasMedicas) {
		this.misCitasMedicas = misCitasMedicas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}	
	
	public void insertarUsuario(Usuario usuario) {
		misUsuarios.add(usuario);
	}
	
	public void insertarPaciente(Paciente paciente) {
		misPacientes.add(paciente);
	}
	
	public void insertarCitasMedicas(CitaMedica cita) {
		misCitasMedicas.add(cita);
	}
	
	public void insertarEnfermedades(Enfermedad enfermedad) {
		misEnfermedades.add(enfermedad);
	}
	
	public void insertarVacuna(Vacuna vacuna) {
		misVacunas.add(vacuna);
	}
	
	public Usuario buscarUsuario(String cedula) {
		Usuario usuario = null;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misUsuarios.size()) {
			if(misUsuarios.get(indexBuscador).getCedulaUsuario().equalsIgnoreCase(cedula)) {				
				usuario=misUsuarios.get(indexBuscador);
				encontrado = true;				
			}
			indexBuscador++;
		}
		return usuario;
	}
	
	public Paciente buscarPaciente(String cedula) {
		Paciente paciente = null;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misPacientes.size()) {
			if(misPacientes.get(indexBuscador).getCedula().equalsIgnoreCase(cedula)) {				
				paciente=misPacientes.get(indexBuscador);
				encontrado = true;				
			}
			indexBuscador++;
		}
		return paciente;
	}
	
	public CitaMedica buscarCitaMedica(String cedulaPaciente) {
		CitaMedica cita = null;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misCitasMedicas.size()) {
			if(misCitasMedicas.get(indexBuscador).getCedulaPersona().equalsIgnoreCase(cedulaPaciente)) {				
				cita=misCitasMedicas.get(indexBuscador);
				encontrado = true;				
			}
			indexBuscador++;
		}
		return cita;
	}
	
	public Enfermedad buscarEnfermedad(String codigo) {
		Enfermedad enfermedad = null;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misEnfermedades.size()) {
			if(misEnfermedades.get(indexBuscador).getCodigoEnfermedad().equalsIgnoreCase(codigo)) {				
				enfermedad=misEnfermedades.get(indexBuscador);
				encontrado = true;				
			}
			indexBuscador++;
		}
		return enfermedad;
	}
	
	public Vacuna buscarVacuna(String codigo) {
		Vacuna vacuna = null;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misVacunas.size()) {
			if(misVacunas.get(indexBuscador).getCodigoVacunacion().equalsIgnoreCase(codigo)) {				
				vacuna=misVacunas.get(indexBuscador);
				encontrado = true;				
			}
			indexBuscador++;
		}
		return vacuna;
	}
}
