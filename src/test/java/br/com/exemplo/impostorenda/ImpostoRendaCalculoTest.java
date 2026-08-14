package br.com.exemplo.impostorenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpostoRendaCalculoTest {

	private final ImpostoRenda impostoRenda = new ImpostoRenda();

	@Test
	void deveSerIsentoAte2500() {

		Pessoa pessoa = new Pessoa("Maria", 2500, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals(2500, resultado.getBaseCalculo()), () -> assertEquals(0, resultado.getAliquota()),
				() -> assertEquals(0, resultado.getImposto()));
	}

	@Test
	void deveCalcularAliquotaDeSeteEMeioPorcento() {

		Pessoa pessoa = new Pessoa("Maria", 3000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals(0.075, resultado.getAliquota()),
				() -> assertEquals(225, resultado.getImposto(), 0.001));
	}

	@Test
	void deveCalcularAliquotaDeQuinzePorcento() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals(0.15, resultado.getAliquota()),
				() -> assertEquals(750, resultado.getImposto(), 0.001));
	}

	@Test
	void deveCalcularAliquotaDeVinteEDoisEMeioPorcento() {

		Pessoa pessoa = new Pessoa("Maria", 7000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals(0.225, resultado.getAliquota()),
				() -> assertEquals(1575, resultado.getImposto(), 0.001));
	}

	@Test
	void deveCalcularAliquotaDeVinteESeteEMeioPorcento() {

		Pessoa pessoa = new Pessoa("Maria", 10000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals(0.275, resultado.getAliquota()),
				() -> assertEquals(2750, resultado.getImposto(), 0.001));
	}

	@Test
	void deveAplicarDescontoDeDependentes() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 2);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals(500, resultado.getDescontoDependentes()),
				() -> assertEquals(4500, resultado.getBaseCalculo()), () -> assertEquals(0.15, resultado.getAliquota()),
				() -> assertEquals(675, resultado.getImposto(), 0.001));
	}

	@Test
	void deveAceitarMuitosDependentes() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 10);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertEquals(2500, resultado.getBaseCalculo());
		assertEquals(0, resultado.getImposto());
	}

	@Test
	void deveCalcularSalarioAnual() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertEquals(60000, resultado.getSalarioAnual());
	}

	@Test
	void salarioZeroDeveSerIsento() {

		Pessoa pessoa = new Pessoa("Maria", 0, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertEquals(0, resultado.getImposto());
	}

	@Test
	void deveCalcularCentavos() {

		Pessoa pessoa = new Pessoa("Maria", 3000.50, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertEquals(225.0375, resultado.getImposto(), 0.001);
	}

	@Test
	void deveCalcularValorAlto() {

		Pessoa pessoa = new Pessoa("Maria", 100000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertEquals(27500, resultado.getImposto(), 0.001);
	}
}