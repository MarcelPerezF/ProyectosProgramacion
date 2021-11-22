package Logico;

public class Medico extends Usuario {

	private String especialidad;

	public Medico(String codigoUsuario, int idUsuario, String usuario, String password, String nombre, String telefono,
			String direccion, String email, String genero, String especialidad) {
		super(codigoUsuario, idUsuario, usuario, password, nombre, telefono, direccion, email, genero);
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
