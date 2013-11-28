package ar.edu.unlam.talleweb.timelineme.model;

public class Agente {

	public Integer id;
	public String nombre;
	public String username;
	public String password;
	public Integer idempresa;
	
	
	public Agente() {
		super();
	}

	public String getFullName() {
		return this.nombre;
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
	
	public Integer getIdempresa() {
		return this.idempresa;
	}
	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}
}
