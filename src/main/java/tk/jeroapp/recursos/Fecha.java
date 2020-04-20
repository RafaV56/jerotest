package tk.jeroapp.recursos;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 * Clase desarrollada por ejemplosdecodigo.ddns.net el 13/1/2020, para manejar fechas en EspAñol
 * @author Ejemplos de c�digo (Rafael Antonio Velásquez Millán)
 *
 */
@Component
public class Fecha implements Comparable<Fecha>{
	public Fecha() {
	}
	/**
	 * retorna la fecha del sistema
	 * @return fecha nueva del sistema
	 */
	public static Fecha creaFechaDeAhora(){
		return new Fecha(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear(), LocalTime.now().getHour(),LocalTime.now().getMinute() );
	}
	
	/**
	 * Fecha que se usa internamente en la clase
	 */
	private LocalDateTime fecha;

	/**
	 * Crea una fecha con la hora 0:0
	 * @param dia Día del mes [1-31]
	 * @param mes Mes del año [1 - 12]
	 * @param anno Año
	 */
	public Fecha(int dia,int mes, int anno){
		super();
		this.fecha =crearFecha(dia, mes, anno, 0, 0);
	}

	/**
	 * Crea una fecha con su hora
	  * @param dia día del mes [1-31]
	 * @param mes Mes del Año [1 - 12]
	 * @param anno Año [-999999999 A +999999999]
	 * @param hora hora [0 - 23]
	 * @param minuto Minuto [0 - 59]
	 */
	public Fecha(int dia, int mes, int anno, int hora,int minuto) {
		super();	
		this.fecha = crearFecha(dia, mes, anno, hora, minuto);
	}
	
//	/**
//	 * Contrustor para usar si se recibe de un formulario web una input tipo date, asi se asigna despues a la fecha
//	 * @param fechaDeFormularioWeb
//	 */
//	public Fecha(String fechaDeFormularioWeb) {
//		if(fechaDeFormularioWeb.isBlank() ||
//		   fechaDeFormularioWeb.isEmpty() ||
//		   fechaDeFormularioWeb==null) {
//			throw new RuntimeException("La fecha es nula, o vacía o tiene espacios en blanco");
//		}
//		String[] nueva=fechaDeFormularioWeb.split("-");
//		this.fecha=crearFecha(Integer.parseInt(nueva[2]), Integer.parseInt(nueva[1]),Integer.parseInt(nueva[0]), 0, 0);
//	}
	
	/**
	 * Calcula los años, de mi  está fecha hasta la actual. si está fecha es superior dará un número negativo. no tiene  en cuenta las horas ni lo  minutos
	 * @return los años que  han pasado
	 */
	public long calcularAnnos(){
		long miAnno=fecha.getYear();
		long miMes=fecha.getMonthValue();
		long miDia=fecha.getDayOfMonth();
		
		long hoyAnno=LocalDate.now().getYear();
		long hoyMes=LocalDate.now().getMonthValue();
		long hoyDia=LocalDate.now().getDayOfMonth();
		
		long contador=0;
		//si mi día es igual que el de hoy, mirar meses y años
		if (miDia==hoyDia) {
			//si mi mes también es el mismo ahora solo se pasa a restar años
			if (miMes==hoyMes) {
				contador=hoyAnno-miAnno;
			 //pero si mi mes es superior se resta una año a contador
			}else if(miMes>hoyMes){
				contador=hoyAnno-miAnno;
				contador--;
				//si mi mes es inferior solo queda restar las fechas
			}else {
				contador=hoyAnno-miAnno;
			}
			//en cambio si mi día es superior, se le resta un año a contador
		}else if (miDia>hoyDia) {
			//si mi mes es el mismo,solo se pasa a restar años y un año menos a contador
			if(miMes==hoyMes){
				contador=hoyAnno-miAnno;
				contador--;
				//si mi mes es inferior solo queda restar las fechas
			}else if(miMes>hoyMes){
				contador=hoyAnno-miAnno;
				contador--;
				//si el día es superior pero el mes es inferior a hoy, solo se resta los años
			}else {
				contador=hoyAnno-miAnno;
			}
			//si mi día es inferior pasamos a ver los meses
		}else {
			//si mi mes también es el mismo ahora solo se pasa a restar años
			if (miMes==hoyMes) {
				contador=hoyAnno-miAnno;
			 //pero si mi mes es superior se resta una año a contador
			}else if(miMes>hoyMes){
				contador=hoyAnno-miAnno;
				contador--;
				//si mi mes es inferior solo queda restar las fechas
			}else {
				contador=hoyAnno-miAnno;
			}
			
		}
		
		
		return contador;
	}
	
