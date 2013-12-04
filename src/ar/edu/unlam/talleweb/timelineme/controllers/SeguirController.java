package ar.edu.unlam.talleweb.timelineme.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.talleweb.timelineme.model.Agente;
import ar.edu.unlam.talleweb.timelineme.model.Empresa;
import ar.edu.unlam.talleweb.timelineme.model.Publicacion;
import ar.edu.unlam.talleweb.timelineme.model.Seguir;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.persistence.SeguirDaoJdbcImpl;
import ar.edu.unlam.talleweb.timelineme.services.AgenteService;
import ar.edu.unlam.talleweb.timelineme.services.CommentsService;
import ar.edu.unlam.talleweb.timelineme.services.EmpresaService;
import ar.edu.unlam.talleweb.timelineme.services.SeguirService;

import java.util.Calendar;

@Controller
@RequestMapping("/seguir")
public class SeguirController {
	@RequestMapping("/timelineme_seguir")
	public ModelAndView seguir(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		//Defino la salida.
		ModelAndView dispatch = null;
		
		//Recupero el usuario.
		String name = (String) session.getAttribute("username");
		
		//Tomo el id de seguimiento.
		String idEmpresa = (String) request.getParameter("idempresa");
		Integer idEmpresaInt = Integer.parseInt(idEmpresa);
		
		//Creo mi instancia de agente
		AgenteService servicioDeAgente = new AgenteService();
		Agente agenteObj = servicioDeAgente.findByName(name);
		
		//Armo el objeto seguir, para insertar
		
		
		Seguir objSeguir = new Seguir();
		objSeguir.idseguidor = agenteObj.id;
		objSeguir.idempresaseguida = idEmpresaInt;
		
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
		 
		objSeguir.fecha = año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
		
		//Ingreso el registro en la tabla seguir.
		SeguirService seguir = new SeguirService();
		Boolean inserta = seguir.insert(objSeguir);
		
		//Obtengo una lista de los comentarios de las empresas que sigo.
		CommentsService publicaciones = new CommentsService();
		List<Publicacion> comentariosDeEmpresasQueSigo = publicaciones.findAllByEmpresa(agenteObj.id);
				
		//Despacho la Lista
		dispatch = new ModelAndView("timelineme", "message", comentariosDeEmpresasQueSigo);
		
		//Despacho el nombre del usuario
		dispatch.addObject("nombre", name);
		
		//Instancio EmpresaService
		EmpresaService empresa = new EmpresaService();
		Empresa ObjEmpresa = empresa.findById(agenteObj.idempresa);
		
		//Despacho la variable con el nombre de la empresa.
		dispatch.addObject("empresa", ObjEmpresa.nombre);
		
		//Empresas que sigo
		SeguirService sigue = new SeguirService();
		List<Seguir> resultadosSeguidas = sigue.findFollow(agenteObj.id);
		
		//Despacho la variable con el nombre de la empresa.
		dispatch.addObject("empresasQueSigo", resultadosSeguidas);
		
		//Empresas que no sigo
		List<Seguir> resultadosNoSeguidas = sigue.findNoFollow(agenteObj.id,agenteObj.idempresa);
		dispatch.addObject("empresasQueNoSigo", resultadosNoSeguidas);
		
		return dispatch;
	}
	
	
	@RequestMapping("/timelineme_dejardeseguir")
	public ModelAndView dejardeseguir(
			HttpServletRequest request, HttpSession session) throws PersistenceException {
		
		//Defino la salida.
		ModelAndView dispatch = null;
		
		//Recupero el usuario.
		String name = (String) session.getAttribute("username");
		
		//Tomo el id de seguimiento.
		String idEmpresa = (String) request.getParameter("idempresa");
		Integer idEmpresaInt = Integer.parseInt(idEmpresa);
		
		//Creo mi instancia de agente
		AgenteService servicioDeAgente = new AgenteService();
		Agente agenteObj = servicioDeAgente.findByName(name);
		
		//Armo el objeto seguir, para insertar
		
		
		//Ingreso el registro en la tabla seguir.
		SeguirService seguir = new SeguirService();
		Boolean borra = seguir.delete(agenteObj.id,idEmpresaInt);
		
		//Obtengo una lista de los comentarios de las empresas que sigo.
		CommentsService publicaciones = new CommentsService();
		List<Publicacion> comentariosDeEmpresasQueSigo = publicaciones.findAllByEmpresa(agenteObj.id);
				
		//Despacho la Lista
		dispatch = new ModelAndView("timelineme", "message", comentariosDeEmpresasQueSigo);
		
		//Despacho el nombre del usuario
		dispatch.addObject("nombre", name);
		
		//Instancio EmpresaService
		EmpresaService empresa = new EmpresaService();
		Empresa ObjEmpresa = empresa.findById(agenteObj.idempresa);
		
		//Despacho la variable con el nombre de la empresa.
		dispatch.addObject("empresa", ObjEmpresa.nombre);
		
		//Empresas que sigo
		SeguirService sigue = new SeguirService();
		List<Seguir> resultadosSeguidas = sigue.findFollow(agenteObj.id);
		
		//Despacho la variable con el nombre de la empresa.
		dispatch.addObject("empresasQueSigo", resultadosSeguidas);
		
		//Empresas que no sigo
		List<Seguir> resultadosNoSeguidas = sigue.findNoFollow(agenteObj.id,agenteObj.idempresa);
		dispatch.addObject("empresasQueNoSigo", resultadosNoSeguidas);
		
		return dispatch;
	}
}
