package tk.jeroapp.entitys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 30)
	private String alias;

	@Column(length = 60, name = "nombre")
	private String nombreDeUsuario;

	@Column(length = 60, name = "apellido")
	private String apellidos;

	@Column(length = 60)
	private String password;

	private Boolean activo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private List<Rol> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", alias=" + alias + ", nombreDeUsuario=" + nombreDeUsuario + ", apellidos="
				+ apellidos + ", password=" + password + ", activo=" + activo + ", roles=" + roles + "]";
	}

	public void setRol(String nombreDelRol) {
		Rol rol=new Rol();
		rol.setNombre(nombreDelRol);
		roles.add(rol);
	}
	
	private static final long serialVersionUID = 1L;

	public Usuario() {
		roles=new ArrayList<Rol>();
	}

}
