package tk.jeroapp.excepciones;

public class UsuarioNull extends RuntimeException {
	
	public UsuarioNull(String error) {
		super(error);
	}

	private static final long serialVersionUID = 1L;
}
