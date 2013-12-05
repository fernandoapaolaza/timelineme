package ar.edu.unlam.tallerweb.timelineme.persistence;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;










import org.junit.After;
import org.junit.Before;
import org.junit.Test;






import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;
import ar.edu.unlam.talleweb.timelineme.persistence.PublicacionDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.SeguirDaoJdbcImpl;

import org.junit.Test;

public class PublicacionDaoTest {
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	/**/
	@Test
	public void testParaInsertarPublicacion() throws PersistenceException {
		Publicacion publicacion = new Publicacion();
		publicacion.idagente=1;
		publicacion.idempresa=1;
		publicacion.comentario="Comentario desde el Test";
		publicacion.fecha = "2013-12-05 15:32:30";
		
		
		PublicacionDaoJdbcImpl publicacionDao = new PublicacionDaoJdbcImpl();
		publicacionDao.insert(publicacion);
		
		//Checkeo que se haya insertado el Id esperado.
		Publicacion publicacionRegistro = publicacionDao.findById(48);
		
		assertNotNull("El registro Seguir con id 29 debería existir", publicacionRegistro);
	}
	
	@Test
	public void testParaTraerComentariosDeUnaEmpresa() throws PersistenceException {
		
		
		PublicacionDaoJdbcImpl publicacionDao = new PublicacionDaoJdbcImpl();
		List<Publicacion> resultadosPublicaciones = publicacionDao.findAllByEmpresa(3);
		
		assertEquals("La empresa 3 tiene 2 comentarios", 2, resultadosPublicaciones.size());
	}
	
}