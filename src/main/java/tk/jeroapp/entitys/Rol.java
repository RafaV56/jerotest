package tk.jeroapp.entitys;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "roles",
uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario_id", "nombre" }) 
})
public class Rol implements Serializable {
	
	public static final String USER;
	public static final String ADMIN;
	
	static {
		USER="ROLE_USER";
		ADMIN="ROLE_ADMIN";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	public Rol() {
	}

	public Rol(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;

}
