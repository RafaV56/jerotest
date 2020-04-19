package tk.jeroapp.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase para configurar la seguridad de la aplicación
 * @author ejemplosdecodigo.ddns.net
 *
 */
@Configuration
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter{

/**
 * Sobreescribimos el método para dar autorización a lo que necesitemos
 */
 @Override
 protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/ccs/**","/js/**","/img/**","/stylesheets/**","/idioma").permitAll().//permitimos a todos usar estas carpetas
		antMatchers("/usuario").hasAnyRole("USER","ADMIN").//Autorizamos a usuarios y Admin a esta url
		antMatchers("/crearUsuario").hasAnyRole("ADMIN").anyRequest().authenticated().//Solo puede verla el Admin
		and().formLogin().permitAll().//Todos pueden acceder al login
		and().logout().permitAll();//Y todos puedes acceder al logout
	}
	
/**
* Registramos un passwordEconder por defecto
* @return
 */
@Bean
public BCryptPasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
}

/**
* Registra a los usuarios, creando dos usuarios, uno ADMIN y otro USER
* @param registrarUsuario	
*/
@Autowired
public void configuracionGlobal(AuthenticationManagerBuilder registrarUsuario) throws Exception{
	//UserBuilder usuario=User.withDefaultPasswordEncoder();//como se usaba antes
	PasswordEncoder encoder= passwordEncoder();
	//Configuramos y encriptamos la contraseña con el encoder de BCrypt
	UserBuilder usuario=User.builder().passwordEncoder(password->encoder.encode(password));
	//Configurar el builder y añadimos los usuarios con sus roles
		registrarUsuario.inMemoryAuthentication().withUser(usuario.username("admin").password("admin").roles("ADMIN","USER")).withUser(usuario.username("usuario").password("usuario").roles("USER"));
    }
}