package ar.edu.unlam.talleweb.timelineme.services;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.persistence.DaoFactory;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.AgenteDao;

public class AgenteService {
	
	public Agente findByName(String username) throws PersistenceException {
		
		AgenteDao miAgenteDao = DaoFactory.getAgenteDao();
		return miAgenteDao.findByName(username);
	}
	
	public Agente findById(Integer idagente) throws PersistenceException {
		
		AgenteDao miAgenteDao = DaoFactory.getAgenteDao();
		return miAgenteDao.findById(idagente);
	}

	public List<Agente> findAll() throws PersistenceException {
		AgenteDao miAgenteDao = DaoFactory.getAgenteDao();
		return miAgenteDao.findAll();

	}
}
