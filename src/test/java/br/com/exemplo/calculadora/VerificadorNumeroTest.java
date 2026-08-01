package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class VerificadorNumeroTest {

	private VerificadorNumero verificador;

	@BeforeEach
	void iniciar() {

		verificador = new VerificadorNumero();

	}

	@Test
	@DisplayName("Deve identificar número positivo")
	void deveSerNumeroPositivo() {

		boolean resultado = verificador.positivo(10);

		assertTrue(resultado);

	}

	@Test
	@DisplayName("Deve identificar número negativo")
	void deveSerNumeroNegativo() {

		boolean resultado = verificador.negativo(-10);

		assertTrue(resultado);

	}

	@Test
	@DisplayName("Deve identificar zero")
	void deveSerZero() {

		boolean resultado = verificador.zero(0);

		assertTrue(resultado);

	}

	@ParameterizedTest
	@CsvSource({ "2", "4", "10", "100" })
	@DisplayName("Deve identificar números pares")
	void deveSerNumeroPar(int numero) {

		assertTrue(verificador.par(numero));

	}

	@ParameterizedTest
	@CsvSource({ "1", "3", "5", "99" })
	@DisplayName("Deve identificar números ímpares")
	void deveSerNumeroImpar(int numero) {

		assertTrue(verificador.impar(numero));

	}

	@Test
	@DisplayName("Número positivo não deve ser negativo")
	void positivoNaoPodeSerNegativo() {

		assertFalse(verificador.negativo(20));

	}

	@Test
	@DisplayName("Número negativo não deve ser positivo")
	void negativoNaoPodeSerPositivo() {

		assertFalse(verificador.positivo(-20));

	}

	@Test
	@DisplayName("Zero não deve ser positivo nem negativo")
	void zeroNaoDeveSerPositivoNemNegativo() {

		assertFalse(verificador.positivo(0));

		assertFalse(verificador.negativo(0));

	}

}