	@Override
	public int compareTo(Fecha obj) {
		int retorna=0;
		if (obj.getAnno()>getAnno()) {
			retorna=1;
		}else if (obj.getAnno()<getAnno()) {
			retorna=-1;
		}else if (obj.getMesEnNumero()>getMesEnNumero()) {
			retorna=1;
		}else if (obj.getMesEnNumero()<getMesEnNumero()) {
			retorna=-1;
		}else if (obj.getDiaDelmes()>getDiaDelmes()) {
			retorna=1;
		}else if (obj.getDiaDelmes()<getDiaDelmes()) {
			retorna=-1;
		}else if (obj.getHora()>getHora()) {
			retorna=1;
		}else if (obj.getHora()<getHora()) {
			retorna=-1;
		}else if (obj.getMinuto()>getMinuto()) {
			retorna=1;
		}else if (obj.getMinuto()<getMinuto()) {
			retorna=-1;
		}else {
			retorna=0;
		}
	
		return retorna;
	}
	
	/**
	 * Retorna una fecha con su hora
	 * @param dia día del mes [1-31]
	 * @param mes Mes del Año [1 - 12]
	 * @param anno Año [-999999999 A +999999999]
	 * @param hora hora [0 - 23]
	 * @param minuto Minut [0 - 59]
	 * @return una fecha con su hora
	 */
	private LocalDateTime crearFecha(int dia, int mes, int anno, int hora,int minuto) {
		//Región 1
		String error=null;
		if (dia<1 || dia>31) {
			error="El día debe estar en este rango[1-31]";
		//Región 2
		}else if (mes<1 || mes>12) {
			error="El mes debe estar en este rango[1-12]";
		//Región 3
		//Sacamos los datos de LocalDateTime.MAX y LocalDateTime.MIN	
		}else if (anno < -999999999 ||anno>999999999 ) {
			error="El Año debe estar en este rango[-999999999  A 999999999]";
			//Región 4
		}else if (hora<0 || hora >23) {
			error="La hora debe estar en este rango[0 - 23]";
			//Región 5
		}else if (minuto<0 || minuto>59) {
			error="El  minuto debe estar en este rango[0 - 59]";
		}
		//Región 6
		if (error!=null) {
			throw new RuntimeException(error);
		}
		//Región 7
		LocalDateTime fechasNueva=null;
		try {
			//Si hay errores de fechas como el 30 de Febrero se comprueba 
			fechasNueva=LocalDateTime.of(anno, mes, dia, hora, minuto);
		} catch (DateTimeException e) {
			//Región 8
			//Tomo el valor donde empieza el mensaje de error
			int donde=e.getMessage().indexOf("'");
			//Solo uso del mensaje que devuelve el evento el mes y su valor
			error=e.getMessage().substring(donde);
			if (error.equalsIgnoreCase("'FEBRUARY 31'")) {
				error="El  Mes de Febrero no puede tener 31 días";
				//Región 9
			}else if (error.equalsIgnoreCase("'FEBRUARY 30'")) {
				error="El  Mes de Febrero no puede tener 30 días";
				//Región 10
			}else if (error.equalsIgnoreCase("'February 29' as '"+anno+"' is not a leap year")) {
				error="El  Mes de Febrero no puede tener 29 días si el Año no es bisiesto";
				//Región 11
			}else if (error.equalsIgnoreCase("'APRIL 31'")) {
				error="El  Mes de Abril no puede tener 31 días";
				//Región 12
			}else if (error.equalsIgnoreCase("'JUNE 31'")) {
				error="El  Mes de Junio no puede tener 31 días";
				//Región 13
			}else if (error.equalsIgnoreCase("'SEPTEMBER 31'")) {
				error="El  Mes de Septiembre no puede tener 31 días";
				//Región 14
			}else if (error.equalsIgnoreCase("'NOVEMBER 31'")) {
				error="El  Mes de Noviembre no puede tener 31 días";
			}
		}
		//Región 15
		if (error!=null) {
			throw new RuntimeException(error);
		}
		return fechasNueva;
	}

