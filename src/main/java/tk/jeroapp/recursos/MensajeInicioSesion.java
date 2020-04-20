package tk.jeroapp.recursos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

/**
 * Clase para poder enviar mensajes cuando el usuario inicia sesión correctame
 * @author ejemplosdecodigo.ddns.net
 *
 */
@Component
public class MensajeInicioSesion extends SimpleUrlAuthenticationSuccessHandler{

	/**
	  * Poder obtener los mensajes de idioma
	  */
	 @Autowired
	 private MessageSource mensajesIdioma;
	 
	 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		if (authentication!=null) {
			logger.info("inició sesion: ".concat(authentication.getName()));
		}
		
		// obtenemos el sesionMap... para poder registrar el flashmap para mensajes
		SessionFlashMapManager flash=new SessionFlashMapManager();
		//Creamos un map para los mensajes
		FlashMap map=new FlashMap(); //hereda de hashMap, se puede usar put, así que añadimos el mensaje 
		map.put("exito", mensajesIdioma.getMessage("login.ok", 
				null,
				LocaleContextHolder.getLocale()).concat(" ' <strong>"+authentication.getName()+"</strong> '"));//***Borrar""  mirar como obtener ingles cuando pase por aqui
		//registramos en el manager
		flash.saveOutputFlashMap(map, request, response);
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
