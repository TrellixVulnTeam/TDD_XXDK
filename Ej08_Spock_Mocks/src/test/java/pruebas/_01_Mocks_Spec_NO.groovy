package pruebas

import com.curso.modelo.negocio.excepcion.SucursalException
import com.curso.modelo.entidad.Cliente
import com.curso.modelo.entidad.Comercial
import com.curso.modelo.entidad.Sucursal
import com.curso.modelo.negocio.GestorClientes
import com.curso.modelo.negocio.GestorComerciales
import com.curso.modelo.negocio.GestorDirecciones
import com.curso.modelo.negocio.GestorSucursales
import com.curso.modelo.negocio.excepcion.DireccionException
import com.curso.modelo.persistencia.ClienteDao

import spock.lang.Specification

//
//En este ejemplo definimos los mocks en m�todos independientes
//Cada mock tiene definido su comportamiento completo
//Esto implica que no se usan todos los metodos de los mocks 
//en cada prueba individual
//Tambien implica que no podemos hacer el verify de los m�todos
//invocados, ni el n�mero de veces ni el orden
//
class _01_Mocks_Spec_NO extends Specification {

	//Test doubles:
	//
	//dummies: un objeto que para los metodos void no hace nada
	//         y para los que devuelven algo devuelve:
	//      	  -cero si es n�mero
	//        	  -false si es boolean
	//        	  -null si es referencia
	//stubs: un objeto que cuenta con una serie de respuestas enlatadas para determinados m�todos
	//fakes: un objeto programado por nosotros y que reproduce el comportamiento del objeto real
	//       un fake se programa de verdad!
	//mocks: un objeto que recuerda las llamadas que ha recibido, el orden de las mismas y el n�mero de veces
	
	GestorClientes gestorClientes
	GestorSucursales gestorSucursales
	GestorComerciales gestorComerciales
	ClienteDao clienteDao
	
	def GestorComerciales getGestorComercialesMock() {
		//Aqui es un dummie
		gestorComerciales = Mock(GestorComerciales)
		//Ahora es un stub
		gestorComerciales
			.encontrarComerciales() >> [ new Comercial(1,"EMP-1","Comercial1"),
										 new Comercial(2,"EMP-2","Comercial2") ] 
		return gestorComerciales
	}
	
	def GestorSucursales getGestorSucursalesMock() {
		//gestorSucursales = Mock(GestorSucursales.class)
		//Cuando el tipo esperado es Class, no hace falta invocar .class:
		gestorSucursales = Mock(GestorSucursales)
					
		gestorSucursales
			.encontrarSucursalCercana(null) >> 
				//Lanzando una excepci�n en una situaci�n concreta
				//Se hace con un closure
				{ direccion -> throw new DireccionException("Direcci�n nula") }
		
		gestorSucursales
			.encontrarSucursalCercana("C/Falsa, 123") >> 
				//Si en el cuerpo del closure no se usan los par�metros no los ponemos y punto:
				//{ direccion -> throw new DireccionException("Esta direccion es falsa") }
				{ throw new DireccionException("Esta direccion es falsa") }
		
		//El orden es de caso m�s espec�fico a caso m�s generico!!!!
		//Pasando al mock cualquier valor del tipo adecuado
		gestorSucursales
			.encontrarSucursalCercana(_) >> new Sucursal(1,"Sucursal1","C/Tal,123")
				
		return gestorSucursales
	}

	def ClienteDao getClienteDaoMock() {
		//Para hacer mocks de interfaces no necesitamos ninguna librer�a espec�fica:
		//ya se hace en java desde la 1.6
		clienteDao = Mock(ClienteDao.class)
		//Pasando al mock un valor variable del tipo adecuado
		//A�adiendo un closure como cuerpo del m�todo
		clienteDao
			.insertar(_) >> {
					Cliente cli ->
						cli.id = 1
						cli //return impl�cito, para disfrutar
				}
				
		return clienteDao
	}
	
	def setup() {
		gestorClientes = new GestorClientes()
		gestorClientes.gestorComerciales = getGestorComercialesMock()
		gestorClientes.gestorSucursales  = getGestorSucursalesMock()
		gestorClientes.clienteDao = getClienteDaoMock()
	}
	
	def "un cliente con datos correctos se inserta correctamente"() {
		
		given:
			def cliente = new Cliente(null,"Nombre","Direccion","Telefono");

		when:			
			def clienteInsertado = gestorClientes.altaCliente(cliente)	
		
		then:
			clienteInsertado.sucursal.nombre == "Sucursal1"
			clienteInsertado.id == 1
			clienteInsertado.comerciales.size() == 2
	}
	
	//Quiz�s deber�an ser dos test separados...
	def "un cliente con datos incorrectos no se inserta"() {

		
		when: "Cliente con direcci�n nula"
		
			def cliente = new Cliente(null,"Nombre",null,"Telefono");
			def clienteInsertado = gestorClientes.altaCliente(cliente)
		
		then:
			Exception e = thrown(Exception) 
			e.message == "Direcci�n nula"
			clienteInsertado == null
		
		when: "Cliente con direcci�n no existente"
		
			cliente = new Cliente(null,"Nombre","C/Falsa, 123","Telefono");
			clienteInsertado = gestorClientes.altaCliente(cliente)
			
		then:
			e = thrown(Exception) 
			e.message == "Esta direccion es falsa"
			clienteInsertado == null
	}

}
