package ar.edu.unlam.talleweb.timelineme.services;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.persistence.DaoFactory;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDao;

public class EmpresaService {
	
	public Empresa findById(int idempresa) throws PersistenceException {
		
		EmpresaDao miEmpresaDao = DaoFactory.getEmpresaDao();
		return miEmpresaDao.findById(idempresa);
	}

	public List<Empresa> findFollow(int idempresa) throws PersistenceException {
		EmpresaDao miEmpresaDao = DaoFactory.getEmpresaDao();
		return miEmpresaDao.findFollow(idempresa);

	}
}