	@Override
	public boolean equals(Object obj) {
		Fecha comparar=(Fecha)obj;
		boolean bandera=false;
		if (comparar==null) {
			throw new RuntimeException("La fecha a comparar es nula");
		}else if (comparar.getAnno()!=getAnno()) {
			bandera=false;
		}else if (comparar.getMesEnNumero()!=getMesEnNumero()) {
			bandera=false;
		}else if (comparar.getDiaDelmes()!=getDiaDelmes()) {
			bandera=false;
		}else if (comparar.getHora()!=getHora()) {
			bandera=false;
		}else if (comparar.getMinuto()!=getMinuto()) {
			bandera=false;
		}else {
			bandera=true;
		}
	return bandera;
	}

	/**
	 * Retorna el Año
	 * @return Año
	 */
	public int getAnno(){
		return fecha.getYear();
	}
	
	/**
	 * Obtienes muchos datos sobre la  fecha
	 * @return Datos de la fecha en espAñol
	 */
	public String getDatosDeLaFecha() {
		return String.format("\n%s\n\tDía del año: %d\n\tDía de la semana: %s"
				+ "\n\tHora : %d Minuto : %d\n\tMes en número: %d", verFechaLarga(),getDiaDelAnno(),getDiaDeLaSemana(),getHora(),getMinuto(),getMesEnNumero());
	}
	/**
	 * Retorna el día del Año [1- 365 o 366 si es bisiesto]
	 * @return día del Año
	 */
	public int getDiaDelAnno(){
		return fecha.getDayOfYear();
	}

	/**
	 * Retorna el día de la semana en espAñol y en may�sculas
	 * @return día de la semana
	 */
	public String getDiaDeLaSemana(){
		DayOfWeek diaDelAnno=fecha.getDayOfWeek();
		
		return diaDelAnno.getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
	}
	
	/**
	 * Retorna el día del mes
	 * @return día del mes
	 */
	public int getDiaDelmes(){
		return fecha.getDayOfMonth();
	}

	/**
	 * Retorna la hora [0 - 23]
	 * @return hora
	 */
	public int getHora(){
		return fecha.getHour();
	}
	
	/**
	 * Retorna el mes en EspAñol y en may�sculas
	 * @return Mes
	 */
	public String getMes() {
	
		Month mes=fecha.getMonth();
		return mes.getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
	}
	
	/**
	 * Retorna el mes en un valor n�merico [1-12]
	 * @return Mes en valor n�merico
	 */
	public int getMesEnNumero() {
		return fecha.getMonthValue();
	}
	
