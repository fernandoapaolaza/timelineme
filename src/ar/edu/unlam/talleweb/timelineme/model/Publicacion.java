package ar.edu.unlam.talleweb.timelineme.model;

import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.AgenteService;

public class Publicacion {
	public Integer id;
	public String comentario;
	public Integer idempresa;
	public Integer idagente;
	public String fecha;
	public Agente agente;
	
	public Publicacion() {
		super();
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComentario() {
		return this.comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
		
	public Integer getIdempresa() {
		return this.idempresa;
	}
	public void setIdempresa(Integer idempresa) {
		this.idempresa = idempresa;
	}
	
	public Integer getIdagente() {
		return this.idagente;
	}
	public void setIdagente(Integer idagente) {
		this.idagente = idagente;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	public Agente getAgente() {
		return this.agente;
	}
	
	public void setAgente(Integer idAgente) throws PersistenceException {
		AgenteService servicioDeAgente = new AgenteService();
		Agente ObjAgente = servicioDeAgente.findById(idAgente);
		this.agente = ObjAgente;
	}
}
