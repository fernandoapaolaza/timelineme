package ar.edu.unlam.talleweb.timelineme.persistence;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Persona;




public interface PersonaDao {

    public void insert(Persona persona) throws PersistenceException;
    
    public void delete(Persona persona) throws PersistenceException;
    
    public void update(Persona persona) throws PersistenceException;
    
    public Persona findById(Integer idPersona) throws PersistenceException;
    
    public List<Persona> findAll() throws PersistenceException;

	public Persona findByName(String username) throws PersistenceException;
    
}
