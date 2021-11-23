package Logico;

public abstract class Usuario {
	/*
	 Clase Usuario: esta clase se utiliza como base para las clases medico y empleado.
	 Todo usuario tendra un codigo, tendra un id y tendra credenciales para entrar al sistema (usuario y password)
	 */
	
	protected String codigoUsuario;
	protected String cedulaUsuario;
	protected int idUsuario;
	protected String usuario;
	protected String password;
	protected String nombre;
	protected String telefono;
	protected String direccion;
	protected String email;
	protected String genero;
	
	public Usuario(String codigoUsuario, String cedulaUsuario, int idUsuario, String usuario, String password, String nombre, String telefono,
			String direccion, String email, String genero) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.cedulaUsuario = cedulaUsuario;
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.password = password;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.genero = genero;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}	

}
