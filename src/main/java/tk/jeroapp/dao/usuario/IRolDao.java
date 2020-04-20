package tk.jeroapp.dao.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tk.jeroapp.entidades.Rol;

public interface IRolDao extends JpaRepository<Rol, Long> {

}
