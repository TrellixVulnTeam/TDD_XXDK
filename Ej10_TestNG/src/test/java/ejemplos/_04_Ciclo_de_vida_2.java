package ejemplos;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.curso.modelo.negocio.Calculadora;

public class _04_Ciclo_de_vida_2 {

	private Calculadora calculadora;

	//CalculadoraTest se instanciar� una �nica vez y se ejecutar�n todos sun m�todos marcados con @Test
	public _04_Ciclo_de_vida_2() {
		super();
		System.out.println("Instanciando 04_Ciclo_de_vida_2");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println(Thread.currentThread().getName()+"-Before test");
	}

	@AfterTest
	public void afterTest() {
		System.out.println(Thread.currentThread().getName()+"-After test");
	}	
	
	@BeforeClass
	public void beforeClass() {
		System.out.println(Thread.currentThread().getName()+"-Before class");
		calculadora = new Calculadora();
	}

	@AfterClass
	public void afterClass() {
		System.out.println(Thread.currentThread().getName()+"-After class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println(Thread.currentThread().getName()+"-Before method");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println(Thread.currentThread().getName()+"-After method");
	}

	@Test
	public void laSumaDebeSerCorrecta() {	
		
		System.out.println(Thread.currentThread().getName()+"-Test 1");
		
		//DADO
		double s1 = 2d;
		double s2 = 2d;
		//CUANDO
		Double res = calculadora.sumar(s1, s2);
		//ENTONCES		
		assertEquals(Double.valueOf(4), res);
	}
	
	@Test
	public void dividirFuncionaCorrectamente() throws Exception {
		System.out.println(Thread.currentThread().getName()+"-Test 2");
		assertEquals(Double.valueOf(5d), calculadora.dividir(10d, 2d));
	}

	@Test
	public void cuadradoFuncionaCorrectamente() throws Exception {
		System.out.println(Thread.currentThread().getName()+"-Test 3");
		assertEquals(Double.valueOf(625d), calculadora.cuadrado(25d));
	}
	
}

