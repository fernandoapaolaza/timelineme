package ar.edu.unlam.tallerweb.timelineme.persistence;


import static org.junit.Assert.*;

import java.util.List;






import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.talleweb.timelineme.model.Empresa;

import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;

import org.junit.Test;

public class EmpresaDaoTest {
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	@Test
	public void testQueSePuedenEncontrarUnaEmpresaPorId() throws PersistenceException {
		EmpresaDaoJdbcImpl empresaImpl = new EmpresaDaoJdbcImpl();
		Empresa unaEmpresa = empresaImpl.findById(2);
		assertEquals("se espera que el nombre sea Caos", "Caos", unaEmpresa.nombre);
		
		//assertNotNull("la persona con id 1 debe existir", unAgente.id);
	}

}