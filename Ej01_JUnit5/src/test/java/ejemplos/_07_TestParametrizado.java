package ejemplos;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.curso.modelo.entidad.Cliente;
import com.curso.modelo.negocio.Calculadora;
import com.curso.modelo.negocio.CalculadoraImpuestos;
import com.curso.modelo.negocio.GestorClientes;
import com.curso.modelo.negocio.InvalidIngresoException;

public class _07_TestParametrizado {

	private CalculadoraImpuestos calculadoraImpuestos;
	private Calculadora calculadora;
	private GestorClientes gestorClientes;
	
	@BeforeEach
	void inicializar() {
		calculadoraImpuestos = new CalculadoraImpuestos();
		calculadora = new Calculadora();
		gestorClientes = new GestorClientes();
	}

	public _07_TestParametrizado() {
		super();
		System.out.println("Instanciando TestParametrizado");
	}
	
	/*
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-params</artifactId>
		<version>5.5.2</version>
		<scope>test</scope>
	</dependency>	
	*/
	
	@ParameterizedTest
	@ValueSource(doubles = { 5, 25, 50, 75 })
	@DisplayName("Prueba de test parametrizado")
	public void ejemplo(Double numero) {
		//Dado este n�mero
		System.out.println("Par�metro:"+numero);
		//Cuando 
		double resultado = calculadora.cuadrado(2d);
		//Entonces
		Assertions.assertTrue(resultado == 2d*2d);	
		//Lo mismo, con equals
		Assertions.assertEquals(resultado, 2d*2d);	
	}
	
	@ParameterizedTest
	@MethodSource("datosParaProbarInsertarCliente")
	void pruebaInsertarClientes(Cliente cliente) {
		//Cuando
		Cliente cAux = gestorClientes.insertarCliente(cliente);
		//Entonces
		Assertions.assertNotNull(cAux.getId());		
	}
	
	@ParameterizedTest(name = "CalculadoraImpuestos {index}: El impuesto de {0} debe ser {1}")
	@MethodSource("datosParaProbarCalculadoraImpuestos")
	void testWithMultiArgMethodSource(Double ingreso, Double impuesto) throws InvalidIngresoException {
		//Dado
		System.out.println(ingreso+","+impuesto);
		//Cuando
		double resultado = calculadoraImpuestos.calcularImpuestosSobreIngreso(ingreso);
	    //Entonces
		Assertions.assertEquals(impuesto, resultado);
	}

	//Es obligatorio que sea est�tico
	static Stream<Arguments> datosParaProbarCalculadoraImpuestos() {
		return Stream.of(
	        Arguments.arguments(5000d,0d),
	        Arguments.arguments(10000d,800d),
	        Arguments.arguments(17000d,1700d),
	        Arguments.arguments(22000d,3300d),
	        Arguments.arguments(35000d,6825d)
	    );	 
	}

	//Es obligatorio que sea est�tico
	static Stream<Arguments> datosParaProbarInsertarCliente() {
		return Stream.of(
	        Arguments.arguments(new Cliente(null,"N1","D1","T1")),
	        Arguments.arguments(new Cliente(null,"N2","D2","T2")),
	        Arguments.arguments(new Cliente(null,"N3","D3","T3")),
	        Arguments.arguments(new Cliente(null,"N4","D4","T4")),
	        Arguments.arguments(new Cliente(null,"N5","D5","T5"))
	    );	 
	}	

}








