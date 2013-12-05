package ar.edu.unlam.tallerweb.timelineme.persistence;


import static org.junit.Assert.*;

import java.util.List;





import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.persistence.AgenteDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;

import org.junit.Test;

public class AgenteDaoTest {
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testQueSePuedenEncontrarUnAgentePorId() throws PersistenceException {
		AgenteDaoJdbcImpl agenteImpl = new AgenteDaoJdbcImpl();
		Agente unAgente = agenteImpl.findById(1);
		assertEquals("se espera que el username sea drodriguez@test.com", "drodriguez@test.com", unAgente.username);
		
		//assertNotNull("la persona con id 1 debe existir", unAgente.id);
	}
	
	@Test
	public void testQueSePuedenEncontrarUnAgentePorUsername() throws PersistenceException {
		AgenteDaoJdbcImpl agenteImpl = new AgenteDaoJdbcImpl();
		Agente unAgente = agenteImpl.findByName("drodriguez@test.com");
		assertEquals("Se espera que el nombre sea Daniel Rodriguez", "Daniel Rodriguez", unAgente.nombre);
		
	}
	
	@Test
	public void testQueSePuedeInsertarUnAgente() throws PersistenceException {
		
		Agente ObjAgente  = new Agente();
		ObjAgente.nombre="Test";
		ObjAgente.username="test";
		ObjAgente.password="test";
		ObjAgente.idempresa=1;
		
		
		AgenteDaoJdbcImpl agenteImpl = new AgenteDaoJdbcImpl();
		agenteImpl.insert(ObjAgente);
		
		Agente unAgente = agenteImpl.findByName("test");
		assertEquals("Se espera que coincidan los nombres.", ObjAgente.nombre, unAgente.nombre);
		
		
	}
	/*
	 * Poner el número con el que se insertó el agente
	@Test
	public void testQueSePuedeBorrarUnAgente() throws PersistenceException {
		
		Agente ObjAgente  = new Agente();
		ObjAgente.id=6;
		
		
		
		AgenteDaoJdbcImpl agenteImpl = new AgenteDaoJdbcImpl();
		agenteImpl.delete(ObjAgente);
		
		Agente unAgente = agenteImpl.findByName("test");
		assertNull("la persona con id 5 no debe existir", unAgente);
	}
	*/
}
