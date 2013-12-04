package ar.edu.unlam.talleweb.timelineme.persistence;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;




public interface SeguirDao {
	
	public Seguir findById(Integer id) throws PersistenceException;
    
    public List<Seguir> findFollow(Integer idAgente) throws PersistenceException;
    
    public List<Seguir> findNoFollow(Integer idAgente,Integer idEmpresa) throws PersistenceException;

    public Boolean sigue(Integer idAgente, Integer idEmpresa) throws PersistenceException;
    
    public Boolean insert(Seguir seguir) throws PersistenceException;
    
    public Boolean delete(Integer idAgente, Integer idEmpresa) throws PersistenceException;
    
     
}