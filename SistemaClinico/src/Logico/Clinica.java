package Logico;

import java.util.ArrayList;


public class Clinica {
	
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<CitaMedica> misCitasMedicas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	public static Clinica instanciaGlobal = null;
	
	public int generadorCodigoUsuario;
	public int generadorCodigoPaciente;
	
	private Clinica() {
		super();
		this.misPacientes = new ArrayList<Paciente>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.misCitasMedicas = new ArrayList<CitaMedica>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
		
		this.generadorCodigoUsuario = 1;
		this.generadorCodigoPaciente = 1;
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
	
	public int getGeneradorCodigoUsuario() {
		return generadorCodigoUsuario;
	}

	public int getGeneradorCodigoPaciente() {
		return generadorCodigoPaciente;
	}
	
	public void insertarUsuario(Usuario usuario) {
		misUsuarios.add(usuario);
		generadorCodigoUsuario++;
	}


	public  void modificarUsuario(Usuario usuarioModificar) {
		int indiceUsuario = buscarIndiceUsuario(usuarioModificar.getCedulaUsuario());
		misUsuarios.set(indiceUsuario, usuarioModificar);	
	}
	
	public void insertarPaciente(Paciente paciente) {
		misPacientes.add(paciente);
		generadorCodigoPaciente++;
	}
	
	public void modificarPaciente(Paciente pacienteModificar) {
		int indicePaciente  = buscarIndicePaciente(pacienteModificar.getCedula());
		misPacientes.set(indicePaciente, pacienteModificar);	
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
	
	public int buscarIndiceUsuario(String cedula) {
		int indiceUsuario = -1;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misUsuarios.size()) {
			if(misUsuarios.get(indexBuscador).getCedulaUsuario().equalsIgnoreCase(cedula)) {				
				indiceUsuario = indexBuscador;
				encontrado = true;				
			}
			indexBuscador++;
		}
		return indiceUsuario;
	}
	
	private int buscarIndicePaciente(String cedulaPaciente) {
		int indicePaciente = -1;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misPacientes.size()) {
			if(misPacientes.get(indexBuscador).getCedula().equalsIgnoreCase(cedulaPaciente)) {				
				indicePaciente = indexBuscador;
				encontrado = true;				
			}
			indexBuscador++;
		}
		return indicePaciente;
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

	public boolean usuarioRepetido(String usuario) {
		boolean aux = true;
		boolean encontrado = false;
		int indexBuscador=0;
		
		while (!encontrado && indexBuscador<misUsuarios.size()) {
			if(misUsuarios.get(indexBuscador).getUsuario().equalsIgnoreCase(usuario)) {				
				aux = false;
				encontrado = true;				
			}
			indexBuscador++;
		}
		return aux;
	}

	public String tipoUsuario(Usuario usuario) {
		String tipoUsuario = "";
		if(usuario instanceof Empleado) {
			Empleado empleado = (Empleado)usuario;
			tipoUsuario = empleado.getPuestoLaboral();
		}
		if(usuario instanceof Medico) {
			tipoUsuario = "Medico";
		}
		return tipoUsuario;
	}

	public String generarCodigoEnfermedad() {
		String codigo="";
		codigo = "E-"+(misEnfermedades.size()+1);
		return codigo;
	}
	
	public void actualizarEnfermedad(Enfermedad auxEnf) {
		int y=0, i=0;
		for(Enfermedad enfermedad : misEnfermedades) {
			if(enfermedad.getCodigoEnfermedad().equalsIgnoreCase(auxEnf.getCodigoEnfermedad())) {
				y=i;
			}
			i++;
		}
		misEnfermedades.set(y, auxEnf);
	}
	
	public String generarCodigoCita() {
		String codigo="";
		codigo = "C-"+(misCitasMedicas.size()+1);
		return codigo;
	}
	
	public String generarCodigoVacuna() {
		String codigo="";
		codigo = "V-"+(misVacunas.size()+1);
		return codigo;
	}
	
	public void ingresarConsultaPaciente(Paciente paciente, Consulta consulta) {
		paciente.insertarConsulta(consulta);
	}
	
	public void ingresarConsultaPacienteHistorial(Paciente paciente, Consulta consulta) {
		paciente.getHistorial().ingresarConsulta(consulta);
	}
	
	public void ingresarVacunaPacienteHistorial(Paciente paciente, Vacuna vacuna) {
		paciente.getHistorial().ingresarVacuna(vacuna);
	}
	
}
