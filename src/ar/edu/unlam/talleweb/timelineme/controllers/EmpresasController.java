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
@RequestMapping("/empresas")
public class EmpresasController{
	
	@RequestMapping("/timeline")
	public ModelAndView timeline(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		
		/*Traigo todas las empresas que sigo*/
		
		ModelAndView dispatch = null;
		
		//Recupero el usuario.
		String name = (String) session.getAttribute("username");
		dispatch = new ModelAndView("timeline", "message", name);
		
		//Creo mi instancia de agente
		AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
		Agente agenteObj = agente.findByName(name);
		
		//Traigo todos las empresas que sigo.
		EmpresaDaoJdbcImpl publicacion = new EmpresaDaoJdbcImpl();
		List<Empresa> resultados = publicacion.findFollow(agenteObj.id);
		
		Iterator<Empresa> results = resultados.iterator();
		
		html="";
		while ( results.hasNext() ) {
			Empresa row = results.next();
			
			AgenteDaoJdbcImpl agentecomenta = new AgenteDaoJdbcImpl();
			Agente datosAgente  = agentecomenta.findById(row.getIdagente());
			
			html = html + "<p class='left both cien'>";
			html = html + "<b>" +datosAgente.nombre +"</b>";
			html = html + "<i>("+row.getFecha()+")</i> dijo:";
			html = html + "<br />";
			html = html + row.getComentario() +"<br /><br />";
			html = html + "</p>";
			html = html + "<hr />";
		}
		
		
		return dispatch;
	}
	
	
}