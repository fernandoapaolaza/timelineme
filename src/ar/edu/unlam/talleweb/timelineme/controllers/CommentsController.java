package ar.edu.unlam.talleweb.timelineme.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.persistence.ConnectionProvider;

import ar.edu.unlam.talleweb.timelineme.persistence.PublicacionDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.AgenteService;
import ar.edu.unlam.talleweb.timelineme.services.CommentsService;
import ar.edu.unlam.talleweb.timelineme.services.EmpresaService;
import ar.edu.unlam.talleweb.timelineme.services.SeguirService;



import java.util.Iterator; //Importo la interfaz Iterator para iterar el arrayList
import java.util.ArrayList; //Importo la clase ArrayList para poder usar la lista
import java.util.Calendar;

@Controller
@RequestMapping("/comments")
public class CommentsController{
	
	@RequestMapping("/comment")
	public ModelAndView comment(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		//Tomo los valores del formulario.
		String comments = (String) request.getParameter("comments");
		String username = (String) request.getParameter("username");
		
		
		//Instancio AgenteService.
		AgenteService  agenteService =  new AgenteService();
		//Obtengo un objeto agente del usuario actual.
		Agente AtributosAgente = agenteService.findByName(username);
		
		//Inserto un nuevo comentario.
		Publicacion objPublicacion = new Publicacion();
		objPublicacion.comentario = comments;
		objPublicacion.idagente = AtributosAgente.id;
		objPublicacion.idempresa = AtributosAgente.idempresa;
		
		//Armo la fecha
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
		 
		objPublicacion.fecha = año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
		
		
		//Instancio PublicacionDaoJdbcImpl		
		PublicacionDaoJdbcImpl publicacioninsert = new PublicacionDaoJdbcImpl();
		publicacioninsert.insert(objPublicacion);
		
		
		//Instancio CommentsService			
		CommentsService publicacion = new CommentsService();
				
		//Obtengo todos los comentarios de una empresa en particular. En este caso el del agente logueado.
		List<Publicacion> resultados = publicacion.findAllByEmpresa(AtributosAgente.idempresa);
		
		//Instancio lo que voy a despachar.
		ModelAndView dispatch = null;
		
		//Envio el List con los resultados de comentarios de esta empresa.			
		dispatch = new ModelAndView("welcome", "message", resultados); 
		
		
		//Instancio EmpresaService
		EmpresaService empresa = new EmpresaService();
		Empresa ObjEmpresa = empresa.findById(AtributosAgente.idempresa);
		
		//Despacho la variable con el nombre de la empresa.
		dispatch.addObject("empresa", ObjEmpresa.nombre);
		
		//Empresas que sigo
		SeguirService sigue = new SeguirService();
		List<Seguir> resultadosSeguidas = sigue.findFollow(AtributosAgente.id);
		
		//Despacho la variable con el nombre de la empresa.
		dispatch.addObject("empresasQueSigo", resultadosSeguidas);
		
		//Empresas que no sigo
		List<Seguir> resultadosNoSeguidas = sigue.findNoFollow(AtributosAgente.id,AtributosAgente.idempresa);
		dispatch.addObject("empresasQueNoSigo", resultadosNoSeguidas);
		
		return dispatch;
	}
	
	
}
