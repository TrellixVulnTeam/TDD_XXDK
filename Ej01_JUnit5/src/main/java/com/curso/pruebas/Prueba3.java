package com.curso.pruebas;

import com.curso.modelo.negocio.Calculadora;

public class Prueba3 implements Prueba {

	//TODO: Este tendr�a que probar 'dividir'
	public void test() {
		
		Calculadora c = new Calculadora();
		
		double resultado = c.sumar(10d,20d);
		
		if(resultado == 30d) {
			System.out.println("calculadora.sumar OK");
		} else {
			System.out.println("calculadora.sumar ZASCA!");
		}		
		
	}	
	
}
