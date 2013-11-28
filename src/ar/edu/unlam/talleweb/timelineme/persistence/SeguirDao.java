package ar.edu.unlam.talleweb.timelineme.persistence;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;




public interface SeguirDao {
	
	public Seguir findById(Integer id) throws PersistenceException;
    
    public List<Seguir> findFollow(Integer idEmpresa) throws PersistenceException;

    public Boolean sigue(Integer idAgente, Integer idEmpresa) throws PersistenceException;
    
    public void insert(Seguir seguir) throws PersistenceException;
    
    public void delete(Seguir seguir) throws PersistenceException;
}