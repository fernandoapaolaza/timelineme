package ar.edu.unlam.talleweb.timelineme.services;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Seguir;
import ar.edu.unlam.talleweb.timelineme.persistence.DaoFactory;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.SeguirDao;

public class SeguirService {
	
	public List<Seguir> findFollow(int idagente) throws PersistenceException {
		SeguirDao miSeguirDao = DaoFactory.getSeguirDao();
		return miSeguirDao.findFollow(idagente);
	}
	
	public List<Seguir> findNoFollow(int idagente,int idempresa) throws PersistenceException {
		SeguirDao miSeguirDao = DaoFactory.getSeguirDao();
		return miSeguirDao.findNoFollow(idagente,idempresa);
	}
}
