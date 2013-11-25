package ar.edu.unlam.talleweb.timelineme.persistence;

public class DaoFactory {

	public static PersonaDao getPersonaDao(){
		return PersonaDaoJdbcImpl.getInstance();
	}

}
