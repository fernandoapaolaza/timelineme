package ar.edu.unlam.talleweb.timelineme.model;

import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.AgenteService;
import ar.edu.unlam.talleweb.timelineme.services.EmpresaService;

public class Seguir {

	public Integer id;
	public Integer idseguidor;
	public Integer idempresaseguida;
	public String fecha;
	public Empresa empresa;
	
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

	public Empresa getEmpresa() {
		return this.empresa;
	}
	public void setEmpresa(Integer idEmpresa) throws PersistenceException {
		EmpresaService servicioDeEmpresa = new EmpresaService();
		Empresa ObjEmpresa = servicioDeEmpresa.findById(idEmpresa);
		this.empresa = ObjEmpresa;
	}
}