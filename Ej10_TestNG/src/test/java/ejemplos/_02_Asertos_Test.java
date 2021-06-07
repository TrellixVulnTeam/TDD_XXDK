package ejemplos;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.curso.modelo.negocio.Calculadora;

public class _02_Asertos_Test {

	@Test
	public void test1() {			

		System.out.println("Test 1");
		
		//dados
		int n1 = 100;
		int n2 = 200;
		
		//cuando
		int n3 = n1 + n2;

		//entonces		
		assertEquals(300, n3);
	
		//Lo mismo, pero sin import est�tico:
		//Assertions.assertEquals(300, n3);
	}
	
	@Test
	public void test2() {	
		System.out.println("Test 2");
		int n1 = 100;
		int n2 = 200;
		assertNotEquals(n1, n2, "No son iguales");	
	}
	
	@Test
	public void test3() {
		System.out.println("Test 3");		
		int n1 = 100;
		int n2 = 200;	
		//Recibe una expresi�n que se resuelve en un boolean
		//Con assertTrue podr�amos hacer cualquier comprobacion
		assertTrue(n2 > n1);		
	}
		
	@Test
	public void test4() {
		System.out.println("Test 4");		
		int n1 = 100;
		int n2 = 200;		
		assertFalse(n2 < n1);		
	}
	
	@Test
	public void test5() throws Exception {
		System.out.println("Test 5");	
		String txt = null;
		
		assertNull(txt);	
	}

	@Test
	public void test6() {
		System.out.println("Test 6");	
		String txt = new String("TXT");
		
		assertNotNull(txt);	
	}
	
	@Test
	public void test7() {
		System.out.println("Test 7");	
		
		Calculadora c1 = new Calculadora();
		Calculadora c2 = c1;
			
		//AssertEquals compara utilizando el  m�todo 'equals'		
		//AssertSame compara utilizando '==', compara LAS REFERENCIAS
		assertSame(c1, c2);	
	}
	
	@Test
	public void test8() {
		System.out.println("Test 8");	
		
		//TestNG no utiliza el '==' para los wrappers
		Integer i1 = 11128; 
		Integer i2 = 11128;
		//assertNotSame(i1, i2);	

		Calculadora c1 = new Calculadora();
		Calculadora c2 = new Calculadora();
		
		assertNotSame(c1, c2);	
	}
	
	@Test
	public void test9() throws Exception {
		System.out.println("Test 9");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};

		/*
		for(int a=0; a<palabras1.length; a++) {
			if(!palabras1[a].equals(palabras2[a])) {
				throw new Exception("No son iguales!!!!");
			}
		}	
		*/
		
		//AsserEquals tambi�n sirve para comparar arrays 
		//assertEquals compara en profundidad
		assertEquals(palabras2, palabras1);	
	}

	@Test
	public void test10() {
		System.out.println("Test 10");
		String[] palabras1 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		String[] palabras2 = new String[] {"HELLO","DOCTOR","NAME","CONTINUE","YESTERDAY","TOMORROW"};
		
		List<String> lista1 = Arrays.asList(palabras1);
		List<String> lista2 = Arrays.asList(palabras2);
		
		//Para comparar Iterables tambi�n utilizamos asserEquals
		assertEquals(lista1, lista2);		
	}
	
	@Test
	public void test11() {		

		System.out.println("Test 11");
		
		boolean condicionDificilDeExpresarConUnAserto = true;
		if(!condicionDificilDeExpresarConUnAserto) {
			//throw new RuntimeException("La liamos parda");
			fail("Test incompleto");
		}
	}

	@Test(testName = "Prueba de softAssert")
	void test12() {
		
		System.out.println("Test 12");		
		
		//Dados
		Calculadora calculadora = new Calculadora();
		double s1 = 5d;
		double s2 = 4d;
			
		//Cuando
		Double sumResult = calculadora.sumar(s1, s2);
		
		//Podr�amos hacer esto, pero al primer aserto que falle se sale del m�todo por la excepci�n que se lanza
		/*
		System.out.println("I");
		assertNotNull(sumResult);
		System.out.println("II");
		assertTrue(sumResult > 10); //A partir de aqui no se ejecuta (se lanza una excepci�n)
		System.out.println("III");
		assertTrue(sumResult < 10);
		System.out.println("IV");
		*/
		
		SoftAssert softAssert = new SoftAssert();
		
		System.out.println("I");
		softAssert.assertNotNull(sumResult);
		System.out.println("II");
		softAssert.assertTrue(sumResult > 10, "No es mayor que 10"); 
		System.out.println("III");
		softAssert.assertTrue(sumResult < 10, "No es menor que 10");		
		System.out.println("IV");
		softAssert.assertTrue(sumResult < 0, "No es menor que 0");		
		System.out.println("V");
		
		softAssert.assertAll();		
	}		
	
	@Test(testName = "Esto solo tiene sentido en linux", enabled = true)
	//Si queremos deshabilitar todos los test de una clase podemos anotarla con @Test( enabled = false)
	public void test13() {
		
		System.out.println("Test 13");
		
		//Precondici�n: Si no se cumple no falla el test
		//La exepcion marca al m�todo como 'skipped'
		if (!System.getProperty("os.name").contains("Linux")) {
			throw new SkipException("Este test no tiene sentido wn Windows");
		}		

		assertNotNull(null);
	}	

}













