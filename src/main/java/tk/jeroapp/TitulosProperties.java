package tk.jeroapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Clase para poder usar los ficheros properties creados por el admin en el folder PropetiesAPP
 * @author Rafael velásquez 26/3/2020
 *
 */
@Configuration
@PropertySources({
	@PropertySource("classpath:/PropertiesAPP/titulos.properties")
})
public class TitulosProperties {

}
