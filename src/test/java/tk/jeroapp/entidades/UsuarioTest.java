package tk.jeroapp.entidades;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

class UsuarioTest {

	@Test
	void test() {
		LogManager.getLogger(this.getClass())
        .debug("--------------------------- Test usuario---------------------------");
		Usuario usuario=new Usuario();
		
		usuario.setNombreDeUsuario("jero app");
		LogManager.getLogger(this.getClass())
        .debug("--> probando el nombre del usuario");
		assertEquals("jero app", usuario.getNombreDeUsuario(),"Se esparaba ' jero app '");
		
		LogManager.getLogger(this.getClass())
        .debug("--> probando el apellido");
		usuario.setApellidos("web");
		assertEquals("web".toUpperCase(), usuario.getApellidos(),"Se esparaba ' WEB ''");
		
		LogManager.getLogger(this.getClass())
		.debug("--> probando el Alias");
		usuario.setAlias("admin");
		assertEquals("admin", usuario.getAlias(),"Se esparaba ' admin ''");
		
		LogManager.getLogger(this.getClass())
		.debug("--> probando el id");
		usuario.setId(55L);
		assertEquals(55L, usuario.getId(),"Se esparaba ' 55 ''");
		
		LogManager.getLogger(this.getClass())
		.debug("--> probando el password");
		usuario.setPassword("asdgasdf");
		assertEquals("asdgasdf", usuario.getPassword(),"Se esparaba ' asdgasdf ''");
		
		LogManager.getLogger(this.getClass())
		.debug("--> probando activo");
		usuario.setActivo(true);
		assertEquals(true, usuario.getActivo(),"Se esparaba ' true ''");
		
		LogManager.getLogger(this.getClass())
		.debug("--> probando Roles");
		usuario.setRol("ROLE_USER"); 
		usuario.setRol("ROLE_ADMIN"); 
		assertEquals("[ROLE_USER, ROLE_ADMIN]", usuario.getRoles().toString(),"Se esparaba ' [ROLE_USER,ROLE_ADMIN] ''");
		
		
		
		LogManager.getLogger(this.getClass())
        .debug("--------------------------- Fin test usuario---------------------------");
	}

}
