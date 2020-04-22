package tk.jeroapp.entidades;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import tk.jeroapp.controllers.UsuarioController;
import tk.jeroapp.entitys.Rol;
import tk.jeroapp.entitys.Usuario;
import tk.jeroapp.exceptions.UsuarioNull;

@ActiveProfiles("test")
@SpringBootTest()
@DirtiesContext()
class UsuarioControladorTest {

	@Autowired
	private UsuarioController controlador;
	
    @MockBean
    private BindingResult status;
	
	@Test
	void test() {
		
		LogManager.getLogger(this.getClass())
        .info("-----------------------Usuario controlador-----------------------");
		assertThat(controlador).isNotNull();//no es nulo
		Mockito.when(status.hasErrors()).thenReturn(true);
		Usuario usuario=null;
		Locale locale=new Locale("es");
		try {
			controlador.crearUsaurioPost(usuario, status, null, null, null, locale);
		}catch (UsuarioNull e) {
			assertEquals("null.usuario", e.getMessage());
		}
//		usuario = new Usuario();
//		usuario.setActivo(true);
//		usuario.setAlias("alias");
//		usuario.setNombreDeUsuario("usaurio nuevo");
//		usuario.setApellidos("apellido nuevo");
//		usuario.setRol(Rol.USER);
//		controlador.crearUsaurioPost(usuario, status, null, null, null, locale);
		
		
	}

}
