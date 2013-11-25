package ar.edu.unlam.talleweb.timelineme.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.talleweb.timelineme.persistence.PersistenceException;
import ar.edu.unlam.talleweb.timelineme.services.LoginService;

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
			dispatch = new ModelAndView("welcome", "message", "Bienvenido, @" + username); 
		} else {
			dispatch = new ModelAndView("error", "message", "Ingreso incorrecto" + username + password);
		}
		return dispatch;
	}

}
