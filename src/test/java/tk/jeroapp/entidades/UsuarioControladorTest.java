package tk.jeroapp.entidades;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import tk.jeroapp.controllers.UsuarioController;

@ActiveProfiles("test")
@SpringBootTest()
@DirtiesContext()
class UsuarioControladorTest {

	@Autowired
	private UsuarioController controlador;
	@Test
	void test() {
		
		LogManager.getLogger(this.getClass())
        .info("-----------------------Usuario controlador-----------------------");
		assertThat(controlador).isNotNull();//no es nulo
	
		
		
	}

}
