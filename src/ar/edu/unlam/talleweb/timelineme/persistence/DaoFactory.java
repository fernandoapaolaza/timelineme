package ar.edu.unlam.talleweb.timelineme.persistence;

public class DaoFactory {

		
		public static AgenteDao getAgenteDao(){
			return AgenteDaoJdbcImpl.getInstance();
		}
}
