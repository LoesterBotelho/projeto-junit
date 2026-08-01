package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BhaskaraTest {

	private Bhaskara bhaskara;

	@BeforeEach
	void iniciar() {

		bhaskara = new Bhaskara();

	}

	@Test
	@DisplayName("Deve calcular o delta corretamente")
	void deveCalcularDelta() {

		double resultado = bhaskara.calcularDelta(1, -5, 6);

		assertEquals(1, resultado);

	}

	@Test
	@DisplayName("Deve calcular a primeira raiz")
	void deveCalcularX1() {

		double resultado = bhaskara.calcularX1(1, -5, 6);

		assertEquals(3, resultado);

	}

	@Test
	@DisplayName("Deve calcular a segunda raiz")
	void deveCalcularX2() {

		double resultado = bhaskara.calcularX2(1, -5, 6);

		assertEquals(2, resultado);

	}

	@Test
	@DisplayName("Deve lançar erro quando delta for negativo")
	void deveLancarErroSemRaizReal() {

		assertThrows(IllegalArgumentException.class, () -> bhaskara.calcularX1(1, 2, 10));

	}

	@Test
	@DisplayName("Equação completa x² - 5x + 6 deve possuir duas raízes")
	void deveResolverEquacaoCompleta() {

		double x1 = bhaskara.calcularX1(1, -5, 6);

		double x2 = bhaskara.calcularX2(1, -5, 6);

		assertEquals(3, x1);

		assertEquals(2, x2);

	}

}