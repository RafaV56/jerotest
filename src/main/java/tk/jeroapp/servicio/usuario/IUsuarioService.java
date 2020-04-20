package tk.jeroapp.servicio.usuario;

import java.util.List;

import tk.jeroapp.entidades.Usuario;

public interface IUsuarioService {

	public List<Usuario> buscarTodos();
	
	public Usuario guardar(Usuario usuario);
	
	public Usuario buscarUno(Long id);
	
	public void editar(Usuario usuario);
	
	public void borrar(Long id);
}
