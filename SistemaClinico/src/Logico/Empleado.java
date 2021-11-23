package Logico;

public class Empleado extends Usuario {
	private String puestoLaboral;

	public Empleado(String codigoUsuario, String cedulaUsuario, int idUsuario, String usuario, String password, String nombre,
			String telefono, String direccion, String email, String genero, String puestoLaboral) {
		super(codigoUsuario, cedulaUsuario, idUsuario, usuario, password, nombre, telefono, direccion, email, genero);
		this.puestoLaboral = puestoLaboral;
	}

	public String getPuestoLaboral() {
		return puestoLaboral;
	}

	public void setPuestoLaboral(String puestoLaboral) {
		this.puestoLaboral = puestoLaboral;
	}	

}
