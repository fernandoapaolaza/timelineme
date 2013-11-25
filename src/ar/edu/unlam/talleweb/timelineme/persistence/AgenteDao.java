package ar.edu.unlam.talleweb.timelineme.persistence;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Agente;




public interface AgenteDao {
	public void insert(Agente agente) throws PersistenceException; 
    
    public void delete(Agente agente) throws PersistenceException;
    
    public void update(Agente agente) throws PersistenceException;
    
    public Agente findById(Integer idAgente) throws PersistenceException;
    
    public List<Agente> findAll() throws PersistenceException;

	public Agente findByName(String username) throws PersistenceException;
}
