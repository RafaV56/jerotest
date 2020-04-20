package tk.jeroapp.entidades;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import tk.jeroapp.entitys.Usuario;


@ActiveProfiles("test")
@SpringBootTest()
class UsuarioTestSpring {

	@MockBean
	private Usuario mock;
	
	@Test
	void test() {
		
	}

}
