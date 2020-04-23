package tk.jeroapp.resources;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

class ValidadoresTest {

	@Test
	void test() {
		
		LogManager.getLogger(this.getClass())
        .info("========================= Validadores test =============================");
		
		LogManager.getLogger(this.getClass())
        .info("---->Validando contiene letras, dígitos, guion bajo, punto, guion y espacios");
		
		boolean bandera=true;
		String cadena="";
		int digito=0;
		
		//=======Validando solo contiene letras, dígitos, guion bajo, punto, guion y espacios=================================);
		cadena="hola NDAD 3333_.-";
		bandera = Validadores.revisarSoloDigitosYLetras(cadena);
		assertFalse(bandera,"Se espera false al ser solo letras, dígitos, guion bajo, punto, guion y espacios");
		
		cadena="´";
		bandera = Validadores.revisarSoloDigitosYLetras(cadena);
		assertTrue(bandera,"Se espera true al ser un caracter especial");
		
		cadena="3 l";
		bandera = Validadores.revisarSoloDigitosYLetras(cadena);
		assertFalse(bandera,"Se espera false al ser un número una letra y un espacio en blanco");
		
		
		//======================================
		LogManager.getLogger(this.getClass())
        .info("---->Validando solo dígitos");
		
		//=======Validando solo contiene letras y espacios en blanco==================================
		cadena=String.valueOf(digito);
		bandera = Validadores.revisarSoloDigitosDelEspannol(cadena);
		assertFalse(bandera,"Se espera false al ser el dígito cero");
		
		cadena = "l";
		bandera = Validadores.revisarSoloDigitosDelEspannol(cadena);
		assertTrue(bandera,"Se espera verdad al ser una letra");
		
		cadena = ".";
		bandera = Validadores.revisarSoloDigitosDelEspannol(cadena);
		assertTrue(bandera,"Se espera verdad al ser un signo");
		
		//======================================
		
		LogManager.getLogger(this.getClass())
		.info("---->Validando solo contiene letras y espacios en blanco");
		
		//=======Validando solo contiene letras y espacios en blanco==================================
		cadena = "EJEMPLO minusculas áéíóú Üü ";
		bandera = Validadores.revisarSoloLetrasDelEspannol(cadena);
		assertFalse(bandera,"Se espera false al ser todas letra del español");
		
		cadena = "3";
		bandera = Validadores.revisarSoloLetrasDelEspannol(cadena);
		assertTrue(bandera,"Se espera verdad al ser un número");
		
		cadena = ".";
		bandera = Validadores.revisarSoloLetrasDelEspannol(cadena);
		assertTrue(bandera,"Se espera verdad al ser un signo");
		
		//======================================
		
		LogManager.getLogger(this.getClass())
        .info("---->Validando cadenas sin espacios");
		
		//===============Validando Espacios en blanco al principio y final==================================
	    cadena="  ConEspaciosAlInicio";
		cadena=Validadores.revisarEspaciosEnBlancoAlPrincipioYAlfinal(cadena);
		assertEquals("ConEspaciosAlInicio", cadena);
		
		cadena="ConEspaciosAlfinal   ";
		cadena=Validadores.revisarEspaciosEnBlancoAlPrincipioYAlfinal(cadena);
		assertEquals("ConEspaciosAlfinal", cadena);
		
		cadena="  ConEspacios en el medio  ";
		cadena=Validadores.revisarEspaciosEnBlancoAlPrincipioYAlfinal(cadena);
		assertEquals("ConEspacios en el medio", cadena);
		//======================================
		
		
		LogManager.getLogger(this.getClass())
        .info("============== Fin de Viladando cadenas sin espacios =================");
	}

}
