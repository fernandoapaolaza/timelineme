package ar.edu.unlam.talleweb.timelineme.model;

public class Publicacion {
	public Integer id;
	public String comentario;
	public Integer idempresa;
	public Integer idagente;
	public String fecha;
	
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
}
