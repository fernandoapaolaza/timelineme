package ar.edu.unlam.talleweb.timelineme.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.talleweb.timelineme.model.Persona;
import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.PersonaService;


@Controller
@SessionAttributes("sValue")
@RequestMapping("/perfil")
public class SessionController {

//	LoginService loginService = new LoginService();
	PersonaService personaService = new PersonaService();

	@RequestMapping("/inicio")
	
		// Pasamos como argumento HttpSession al método que maneja el requerimiento

	public ModelAndView mainPage(HttpSession session) throws PersistenceException {
		ModelAndView mav = new ModelAndView("home"); 
		
		// Se recupera el atributo de la sesión
		String name = (String) session.getAttribute("username");
		
		// Se obtiene el usuario/persona logueado/a
		Persona miUsuario = personaService.findByName(name);
		
		// Se obtiene una lista de todos los usuarios/personas
		List<Persona> misUsuarios = personaService.findAll();
		
		// Se obtiene el Id único de sesión
		String sid = session.getId();
		
		// Colocamos algunos valores en la sesión del controlador		
        mav.addObject("sid", sid);  
        mav.addObject("name", name); 
        mav.addObject("miUsuario", miUsuario); 
        mav.addObject("misUsuarios", misUsuarios);
        String sValue = "Algún valor como String";  
	    mav.addObject("sValue", sValue);  
	    return mav;
	}

}
