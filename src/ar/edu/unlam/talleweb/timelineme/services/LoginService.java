package ar.edu.unlam.talleweb.timelineme.services;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;

public class LoginService {

	public Boolean authenticate(String username, String password) throws PersistenceException {
		Boolean retorno = false;
		Agente usuarioActual = findByName(username);
		if (usuarioActual == null) {
			retorno = false;
		} else {
			retorno = usuarioActual.getPassword().equals(password);
		}
		return retorno;
	}
	
	public Agente findByName(String username) throws PersistenceException{
		AgenteService agenteSvc = new AgenteService();
		return agenteSvc.findByName(username);
		
	}
	
}
