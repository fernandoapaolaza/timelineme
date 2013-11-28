package ar.edu.unlam.talleweb.timelineme.persistence;

import java.util.List;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;




public interface EmpresaDao {
	
    
    public Empresa findById(Integer idEmpresa) throws PersistenceException;
    
    public List<Empresa> findFollow(Integer idEmpresa) throws PersistenceException;

	
}