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

import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.persistence.ConnectionProvider;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;

import ar.edu.unlam.talleweb.timelineme.services.LoginService;
import ar.edu.unlam.talleweb.timelineme.services.AgenteService;
import ar.edu.unlam.talleweb.timelineme.services.CommentsService;
import ar.edu.unlam.talleweb.timelineme.services.EmpresaService;
import ar.edu.unlam.talleweb.timelineme.services.SeguirService;


import java.util.Iterator; //Importo la interfaz Iterator para iterar el arrayList
import java.util.ArrayList; //Importo la clase ArrayList para poder usar la lista


@Controller
@RequestMapping("/login")
public class LoginController {

	LoginService loginService = new LoginService();

	@RequestMapping("/auth")
	public ModelAndView authenticate(
				HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		
		//Tomo las variables de usuario y pass que obtengo del formulario.
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		
		//Seteo dispatch del tipo ModelAndView
		ModelAndView dispatch = null;
		
		
		if (loginService.authenticate(username, password)) {
			//Login válido.
			
			//Setea  username en una variable de sesión
			session.setAttribute("username", username);
			
			//Instancio AgenteService
			AgenteService agente = new AgenteService();
			
			//Obtengo un Agente, con el username de la sesión
			Agente AtributosAgente = agente.findByName(username);
			
			
			//Instancio CommentsService			
			CommentsService publicacion = new CommentsService();
			//Obtengo todos los comentarios de una empresa en particular. En este caso el del agente logueado.
			List<Publicacion> resultados = publicacion.findAllByEmpresa(AtributosAgente.idempresa);
			
			
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
			
		} else {
			dispatch = new ModelAndView("error", "message", "Ingreso incorrecto");
		}
		return dispatch;
	}

}
