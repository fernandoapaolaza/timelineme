package ar.edu.unlam.talleweb.timelineme.services;

import ar.edu.unlam.talleweb.timelineme.model.Persona;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;

public class LoginService {

	public Boolean authenticate(String username, String password) throws PersistenceException {
		Boolean retorno = false;
		Persona usuarioActual = findByName(username);
		if (usuarioActual == null) {
			retorno = false;
		} else {
			retorno = usuarioActual.getPassword().equals(password);
		}
		return retorno;
	}
	
	public Persona findByName(String username) throws PersistenceException{
		PersonaService personaSvc = new PersonaService();
		return personaSvc.findByName(username);
		
	}
	
}
