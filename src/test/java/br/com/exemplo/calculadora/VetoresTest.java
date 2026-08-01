package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class VetoresTest {

	private Vetores vetores;

	@BeforeEach
	void iniciar() {

		vetores = new Vetores();

	}

	@Test
	@DisplayName("Deve calcular a soma dos valores do vetor")
	void deveCalcularSoma() {

		int[] numeros = { 10, 20, 30, 40 };

		int resultado = vetores.soma(numeros);

		assertEquals(100, resultado);

	}

	@Test
	@DisplayName("Deve encontrar o maior valor do vetor")
	void deveEncontrarMaiorValor() {

		int[] numeros = { 5, 15, 2, 30, 8 };

		int resultado = vetores.maior(numeros);

		assertEquals(30, resultado);

	}

	@Test
	@DisplayName("Deve encontrar o menor valor do vetor")
	void deveEncontrarMenorValor() {

		int[] numeros = { 5, 15, 2, 30, 8 };

		int resultado = vetores.menor(numeros);

		assertEquals(2, resultado);

	}

	@Test
	@DisplayName("Deve calcular a média do vetor")
	void deveCalcularMedia() {

		int[] numeros = { 10, 20, 30, 40 };

		double resultado = vetores.media(numeros);

		assertEquals(25, resultado, 0.01);

	}

	@Test
	@DisplayName("Deve retornar tamanho do vetor")
	void deveRetornarTamanho() {

		int[] numeros = { 1, 2, 3, 4, 5 };

		int resultado = vetores.tamanho(numeros);

		assertEquals(5, resultado);

	}

	@ParameterizedTest
	@CsvSource({ "10,10", "20,20", "50,50" })
	@DisplayName("Deve retornar o maior valor quando vetor possui um elemento")
	void deveTestarValorUnico(int entrada, int esperado) {

		int[] numeros = { entrada };

		assertEquals(esperado, vetores.maior(numeros));

	}

	@Test
	@DisplayName("Deve trabalhar com números negativos")
	void deveAceitarNumerosNegativos() {

		int[] numeros = { -10, -20, -5 };

		assertEquals(-5, vetores.maior(numeros));

		assertEquals(-20, vetores.menor(numeros));

	}

}