	/**
	 * Retorna el minuto de la hora	
	 * @return Minutos
	 */
	public int getMinuto() {
		return fecha.getMinute();
	}
	/**
	 *  Retorna un fecha con la resta de los Años pasados por parametro
	 * @param annos Años a restar 1 o más
	 * @return Fecha nueva con los Años restados
	 */
	public Fecha restarAnnos(long annos) {
		LocalDateTime nueva=LocalDateTime.now();
		if (annos <= 0) {
			throw new RuntimeException("Los Años a restar tienen que ser uno o más");
		}else{
			 try {
				nueva=fecha.minusYears(annos);
			} catch (DateTimeException e) {
				throw new RuntimeException("Los Años a restar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	/**
	 * Retorna la fecha restando los días pasado por parametros, o, tienen que ser positivos
	 * @param dias días que se quiere restar (Solo Positivos)
	 * @return Fecha con la resta de días
	 */
	public Fecha restarDias(long dias){
		LocalDateTime nueva=LocalDateTime.now();
		if (dias<=0) {
			throw new RuntimeException("Los días a restar tienen que ser superior a cero");
		}else{
		 try {
			nueva=fecha.minusDays(dias);
		} catch (DateTimeException e) {
			throw new RuntimeException("Los días a restar estan fuera del rango permitido");
		}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	/**
	 * Retorna un fecha con la resta de las semanas pasadas por parametro
	 * @param semanas Semanas a restar a la fecha
	 * @return Fecha nueva con las semanas restadas
	 */
	public Fecha restarSemanas(long semanas){
		LocalDateTime nueva=LocalDateTime.now();
		if (semanas <= 0) {
			throw new RuntimeException("Las semanas a restar tienen que ser una o más");
		}else{
			 try {
				nueva=fecha.minusWeeks(semanas);
			} catch (DateTimeException e) {
				throw new RuntimeException("Las semanas a restar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	
	/**
	 * Retorna un fecha con la suma de los Años pasados por parametro
	 * @param annos Años a sumar 1 o más
	 * @return Fecha nueva con los Años sumados
	 */
	public Fecha sumarAnnos(long annos) {
		LocalDateTime nueva=LocalDateTime.now();
		if (annos <= 0) {
			throw new RuntimeException("Los Años a sumar tienen que ser uno o más");
		}else{
			 try {
				nueva=fecha.plusYears(annos);
			} catch (DateTimeException e) {
				throw new RuntimeException("Los Años a sumar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}

	/**
	 * Retorna una fecha sumando los días pasados como parametro, tienen que ser positivos
	 * @param dias días para sumarle a la fecha (Solo Positivos)
	 * @return Fecha nueva con la suma de días
	 */
	public Fecha sumarDias(long dias){
		LocalDateTime nueva=null;
		if (dias<=0) {
			throw new RuntimeException("Los días a sumar tienen que ser superior a cero");
		}else{
		 try {
			nueva=fecha.plusDays(dias);
		} catch (DateTimeException e) {
			throw new RuntimeException("Los días a suma estan fuera del rango permitido");
		}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
		
	}

	/**
	 * Retorna una Fecha sumando las semanas que pasadas por parametro
	 * @param semanas Las semanas a sumar a tu fecha
	 * @return Fecha nueva con las semanas sumadas
	 */
	public Fecha sumarSemanas(long semanas){
		LocalDateTime nueva=LocalDateTime.now();
		if (semanas <= 0) {
			throw new RuntimeException("Las semanas a sumar tienen que ser una o más");
		}else{
			 try {
				nueva=fecha.plusWeeks(semanas);
			} catch (DateTimeException e) {
				throw new RuntimeException("Las semanas a sumar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}

	@Override
	public String toString() {
		
		//return "["+getAnno()+"-"+getMesEnNumero()+"-"+getDiaDelmes()+"]";
		return ""+verFechaLarga()+ " "+getHora()+":"+getMinuto()+"";
	}
	/**
	 * Retorna una fecha con los meses sumados como parametros
	 * @param meses Meses a sumar
	 * @return
	 */
	public Fecha sumarMeses(long meses) {
		LocalDateTime nueva=LocalDateTime.now();
		if (meses <= 0) {
			throw new RuntimeException("Los meses a sumar tienen que ser uno o más");
		}else{
			 try {
				nueva=fecha.plusMonths(meses);
			} catch (DateTimeException e) {
				throw new RuntimeException("Los meses a sumar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}


	/**
	 * Retorna un String con solo la fecha en la zona que estas, ej: 16 de enero de 2020
	 * @return
	 */
	private String verFechaLarga(){
		return fecha.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
	}


}
