package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

	@Test
	@DisplayName("Deve somar dois números")
	void deveSomarDoisNumeros() {

		Calculadora calculadora = new Calculadora();

		int resultado = calculadora.somar(10, 20);

		assertEquals(30, resultado);

	}

	@Test
	@DisplayName("Deve subtrair dois números")
	void deveSubtrairDoisNumeros() {

		Calculadora calculadora = new Calculadora();

		int resultado = calculadora.subtrair(20, 5);

		assertEquals(15, resultado);

	}

	@Test
	@DisplayName("Deve multiplicar dois números")
	void deveMultiplicarDoisNumeros() {

		Calculadora calculadora = new Calculadora();

		int resultado = calculadora.multiplicar(5, 4);

		assertEquals(20, resultado);

	}

	@Test
	@DisplayName("Deve dividir dois números")
	void deveDividirDoisNumeros() {

		Calculadora calculadora = new Calculadora();

		double resultado = calculadora.dividir(20, 5);

		assertEquals(4, resultado);

	}

	@Test
	@DisplayName("Não deve permitir divisão por zero")
	void naoDeveDividirPorZero() {

		Calculadora calculadora = new Calculadora();

		assertThrows(IllegalArgumentException.class, () -> calculadora.dividir(10, 0));

	}

}
