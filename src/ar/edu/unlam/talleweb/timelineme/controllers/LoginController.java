package ar.edu.unlam.talleweb.timelineme.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;
import ar.edu.unlam.talleweb.timelineme.persistence.AgenteDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.persistence.ConnectionProvider;
import ar.edu.unlam.talleweb.timelineme.persistence.PublicacionDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.SeguirDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.services.LoginService;
import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDaoJdbcImpl;



import java.util.Iterator; //Importo la interfaz Iterator para iterar el arrayList
import java.util.ArrayList; //Importo la clase ArrayList para poder usar la lista


@Controller
@RequestMapping("/login")
public class LoginController {

	LoginService loginService = new LoginService();

	@RequestMapping("/auth")
	public ModelAndView authenticate(
				HttpServletRequest request, HttpSession session) throws PersistenceException {
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		ModelAndView dispatch = null;
		session.setAttribute("username", username);
		if (loginService.authenticate(username, password)) {
			// Se agrega "username a la sesi�n"
			session.setAttribute("username", username);
			
			AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
			Agente AtributosAgente = agente.findByName(username);
			
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
				html = html + "<i>("+row.getFecha()+")</i> dijo:";
				html = html + "<br />";
				html = html + row.getComentario() +"<br /><br />";
				html = html + "</p>";
				html = html + "<hr />";
			}
			
			//request.getSession().setAttribute("club", new Objeto());
			
			dispatch = new ModelAndView("welcome", "message",html); 
			dispatch.addObject("nombre", username);
			
			EmpresaDaoJdbcImpl empresa = new EmpresaDaoJdbcImpl();
			Empresa ObjEmpresa = empresa.findById(AtributosAgente.idempresa);
			
			//Empresas que sigo
			SeguirDaoJdbcImpl sigue = new SeguirDaoJdbcImpl();
			List<Seguir> resultadosseguidas = sigue.findFollow(AtributosAgente.id);
			
			Iterator<Seguir> resultsseguidas = resultadosseguidas.iterator();
			
			
			
			String Seguidas="";
			
			while ( resultsseguidas.hasNext() ) {
				Seguir row = resultsseguidas.next();
				
				Seguir objSeguir = sigue.findById(row.getId());
				
				
				EmpresaDaoJdbcImpl empresasQueSigo = new EmpresaDaoJdbcImpl();
				//Armo objeto empresa.
				Empresa objEmpresa  = empresasQueSigo.findById(objSeguir.idempresaseguida);
				Seguidas = Seguidas+objEmpresa.nombre+"<br />";
				
				
				
				
			}
			
			dispatch.addObject("empresasquesigo", Seguidas);
			dispatch.addObject("empresa", ObjEmpresa.nombre);
		} else {
			dispatch = new ModelAndView("error", "message", "Ingreso incorrecto");
		}
		return dispatch;
	}

}
