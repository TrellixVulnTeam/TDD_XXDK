package ejemplos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.modelo.negocio.Calculadora;
import com.curso.modelo.negocio.CalculadoraException;
import com.curso.modelo.negocio.CalculadoraPrueba;

public class _05_Excepciones {

	private Calculadora calculadora;

	public _05_Excepciones() {
		super();
		System.out.println("Instanciando 05_Excepciones");
	}

	@BeforeEach
	public void beforeEach() {
		calculadora = new Calculadora();
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("After all");
	}

	@Test
	public void dividirFuncionaCorrectamente() {
		double dividendo = 50;
		double divisor = 2;
		
		try {
			double rs = calculadora.dividir(dividendo, divisor);
			assertEquals(25, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	//Excepciones
	@Test
	public void divisionPorCeroDebeLanzarExcepcion() {
		
		double dividendo = 100;
		double divisor   = 0;
		
		//Podr�amos controlarlo nosotros:
		/*
		try {
			calculadora.dividir(dividendo, divisor);
			//MAL
			Assertions.fail("Pues no ha lanzado la excepci�n!");
		} catch (CalculadoraException e) {
			System.out.println("OK");
			//OK, Esto es lo que esperabamos!
		} catch(Exception e) {
			Assertions.fail("Pues ha lanzado OTRA excepci�n!");			
		}
		*/
		
		/*
		Assertions.assertThrows(CalculadoraException.class, new Executable() {
			public void execute() throws Throwable {
				calculadora.dividir(dividendo, divisor);				
			}
		});
		 */
		
		/*
		Assertions.assertThrows(CalculadoraException.class,  
			                    () -> calculadora.dividir(dividendo, divisor));
		*/

		
		//Nos entregan la excepci�n que se ha lanzado por si nos hace falta
		CalculadoraException e = 
			Assertions.assertThrows(CalculadoraException.class,  
                                    () -> calculadora.dividir(dividendo, divisor));	
		assertEquals("Divisi�n por cero", e.getMessage());
		
	}
	
	
}















		/*
		try {
			calculadora.dividir(10d, 10d);
			fail("ZASCA");
		} catch (Exception e) {
			//Me pongo un visto
		}
		*/
		
		//Con clase interna an�nima
		/*
		assertThrows(Exception.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				calculadora.dividir(10d, 0d);
			}
		});
		*/
		
		//Con expresiones lambda
		//assertThrows(Exception.class, () -> calculadora.dividir(10d, 0d));	
	
	/*
	}

}
*/


















