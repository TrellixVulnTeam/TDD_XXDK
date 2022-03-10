package com.curso.modelo.negocio;

import com.curso.modelo.negocio.excepcion.DireccionException;

public class GestorDirecciones {

	public GestorDirecciones() {
	}
	
	public void comprobarDireccion(String direccion) throws DireccionException {
		
		if(direccion==null) {
			throw new DireccionException("Direcci�n nula");
		}
		
		//Simulamos una conexi�n a una base de datos de direcciones 
		if(direccion.toUpperCase().contains("FALSA")) {
			throw new DireccionException("Esta direccion es falsa");
		}
		
		//Y si llegamos hasta aqui la direcci�n es correcta.
	}

}

/*
package com.curso.modelo.negocio;

import com.curso.modelo.negocio.excepcion.DireccionException;

public class GestorDirecciones_Dummie extends GestorDirecciones {
	
	public void comprobarDireccion(String direccion) throws DireccionException {
	}

}
*/
