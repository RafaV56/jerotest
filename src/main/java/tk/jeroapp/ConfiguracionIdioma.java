package tk.jeroapp;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Clase para configurar el idioma
 * @author ejemplosdecodigo.ddns.net
 *
 */
@Configuration
public class ConfiguracionIdioma  implements WebMvcConfigurer{
	
	/**
	 * Bean para retomar el la configuración que tiene de idioma la aplicación
	 * que se usará por el interceptor
	 * @return
	 */
	@Bean //No olvidar poner la anotación
	public LocaleResolver localeResolver() {
		//Para guardar en la sessión
		SessionLocaleResolver localeResolver=new SessionLocaleResolver();
		/*Asignar el locale por defecto, 
		creado nueva instancia, pasando dos parametros,
		el código del idioma, y el segundo el país
		que va en Mayúsculas*/
		localeResolver.setDefaultLocale(new Locale("es", "ES"));
		return localeResolver;//retornamos el localater
	}
	
	/**
	 * Interceptor para capturar la variable idioma y asi cambiar el idioma
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localChangeInterceptor() {
		LocaleChangeInterceptor interceptor=new LocaleChangeInterceptor();
		interceptor.setParamName("idioma");//pasamos en la url con el código de localResolver
		return interceptor;
	}

	/**
	 * Registramos el localChageInterceptor
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//añadimos el localchangeinterceptor
		registry.addInterceptor(localChangeInterceptor());
	}
	
	

}
