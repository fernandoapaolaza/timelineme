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
import ar.edu.unlam.talleweb.timelineme.persistence.AgenteDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.persistence.ConnectionProvider;
import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PublicacionDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.CommentsService;
import ar.edu.unlam.talleweb.timelineme.persistence.EmpresaDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.persistence.SeguirDaoJdbcImpl;










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
		
		
		//Creo mi instancia de agente
		AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
		Agente agenteObj = agente.findByName(name);
		
		
		PublicacionDaoJdbcImpl publicaciones = new PublicacionDaoJdbcImpl();
		List<Publicacion> comentariosDeEmpresasQueSigo = publicaciones.findAllComments(agenteObj.id);
		
		String html ="";
		
		
		
		Iterator<Publicacion> results = comentariosDeEmpresasQueSigo.iterator();
		
		
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
		
		//dispatch = new ModelAndView("welcome", "message",html); 
		//dispatch.addObject("nombre", name);
		/*
		 * Empresas que sigue.
		Iterator<Seguir> results = resultados.iterator();
		
		String html="";
		
		while ( results.hasNext() ) {
			Seguir row = results.next();
			
			Seguir objSeguir = sigue.findById(row.getId());
			
			
			EmpresaDaoJdbcImpl empresasQueSigo = new EmpresaDaoJdbcImpl();
			//Armo objeto empresa.
			//Empresa objEmpresa  = empresasQueSigo.findById(objSeguir.idempresaseguida);
			//html = html+objEmpresa.nombre;
			
			
			
			
		}*/
		
		dispatch = new ModelAndView("timeline", "message", html);
		dispatch.addObject("nombre", name);
		return dispatch;
	}
	
	
	
	@RequestMapping("/timelinegeneral")
	public ModelAndView timelinegeneral(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		
		/*Traigo todas las empresas que sigo*/
		
		ModelAndView dispatch = null;
		
		//Recupero el usuario.
		String name = (String) session.getAttribute("username");
		
		
		//Creo mi instancia de agente
		AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
		Agente agenteObj = agente.findByName(name);
		
		
		PublicacionDaoJdbcImpl publicaciones = new PublicacionDaoJdbcImpl();
		List<Publicacion> comentariosDeEmpresas = publicaciones.findAllCommentsGeneral();
		
		String html ="";
		
		
		
		Iterator<Publicacion> results = comentariosDeEmpresas.iterator();
		
		
		while ( results.hasNext() ) {
			Publicacion row = results.next();
			
			AgenteDaoJdbcImpl agentecomenta = new AgenteDaoJdbcImpl();
			Agente datosAgente  = agentecomenta.findById(row.getIdagente());
			
			EmpresaDaoJdbcImpl empresaagente = new EmpresaDaoJdbcImpl();
			Empresa empresaObj  = empresaagente.findById(row.getIdempresa());
			
			html = html + "<p class='left both cien'>";
			html = html + "<b>" +datosAgente.nombre +" de la empresa "+empresaObj.nombre+"</b>";
			
			//Preguntar si sigue a esta empresa:
			SeguirDaoJdbcImpl seguir = new SeguirDaoJdbcImpl();
			
			//html = html + agenteObj.id+"-"+empresaObj.id;
			
			if(agenteObj.idempresa!=empresaObj.id){
			
				Boolean sigue = seguir.sigue(agenteObj.id,empresaObj.id);
				if(!sigue){
					html = html+"Aún no sigue a esta empresa! <a href='http://localhost:8080/timelineme/empresas/seguir.do?id="+empresaObj.id+"' style='text-decoration:underline;color:red;'>Seguir!</a>";	
					
				}else{
					html = html+"Ya sigues a esta empresa";	
					
				}
			}
			
			html = html + "<i>("+row.getFecha()+")</i> dijo:";
			html = html + "<br />";
			html = html + row.getComentario() +"<br /><br />";
			html = html + "</p>";
			html = html + "<hr />";
		}
		
		//request.getSession().setAttribute("club", new Objeto());
		
		
		/*
		 * Empresas que sigue.
		Iterator<Seguir> results = resultados.iterator();
		
		String html="";
		
		while ( results.hasNext() ) {
			Seguir row = results.next();
			
			Seguir objSeguir = sigue.findById(row.getId());
			
			
			EmpresaDaoJdbcImpl empresasQueSigo = new EmpresaDaoJdbcImpl();
			//Armo objeto empresa.
			//Empresa objEmpresa  = empresasQueSigo.findById(objSeguir.idempresaseguida);
			//html = html+objEmpresa.nombre;
			
			
			
			
		}*/
		
		dispatch = new ModelAndView("timelinegeneral", "message", html);
		dispatch.addObject("nombre", name);
		return dispatch;
	}
	
	@RequestMapping("/timelineme")
	public ModelAndView timelineme(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		//Recupero el usuario.
		String name = (String) session.getAttribute("username");
				
		/*Traigo todas las empresas que sigo*/
		
		ModelAndView dispatch = null;
		

		AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
		Agente AtributosAgente = agente.findByName(name);
		
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
		
		
		//Empresa del agente
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
		
		
		
		
		dispatch = new ModelAndView("welcome", "message", html);
		dispatch.addObject("nombre", name);
		dispatch.addObject("empresasquesigo", Seguidas);
		return dispatch;
	}
	
	@RequestMapping("/seguir")
	public ModelAndView seguir(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		//Tomo el id de seguimiento.
		String empresaAseguir = (String) request.getParameter("id");
		Integer empresaAseguirInt = Integer.parseInt(empresaAseguir);
		
		String insertaroeliminar = (String) request.getParameter("seguir");
		/*Traigo todas las empresas que sigo*/
		
		ModelAndView dispatch = null;
		
		//Recupero el usuario.
		String name = (String) session.getAttribute("username");
		
		
		//Creo mi instancia de agente
		AgenteDaoJdbcImpl agente = new AgenteDaoJdbcImpl();
		Agente agenteObj = agente.findByName(name);
		
		
		//Agrego el nuevo registro de seguimiento.
		SeguirDaoJdbcImpl nuevoseguimiento = new SeguirDaoJdbcImpl();
		
		Seguir objSeguir = new Seguir();
		objSeguir.idseguidor = agenteObj.id;
		objSeguir.idempresaseguida = empresaAseguirInt;
		
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
		 
		objSeguir.fecha = año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
		
		SeguirDaoJdbcImpl seguirInsert = new SeguirDaoJdbcImpl();
		
		if(insertaroeliminar=="1"){
			seguirInsert.insert(objSeguir);
		}else{
			seguirInsert.insert(objSeguir);				
		}
		
		
		//Crea objeto PublicacionDaoImpl
		PublicacionDaoJdbcImpl publicaciones = new PublicacionDaoJdbcImpl();
		List<Publicacion> comentariosDeEmpresas = publicaciones.findAllCommentsGeneral();
		
		String html ="";
		
		
		
		Iterator<Publicacion> results = comentariosDeEmpresas.iterator();
		
		
		while ( results.hasNext() ) {
			Publicacion row = results.next();
			
			AgenteDaoJdbcImpl agentecomenta = new AgenteDaoJdbcImpl();
			Agente datosAgente  = agentecomenta.findById(row.getIdagente());
			
			EmpresaDaoJdbcImpl empresaagente = new EmpresaDaoJdbcImpl();
			Empresa empresaObj  = empresaagente.findById(row.getIdempresa());
			
			html = html + "<p class='left both cien'>";
			html = html + "<b>" +datosAgente.nombre +" de la empresa "+empresaObj.nombre+"</b>";
			
			//Preguntar si sigue a esta empresa:
			SeguirDaoJdbcImpl seguir = new SeguirDaoJdbcImpl();
			
			//html = html + agenteObj.id+"-"+empresaObj.id;
			
			if(agenteObj.idempresa!=empresaObj.id){
			
				Boolean sigue = seguir.sigue(agenteObj.id,empresaObj.id);
				if(!sigue){
					html = html+"Aún no sigue a esta empresa! <a href='http://localhost:8080/timelineme/empresas/seguir.do?id="+empresaObj.id+"&seguir=1' style='text-decoration:underline;color:red;'>¡Seguir!</a>";	
					
				}else{
					html = html+"Ya sigues a esta empresa! <a href='http://localhost:8080/timelineme/empresas/seguir.do?id="+empresaObj.id+"&seguir=0' style='text-decoration:underline;color:red;'>Dejar de seguir!</a>";	
					
				}
			}
			
			html = html + "<i>("+row.getFecha()+")</i> dijo:";
			html = html + "<br />";
			html = html + row.getComentario() +"<br /><br />";
			html = html + "</p>";
			html = html + "<hr />";
		}
		
		//request.getSession().setAttribute("club", new Objeto());
		
		
				
		dispatch = new ModelAndView("timelinegeneral", "message", html);
		dispatch.addObject("nombre", name);
		return dispatch;
	}
}