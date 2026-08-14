package br.com.exemplo.impostorenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoImpostoTest {

	@Test
	void deveCriarResultado() {

		ResultadoImposto resultado = new ResultadoImposto(5000, 60000, 500, 4500, 675, 0.15);

		assertAll(() -> assertEquals(5000, resultado.getSalarioBruto()),
				() -> assertEquals(60000, resultado.getSalarioAnual()),
				() -> assertEquals(500, resultado.getDescontoDependentes()),
				() -> assertEquals(4500, resultado.getBaseCalculo()), () -> assertEquals(675, resultado.getImposto()),
				() -> assertEquals(0.15, resultado.getAliquota()));
	}

	@Test
	void deveRetornarSalarioBruto() {

		ResultadoImposto resultado = new ResultadoImposto(3000, 36000, 0, 3000, 225, 0.075);

		assertEquals(3000, resultado.getSalarioBruto());
	}

	@Test
	void deveRetornarSalarioAnual() {

		ResultadoImposto resultado = new ResultadoImposto(3000, 36000, 0, 3000, 225, 0.075);

		assertEquals(36000, resultado.getSalarioAnual());
	}

	@Test
	void deveRetornarBaseCalculo() {

		ResultadoImposto resultado = new ResultadoImposto(5000, 60000, 500, 4500, 675, 0.15);

		assertEquals(4500, resultado.getBaseCalculo());
	}

	@Test
	void deveRetornarImposto() {

		ResultadoImposto resultado = new ResultadoImposto(5000, 60000, 0, 5000, 750, 0.15);

		assertEquals(750, resultado.getImposto());
	}

	@Test
	void deveGerarToString() {

		ResultadoImposto resultado = new ResultadoImposto(5000, 60000, 0, 5000, 750, 0.15);

		assertNotNull(resultado.toString());
	}
}