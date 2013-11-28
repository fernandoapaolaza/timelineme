package ar.edu.unlam.talleweb.timelineme.persistence;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Publicacion;


public interface PublicacionDao {
	public void insert(Publicacion publicacion) throws PersistenceException; 
    
    public void delete(Publicacion publicacion) throws PersistenceException;
    
    public Publicacion findById(Integer idPublicacion) throws PersistenceException;
    
    public List<Publicacion> findAllByEmpresa(Integer idEmpresa) throws PersistenceException;
	
}
