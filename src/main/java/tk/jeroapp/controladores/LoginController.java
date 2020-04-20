package tk.jeroapp.controladores;

import java.security.Principal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	/**
	  * Poder obtener los mensajes de idioma
	  */
	 @Autowired
	 private MessageSource mensajesIdioma;
	/**
	 * Se necesita validar al usuario para que no se login dos veces
	 * @return
	 */
	@GetMapping("/login")
	public String login(
			@RequestParam(value = "error",required = false)String error,
			@RequestParam(value = "logout",required = false)String logout,
			Model model,Principal principal,RedirectAttributes flash,Locale locale) { 
		//Authentication aut=SecurityContextHolder.getContext().getAuthentication();
		
		if (principal!=null) {
			flash.addFlashAttribute("informacion",  mensajesIdioma.getMessage("login.iniciado", null, locale));
			return "redirect:/";
		}
		if(error!=null) {
			model.addAttribute("cuidado",  mensajesIdioma.getMessage("login.error", null, locale));
		}
		if(logout!=null) {
			flash.addFlashAttribute("informacion",  mensajesIdioma.getMessage("logout.ok", null, locale));
			return "redirect:/";
		}
		return "login";
	}
	
}
