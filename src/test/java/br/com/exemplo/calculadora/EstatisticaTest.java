package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EstatisticaTest {

	private Estatistica estatistica;

	@BeforeEach
	void iniciar() {

		estatistica = new Estatistica();

	}

	@Test
	@DisplayName("Deve calcular a média dos valores")
	void deveCalcularMedia() {

		int[] valores = { 10, 20, 30, 40, 50 };

		double resultado = estatistica.media(valores);

		assertEquals(30, resultado, 0.01);

	}

	@Test
	@DisplayName("Deve encontrar o maior valor")
	void deveEncontrarMaiorValor() {

		int[] valores = { 5, 10, 3, 8, 20 };

		int resultado = estatistica.maior(valores);

		assertEquals(20, resultado);

	}

	@Test
	@DisplayName("Deve encontrar o menor valor")
	void deveEncontrarMenorValor() {

		int[] valores = { 5, 10, 3, 8, 20 };

		int resultado = estatistica.menor(valores);

		assertEquals(3, resultado);

	}

	@Test
	@DisplayName("Deve calcular a mediana de quantidade ímpar")
	void deveCalcularMedianaImpar() {

		int[] valores = { 5, 1, 9, 3, 7 };

		double resultado = estatistica.mediana(valores);

		assertEquals(5, resultado);

	}

	@Test
	@DisplayName("Deve calcular a mediana de quantidade par")
	void deveCalcularMedianaPar() {

		int[] valores = { 10, 20, 30, 40 };

		double resultado = estatistica.mediana(valores);

		assertEquals(25, resultado);

	}

	@Test
	@DisplayName("Deve validar vários resultados juntos")
	void deveValidarTodosResultados() {

		int[] valores = { 10, 20, 30, 40, 50 };

		assertAll(

				() -> assertEquals(30, estatistica.media(valores), 0.01),

				() -> assertEquals(50, estatistica.maior(valores)),

				() -> assertEquals(10, estatistica.menor(valores))

		);

	}

	@Test
	@DisplayName("Não deve aceitar lista vazia")
	void naoDeveAceitarListaVazia() {

		int[] valores = {};

		assertThrows(IllegalArgumentException.class, () -> estatistica.media(valores));

	}

	@Test
	@DisplayName("Não deve aceitar array nulo")
	void naoDeveAceitarArrayNulo() {

		assertThrows(IllegalArgumentException.class, () -> estatistica.maior(null));

	}

}