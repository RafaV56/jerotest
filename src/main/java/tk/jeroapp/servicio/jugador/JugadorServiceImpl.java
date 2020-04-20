package tk.jeroapp.servicio.jugador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.jeroapp.dao.usuario.IJugadorDao;
import tk.jeroapp.entidades.Jugador;

@Service
public class JugadorServiceImpl implements IJugadorService {

	@Autowired
	private IJugadorDao jugadorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Jugador> buscarTodos() {
		return (List<Jugador>)jugadorDao.findAll();
	}

	@Override
	@Transactional//escritura
	public void guardar(Jugador usuario) {
		jugadorDao.save(usuario);

	}

	@Override
	@Transactional
	public Jugador buscarUno(Long id) {
		return jugadorDao.findById(id).orElseGet(null);
	}

	@Override
	@Transactional
	public void editar(Jugador usuario) {
		jugadorDao.save(usuario);
	}

	@Override
	@Transactional
	public void borrar(Long id) {
		jugadorDao.deleteById(id);

	}

}
