package tk.jeroapp.exceptions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


//Solo para usarlo en un paquete o paquetes que se necesite
//@ControllerAdvice(basePackages={"com.example.exceptions"})
// Cuando una clase herede de una interfaz o herede
//@ControllerAdvice(assignableTypes = {ThisInterface.class, ThatInterface.class})
//con una anotación predeterminada
//@ControllerAdvice(annotations= MyAnnotation.class)
@ControllerAdvice
public class ManejadorDeErrores {
	
	@Autowired
	private MessageSource mensajesIdioma;
	
	/**
	 * El usuario es nulo a la hora de validar
	 * @param request
	 * @param ex
	 * @param model
	 * @param locale
	 * @return
	 */
	@ExceptionHandler({UsuarioNull.class})
    public String exceptiosControladas(HttpServletRequest request,Exception ex,Model model,Locale locale){
		model.addAttribute("status",404);
		model.addAttribute("cuidado", mensajesIdioma.getMessage("null.usuario", null, locale));
        return "error";
    }
	
	
	@ExceptionHandler({Exception.class})
	public String exceptionHandler(HttpServletRequest request,Exception ex,Model model,Locale locale){
		model.addAttribute("status",500);
		model.addAttribute("info", mensajesIdioma.getMessage("error.general.aplicacion", null, locale));
		model.addAttribute("cuidado",ex.getClass().toString());
		return "error";
	}
	
	/**
	 * Usuario con alias repetido
	 * @param request
	 * @param ex
	 * @param model
	 * @param locale
	 * @return
	 */
	@ExceptionHandler({DataIntegrityViolationException.class})
	public String usuarioAliasRepetido(HttpServletRequest request,Exception ex,Model model,Locale locale){
		//Ultima url que visitó el usuario para insertarla en el botón
//		String sql="could not execute statement; SQL [n/a]; nested exception is org.hibernate.exception.DataException: could not execute statement";
//		System.out.println("Error sql= "+ex.getMessage().equals(sql));
//		System.out.println("Erroes data:----->  "+ex.getMessage());
		model.addAttribute("cuidado", 
				mensajesIdioma.getMessage("error.usuario.alias.repetido", null,	locale));
		model.addAttribute("status",409);
		return "error";
	}
	/**
	 * Sin autorización para acceder
	 * @param request
	 * @param ex
	 * @param model
	 * @param locale
	 * @return
	 */
	@ExceptionHandler({AccessDeniedException.class})
	public String sinAutorizacion(HttpServletRequest request,Exception ex,Model model,Locale locale){
		//Ultima url que visitó el usuario para insertarla en el path
		String url=request.getRequestURI();
		model.addAttribute("cuidado", 
				mensajesIdioma.getMessage("error.acceso", 
				null,
				locale));
		model.addAttribute("status",401);
		model.addAttribute("path",url);
		return "error";
	}
	
	
	/**
	 * Error cuando se pasa un parametro que no es del tipo que se espera ej:(Long id="dd") 
	 * @param request
	 * @param ex
	 * @param model
	 * @param locale
	 * @return
	 */
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public String errorTipoParametroURL(HttpServletRequest request,Exception ex,Model model,Locale locale){
		//Ultima url que visitó el usuario para insertarla en el path
		String url=request.getRequestURI();
		model.addAttribute("cuidado", 
				mensajesIdioma.getMessage("error.parametro", 
						null,
						locale));
		model.addAttribute("status",404);
		model.addAttribute("path",url);
		return "error";
	}
	
	/**
	 * Usuario no existe
	 * @param request
	 * @param ex
	 * @param model
	 * @param locale
	 * @return
	 */
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public String usuarioNoExiste(HttpServletRequest request,Exception ex,Model model,Locale locale){
		//para el botón 
		String volver=mensajesIdioma.getMessage("text.volver", null, locale);
		//Ultima url que visitó el usuario para insertarla en el botón
		String id=request.getRequestURI();
		model.addAttribute("cuidado", 
				mensajesIdioma.getMessage("null.usuario", 
						null,
						locale)
				.concat(" ' <strong>ID: "+id.charAt(id.length()-1)+"</strong> ' <a href='/crearUsuario'\" class=\"card-link text-dark btn btn-warning btn-sm\">"+volver+"</a>"));
		model.addAttribute("status",409);
		return "error";
	}

}
