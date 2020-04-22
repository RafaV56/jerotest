package tk.jeroapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tk.jeroapp.resources.MensajeInicioSesion;
import tk.jeroapp.service.usuario.UsuarioUserDetailService;

/**
 * Clase para configurar la seguridad de la aplicación
 * @author ejemplosdecodigo.ddns.net
 *
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{

	@Autowired
	private MensajeInicioSesion mensajeInicioSesion;

	/**
	 * Sobreescribimos el método para dar autorización a lo que necesitemos, siempre se irá a la página de login si no tiene permiso
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/ccs/**","/js/**","/img/**","/stylesheets/**","/idioma").permitAll().//permitimos a todos usar estas carpetas
		//antMatchers("/usuario").hasAnyRole("USER","ADMIN").//Autorizamos a usuarios y Admin a esta url
		//antMatchers("/crearUsuario").hasAnyRole("ADMIN").anyRequest().authenticated().//Solo puede verla el Admin
		and().formLogin().
		successHandler(mensajeInicioSesion).
		loginPage("/login").permitAll().//Todos pueden acceder al login
		and().logout().permitAll();//Y todos puedes acceder al logout
		
		//Código para poder acceder a la consola de h2 usando spring security
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.csrf().disable();
	    http.headers().frameOptions().disable();

	}
	/**
	 * Registramos un passwordEconder por defecto
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UsuarioUserDetailService usuarioValidado;
	
	/**
	 * Registra a los usuarios, creando dos usuarios, uno ADMIN y otro USER
	 * @param registrarUsuario
	 */
	@Autowired
	public void configuracionGlobal(AuthenticationManagerBuilder registrarUsuario) throws Exception{
		
		registrarUsuario.userDetailsService(usuarioValidado).passwordEncoder(passwordEncoder());
	}
}
