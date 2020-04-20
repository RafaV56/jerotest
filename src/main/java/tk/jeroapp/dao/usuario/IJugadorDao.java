package tk.jeroapp.dao.usuario;

import org.springframework.data.repository.CrudRepository;

import tk.jeroapp.entidades.Jugador;

public interface IJugadorDao extends CrudRepository<Jugador, Long> {

}
