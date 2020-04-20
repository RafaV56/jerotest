package tk.jeroapp.dao.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.jeroapp.entidades.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByAlias(String username);
	

}
