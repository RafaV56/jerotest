package tk.jeroapp.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tk.jeroapp.entidades.Jugador;
import tk.jeroapp.recursos.Validadores;

@Component
public class JugadorValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Jugador.class.isAssignableFrom(clazz);
	}
	 
	@Override
	public void validate(Object target, Errors errors) {
		Jugador usuario = (Jugador) target;

		// ----- Validamos el nombre
		// ----------------------------------------------------------------------

		// Validamos que el nombre no sea nulo
		if (usuario.getNombre() == null) {
			errors.rejectValue("nombre", "null.usuario.nombre");
			usuario.setNombre("");
		}
		// Quitamos los espacios en blanco
		usuario.setNombre(Validadores.revisarEspaciosEnBlancoAlPrincipioYAlfinal(usuario.getNombre().trim()));
		// Luego revisamos que tenga el tamaño de 3 - 50 caracteres
		if (usuario.getNombre().length() < 3 || usuario.getNombre().length() > 50) {
			// la info esta en el messages.properties que se crea y no hay que configurarlo
			errors.rejectValue("nombre", "size.usuario.nombre");
		}
		if (Validadores.revisarSoloLetrasDelEspannol(usuario.getNombre())) {
			// Informamos que no se puede tener números ni caracrteres especiales
			errors.rejectValue("nombre", "letras.usuario.nombre");
		}

		// -----------------------------------------------------------------------------------------------------

		// ----- Validamos la talla y el peso en la propia clase con las anotaciones

		// ----- Validamos la fecha de nacimiento,


	}

}
