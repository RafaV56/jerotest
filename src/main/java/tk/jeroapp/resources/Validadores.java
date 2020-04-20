package tk.jeroapp.resources;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Collection;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * Clase con métodos para validar campos de los formularios de la aplicación
 * @author RAFAEL VELASQUEZ 30/3/2020
 *
 */
public class Validadores {
	
	/**
	 * Obj para poder hacer entradas en el log
	 */
	private final Logger log=org.slf4j.LoggerFactory.getLogger(getClass());
	
	 /**
	  * Método para validar si el rol del usuario es uno en concreto
	  * @param rol nombre del rol
	  * @return true si contiene el rol false en caso contrario
	  */
	 public static boolean rol(String rol) {
		 boolean bandera=false;
		 
		 SecurityContext context=SecurityContextHolder.getContext();
		 
		 if (context == null) return bandera;
		 
		 Authentication aut=context.getAuthentication();
		 
		 if (aut == null) return bandera;
		 
		 Collection<? extends GrantedAuthority> autorizados=aut.getAuthorities();
		
		 bandera=autorizados.contains(new SimpleGrantedAuthority(rol));
		 
		 return bandera;
	 }
	
	/**
	 * Revisa que solo tenga letras, nada de números ni caracteres especiales, acepta la ñ los acentos á é í ó ú
	 * @param cadena
	 * @return true si tiene números o cualquier caracter especia como [+,´,¡]
	 */
	public static boolean revisarSoloLetrasDelEspannol(String cadena) {

		boolean bandera = false;
		char[] array = cadena.toCharArray();

		for (char c : array) {
			//Si no es letra rompe el bucle y devuelve falso
			if (!Character.isLetter(c)) {
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
	/**
	 * Revisa que solo tenga dígitos, nada de letras ni caracteres especiales
	 * @param cadena
	 * @return true si tiene letras o cualquier caracter especia como [+,´,¡]
	 */
	public static boolean revisarSoloDigitosDelEspannol(String cadena) {
		
		boolean bandera = false;
		char[] array = cadena.toCharArray();
		
		for (char c : array) {
			//Si no es letra rompe el bucle y devuelve falso
			if (!Character.isDigit(c)) {
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
	
	/**
	 * Revisa que solo tenga dígitos y letras nada de caracteres especiales
	 * @param cadena
	 * @return true si tiene cualquier caracter especia como [+,´,¡]
	 */
	public static boolean revisarSoloDigitosYLetras(String cadena) {
		
		boolean bandera = false;
		char[] array = cadena.toCharArray();
		
		for (char c : array) {
			
			//Si no es letra ni dígito
			if (Character.isDigit(c) ) {
				continue;
			}else if(Character.isLetter(c)){
				continue;
			}else {
				bandera = true;
				break;
			}
		}
	
		return bandera;
	}
	
	
	
	
	/**
	 * Revisa los espacios en blanco al final de un string para borrarlos
	 * @param usu usuario a validar
	 */
	public static String revisarEspaciosEnBlancoAlPrincipioYAlfinal(String cadena) {
		//Revisamos que no termine en espacios en blanco
		boolean bandera=false; //si se encuentra un caracter diferente a espacio ya no se válida más
		String apoyo="";
		int largo=cadena.length()-1;
		//Revisamos los espacios finales
		for (int i =largo; i >= 0; i--) {
			//si todavia no hay bandera
			if (!bandera && cadena.charAt(i)==' ') {
				continue;
			}else {
				bandera=true;
				apoyo=cadena.charAt(i)+apoyo;
			}
			
		}
		return apoyo.toString().trim();	
		
	}

	/**
	 * Retorna null si la fecha está bien, si no retorna el mensaje de error
	 * @param fecha
	 * @return
	 */
	public static String validarFecha(int dia, int mes, int anno, int hora, int minuto) {
			

		String error = null;
		
		// Región 1
		if (dia < 1 || dia > 31) {
			error = "El día debe estar en este rango[1-31]";
			// Región 2
		} else if (mes < 1 || mes > 12) {
			error = "El mes debe estar en este rango[1-12]";
			// Región 3
			// Sacamos los datos de LocalDateTime.MAX y LocalDateTime.MIN
		} else if (anno < -999999999 || anno > 999999999) {
			error = "El Año debe estar en este rango[-999999999  A 999999999]";
			// Región 4
		} else if (hora < 0 || hora > 23) {
			error = "La hora debe estar en este rango[0 - 23]";
			// Región 5
		} else if (minuto < 0 || minuto > 59) {
			error = "El  minuto debe estar en este rango[0 - 59]";
		}
		// Región 6
		if (error != null) {
			return error;
		}
		// Región 7
		LocalDateTime fechasNueva = null;
		try {
			// Si hay errores de fechas como el 30 de Febrero se comprueba
			fechasNueva = LocalDateTime.of(anno, mes, dia, hora, minuto);
		} catch (DateTimeException e) {
			// Región 8
			// Tomo el valor donde empieza el mensaje de error
			int donde = e.getMessage().indexOf("'");
			// Solo uso del mensaje que devuelve el evento el mes y su valor
			error = e.getMessage().substring(donde);
			if (error.equalsIgnoreCase("'FEBRUARY 31'")) {
				error = "El  Mes de Febrero no puede tener 31 días";
				// Región 9
			} else if (error.equalsIgnoreCase("'FEBRUARY 30'")) {
				error = "El  Mes de Febrero no puede tener 30 días";
				// Región 10
			} else if (error.equalsIgnoreCase("'February 29' as '" + anno + "' is not a leap year")) {
				error = "El  Mes de Febrero no puede tener 29 días si el Año no es bisiesto";
				// Región 11
			} else if (error.equalsIgnoreCase("'APRIL 31'")) {
				error = "El  Mes de Abril no puede tener 31 días";
				// Región 12
			} else if (error.equalsIgnoreCase("'JUNE 31'")) {
				error = "El  Mes de Junio no puede tener 31 días";
				// Región 13
			} else if (error.equalsIgnoreCase("'SEPTEMBER 31'")) {
				error = "El  Mes de Septiembre no puede tener 31 días";
				// Región 14
			} else if (error.equalsIgnoreCase("'NOVEMBER 31'")) {
				error = "El  Mes de Noviembre no puede tener 31 días";
			}
		}
		return error;
	}


}
