package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraIMCTest {

	private CalculadoraIMC calculadora;

	@BeforeEach
	void iniciar() {

		calculadora = new CalculadoraIMC();

	}

	@Test
	@DisplayName("Deve calcular o IMC corretamente")
	void deveCalcularIMC() {

		double resultado = calculadora.calcular(80, 1.80);

		assertEquals(24.69, resultado, 0.01);

	}

	@Test
	@DisplayName("Deve classificar abaixo do peso")
	void deveClassificarAbaixoDoPeso() {

		assertEquals("Abaixo do peso", calculadora.classificacao(17));

	}

	@Test
	@DisplayName("Deve classificar peso normal")
	void deveClassificarPesoNormal() {

		assertEquals("Peso normal", calculadora.classificacao(22));

	}

	@Test
	@DisplayName("Deve classificar sobrepeso")
	void deveClassificarSobrepeso() {

		assertEquals("Sobrepeso", calculadora.classificacao(27));

	}

	@Test
	@DisplayName("Deve classificar obesidade")
	void deveClassificarObesidade() {

		assertEquals("Obesidade", calculadora.classificacao(35));

	}

	@Test
	@DisplayName("Não deve aceitar altura zero")
	void naoDeveAceitarAlturaZero() {

		assertThrows(IllegalArgumentException.class, () -> calculadora.calcular(80, 0));

	}

}
