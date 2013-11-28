package ar.edu.unlam.talleweb.timelineme.model;

public class Empresa {

	public Integer id;
	public String nombre;
	public String razon;
	
	
	public Empresa() {
		super();
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
	
	public String getRazon() {
		return this.razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}


	
}
