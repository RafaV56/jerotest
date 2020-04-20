package tk.jeroapp.entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioTestSpring {

	@Autowired
	private Usuario usuario;
	
	@Test
	void test() {
		LogManager.getLogger(this.getClass())
        .debug("--------------------------- Test usuario Spring---------------------------");
		assertNotNull(usuario);
	}

}
