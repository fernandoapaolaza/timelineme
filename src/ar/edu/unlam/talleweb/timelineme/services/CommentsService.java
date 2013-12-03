package ar.edu.unlam.talleweb.timelineme.services;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.persistence.AgenteDao;
import ar.edu.unlam.talleweb.timelineme.persistence.DaoFactory;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.PublicacionDao;

public class CommentsService {
	
	public List<Publicacion> findAllByEmpresa(Integer idempresa) throws PersistenceException {
		PublicacionDao miCommentsDao = DaoFactory.getPublicacionDao();
		return miCommentsDao.findAllByEmpresa(idempresa);

	}
	
	public void insert(Publicacion publicacion) throws PersistenceException{
		return;
	}
	
	public List<Publicacion> findAllComments(Integer idagente) throws PersistenceException {
		PublicacionDao miCommentsDao = DaoFactory.getPublicacionDao();
		return miCommentsDao.findAllComments(idagente);

	}
}
