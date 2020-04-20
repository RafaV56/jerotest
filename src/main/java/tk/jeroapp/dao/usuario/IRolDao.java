package tk.jeroapp.dao.usuario;

import org.springframework.data.jpa.repository.JpaRepository;


import tk.jeroapp.entitys.Rol;

public interface IRolDao extends JpaRepository<Rol, Long> {

}
