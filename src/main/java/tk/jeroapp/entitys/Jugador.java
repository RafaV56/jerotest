package tk.jeroapp.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import tk.jeroapp.resources.Fecha;


@Entity
@Table(name = "jugadores")
public class Jugador implements Serializable {

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@NotNull(message = "El año no puede ser nulo")
	@Max(value = 2100)
	@Min(value = 1900)
	@Column(name = "anno")
	private Integer annoNacimiento;
	
	@NotNull(message = "El día no puede ser nulo")
	@Max(value = 31)
	@Min(value = 1)
	@Column(name = "dia")
	private Integer diaNacimiento;
	
	@NotNull(message = "La hora no puede ser nula")
	@Max(value = 23)
	@Min(value = 0)
	@Column(name = "hora")
	private Integer horaNacimiento;
	
	@NotNull(message = "El mes no puede ser nulo")
	@Max(value = 12)
	@Min(value = 1)
	@Column(name = "mes")
	private Integer mesNacimiento;
	
	@NotNull(message = "El minuto no puede ser nulo")
	@Max(value = 59)
	@Min(value = 0)
	@Column(name = "minuto")
	private Integer minutoNacimiento;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,length = 50)
	private String nombre;


	@NotNull(message = "El peso no puede ser nulo")
	@Min(value = 1)
	@Max(value = 350)
	private Double peso;

	@NotNull(message = "La talla no puede ser nula")
	@Min(value = 1)
	@Max(value = 235)
	private Double talla;

	public Jugador() {
	}

	public Jugador(String nombre, Double peso, Double talla) {
		setNombre(nombre);
		setPeso(peso);
		setTalla(talla);
	}

	public Integer getAnnoNacimiento() {
		return annoNacimiento;
	}

	public Integer getDiaNacimiento() {
		return diaNacimiento;
	}

	public Integer getHoraNacimiento() {
		return horaNacimiento;
	}

	public Long getId() {
		return id;
	}

	public Integer getMesNacimiento() {
		return mesNacimiento;
	}
	public Integer getMinutoNacimiento() {
		return minutoNacimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public Double getPeso() {
		return peso;
	}
	
	public Double getTalla() {
		return talla;
	}
	
	public void setAnnoNacimiento(Integer annoNacimiento) {
		this.annoNacimiento = annoNacimiento;
	}
	
	public void setDiaNacimiento(Integer diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}
	public void setHoraNacimiento(Integer horaNacimiento) {
		this.horaNacimiento = horaNacimiento;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setMesNacimiento(Integer mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}
	public void setMinutoNacimiento(Integer minutoNacimiento) {
		this.minutoNacimiento = minutoNacimiento;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public void setTalla(Double talla) {
		this.talla = talla;
	}
	
	/**
	 * Devuelve la fecha de nacimiento usando internamente la clase fecha
	 * @return
	 */
	public String verFechaNacimiento() {
		return new Fecha(getDiaNacimiento(),getMesNacimiento(),getAnnoNacimiento(),getHoraNacimiento(),getMinutoNacimiento()).toString();
	}
	/**
	 * Retorna la edad del usaurio, calculando con la fecha actual
	 * @return
	 */
	public Long edad() {
		return new Fecha(getDiaNacimiento(),getMesNacimiento(),getAnnoNacimiento(),getHoraNacimiento(),getMinutoNacimiento()).calcularAnnos();
	}
	
	private static final long serialVersionUID = 1L;
}
