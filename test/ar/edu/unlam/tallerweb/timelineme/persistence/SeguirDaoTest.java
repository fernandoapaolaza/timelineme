package ar.edu.unlam.tallerweb.timelineme.persistence;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;









import org.junit.After;
import org.junit.Before;
import org.junit.Test;





import ar.edu.unlam.talleweb.timelineme.model.Seguir;
import ar.edu.unlam.talleweb.timelineme.persistence.SeguirDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;

import org.junit.Test;

public class SeguirDaoTest {
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	/**/
	@Test
	public void testParaSeguir() throws PersistenceException {
		Seguir seguir = new Seguir();
		seguir.idempresaseguida = 5;
		seguir.idseguidor = 10;
		seguir.fecha = "2013-05-12 02:53:20";
		
		SeguirDaoJdbcImpl seguirDao = new SeguirDaoJdbcImpl();
		seguirDao.insert(seguir);
		
		//Checkeo que se haya insertado el Id esperado.
		Seguir seguirRegistro = seguirDao.findById(29);
		
		assertNotNull("El registro Seguir con id 29 debería existir", seguirRegistro.id);
	}
	/**/
	/*
	@Test
	public void testParaDejarDeSeguir() throws PersistenceException {
		
		
		SeguirDaoJdbcImpl seguirDao = new SeguirDaoJdbcImpl();
		seguirDao.delete(10,5);
		
		//Checkeo que se haya insertado el Id esperado.
		Seguir seguirRegistro = seguirDao.findById(29);
		
		assertNull("la persona con id 29 no debe existir", seguirRegistro);
	}
	*/
	
	
	@Test
	public void testParaTraerCuantasSigo() throws PersistenceException {
		
		
		SeguirDaoJdbcImpl seguirDao = new SeguirDaoJdbcImpl();
		List<Seguir> resultadosSeguidas = seguirDao.findFollow(1);
		
		assertEquals("Tengo que seguir una empresa", 1, resultadosSeguidas.size());
	}
	
	@Test
	public void testParaTraerCuantasNoSigo() throws PersistenceException {
		
		
		SeguirDaoJdbcImpl seguirDao = new SeguirDaoJdbcImpl();
		List<Seguir> resultadosSeguidas = seguirDao.findNoFollow(1,1);
		
		assertEquals("No sigo a una empresa", 1, resultadosSeguidas.size());
	}
	
}