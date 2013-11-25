package ar.edu.unlam.talleweb.timelineme.model;

public class Persona {

	private Integer id;
	private String nombre;
	private String apellido;
	private Integer edad;
	public String username;
	public String password;
	

	public Persona() {
		super();
	}

	public String getFullName() {
		return this.nombre + " " + this.apellido;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return this.apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getEdad() {
		return this.edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String string) {
		this.password = string;
	}

}
