package ar.edu.unlam.talleweb.timelineme.model;

public class Seguir {

	public Integer id;
	public Integer idseguidor;
	public Integer idempresaseguida;
	public String fecha;
	
	
	public Seguir() {
		super();
	}

		
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdseguidor() {
		return this.idseguidor;
	}
	public void setIdseguidor(Integer idseguidor) {
		this.idseguidor = idseguidor;
	}
	
	public Integer getIdempresaseguida() {
		return this.idempresaseguida;
	}
	public void setIdempresaseguida(Integer idempresaseguida) {
		this.idempresaseguida = idempresaseguida;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
}