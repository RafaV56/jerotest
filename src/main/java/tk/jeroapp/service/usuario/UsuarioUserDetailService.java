package tk.jeroapp.service.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.jeroapp.dao.usuario.IUsuarioDao;
import tk.jeroapp.entitys.Rol;
import tk.jeroapp.entitys.Usuario;

@Service("usuarioServiceImp")
public class UsuarioUserDetailService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario=usuarioDao.findByAlias(username);
		
		if (usuario==null) {
			throw new RuntimeException("El usuario no existe");
		}
		
		//obtener los roles
		List<GrantedAuthority> roles=new ArrayList<GrantedAuthority>();
		
		
		//por cada rol lo añadimos a 
		for (Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		if (roles.isEmpty()) {
			throw new RuntimeException("El usuario no tiene roles");
		}
		
		return new User(usuario.getAlias(), usuario.getPassword(), usuario.getActivo(), true, true, true, roles);
	}
	

}
