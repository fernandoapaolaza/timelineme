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
import ar.edu.unlam.talleweb.timelineme.persistence.AgenteDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.persistence.ConnectionProvider;
import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PublicacionDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.CommentsService;
import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDaoJdbcImpl;







import java.util.Iterator; //Importo la interfaz Iterator para iterar el arrayList
import java.util.ArrayList; //Importo la clase ArrayList para poder usar la lista
import java.util.Calendar;

@Controller
@RequestMapping("/comments")
public class CommentsController{
	
	@RequestMapping("/comment")
	public ModelAndView comment(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		String comments = (String) request.getParameter("comments");
		String username = (String) request.getParameter("username");
		
		//Inserto un nuevo comentario.
		AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
		Agente AtributosAgente = agente.findByName(username);
		
		
		
		Publicacion objPublicacion = new Publicacion();
		objPublicacion.comentario = comments;
		objPublicacion.idagente = AtributosAgente.id;
		objPublicacion.idempresa = AtributosAgente.idempresa;
		
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
		 
		objPublicacion.fecha = año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
		
		PublicacionDaoJdbcImpl publicacionInsert = new PublicacionDaoJdbcImpl();
		publicacionInsert.insert(objPublicacion);
		
		/**/
		
		
		String html = "";
		
		
		PublicacionDaoJdbcImpl publicacion = new PublicacionDaoJdbcImpl();
		List<Publicacion> resultados = publicacion.findAllByEmpresa(AtributosAgente.idempresa);
		
		Iterator<Publicacion> results = resultados.iterator();
		
		
		while ( results.hasNext() ) {
			Publicacion row = results.next();
			
			AgenteDaoJdbcImpl agentecomenta = new AgenteDaoJdbcImpl();
			Agente datosAgente  = agentecomenta.findById(row.getIdagente());
			html = html + "<p class='left both cien'>";
			html = html + "<b>" +datosAgente.nombre +"</b>";
			html = html + "<i>("+row.getFecha()+") dijo:</i>";
			html = html + "<br />";
			html = html + row.getComentario() +"<br /><br />";
			html = html + "</p>";
			html = html + "<hr />";
		}
		
		
		/**/
		ModelAndView dispatch = null;
		
		dispatch = new ModelAndView("welcome", "message", html);
		dispatch.addObject("nombre", username);
		
		EmpresaDaoJdbcImpl empresa = new EmpresaDaoJdbcImpl();
		Empresa ObjEmpresa = empresa.findById(AtributosAgente.idempresa);
		
		dispatch.addObject("empresa", ObjEmpresa.nombre);
		return dispatch;
	}
	
	
}
