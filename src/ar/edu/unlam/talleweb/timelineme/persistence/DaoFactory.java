package ar.edu.unlam.talleweb.timelineme.persistence;

public class DaoFactory {

		public static AgenteDao getAgenteDao(){
			return AgenteDaoJdbcImpl.getInstance();
		}
		
		public static EmpresaDao getEmpresaDao(){
			return EmpresaDaoJdbcImpl.getInstance();
		}
		
		public static SeguirDao getSeguirDao(){
			return SeguirDaoJdbcImpl.getInstance();
		}
		
		public static PublicacionDao getPublicacionDao(){
			return PublicacionDaoJdbcImpl.getInstance();
		}
}
