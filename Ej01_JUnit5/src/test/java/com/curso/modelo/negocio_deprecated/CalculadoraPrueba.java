package com.curso.modelo.negocio_deprecated;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.curso.modelo.negocio.Calculadora;


//Nombres de clases para que SureFire las encuentre:
//**/Test*.java
//**/*Test.java
//**/*Tests.java
//**/*TestCase.java
public class CalculadoraPrueba {

	private static Calculadora calculadora;

	//CalculadoraTest se instanciar� por cada m�todo marcado con @Test
	public CalculadoraPrueba() {
		super();
		System.out.println("Instanciando CalculadoraTest");
	}

	@BeforeAll
	public static void beforeAll() {
		System.out.println("Before all");
		//Si calculadora es est�tico se utilizar� la misma instancia para todas las pruebas
		//No es recomendable puesto que debemos asegurarnos de que ning�n test est� acoplado a otro
		calculadora = new Calculadora();
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("After all");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("Before each");
		//Es m�s ortodoxo inicializar todo antes de cada test
		//si hicieramos esto 'calculadora' no ser�a est�tica
		//calculadora = new Calculadora();
	}

	@AfterEach
	public void afterEach() {
		System.out.println("After each");
	}

	@Test
	@DisplayName("Prueba de assertAll")
	void testSum() {
		Double sumResult = calculadora.sumar(5d, 4d);
		assertAll(() -> {
			assertTrue(sumResult != null);
			assertTrue(sumResult < 10);
		});
	}

	@Test
	//@Disabled 
	public void estoSoloTieneSentidoEnLinux() {
		// assumeFalse(System.getProperty("os.name").contains("Linux"));

		//Precondici�n
		assumeTrue(System.getProperty("os.name").contains("Linux"));
		assertNotNull(null);

		// Id�ntico al anterior
		assumingThat(System.getProperty("os.name").contains("Linux"), () -> assertNotNull(null));
	}

}


