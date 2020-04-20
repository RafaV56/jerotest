package tk.jeroapp.dao.usuario;

import org.springframework.data.repository.CrudRepository;

import tk.jeroapp.entitys.Jugador;

public interface IJugadorDao extends CrudRepository<Jugador, Long> {

}
