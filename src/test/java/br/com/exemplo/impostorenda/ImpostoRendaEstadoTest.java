package br.com.exemplo.impostorenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpostoRendaEstadoTest {

	private final ImpostoRenda impostoRenda = new ImpostoRenda();

	@Test
	void deveCalcularDuasPessoasIndependentes() {

		Pessoa maria = new Pessoa("Maria", 3000, 0);

		Pessoa joao = new Pessoa("João", 5000, 0);

		ResultadoImposto resultadoMaria = impostoRenda.calcular(maria);

		ResultadoImposto resultadoJoao = impostoRenda.calcular(joao);

		assertAll(() -> assertEquals(225, resultadoMaria.getImposto(), 0.001),

				() -> assertEquals(750, resultadoJoao.getImposto(), 0.001));
	}

	@Test
	void deveCalcularNovamenteSemManterEstadoAnterior() {

		Pessoa maria = new Pessoa("Maria", 3000, 0);

		ResultadoImposto primeiro = impostoRenda.calcular(maria);

		ResultadoImposto segundo = impostoRenda.calcular(maria);

		assertEquals(primeiro.getImposto(), segundo.getImposto(), 0.001);
	}

	@Test
	void resultadosDePessoasDiferentesDevemSerDiferentes() {

		Pessoa maria = new Pessoa("Maria", 3000, 0);

		Pessoa joao = new Pessoa("João", 6000, 0);

		ResultadoImposto resultadoMaria = impostoRenda.calcular(maria);

		ResultadoImposto resultadoJoao = impostoRenda.calcular(joao);

		assertNotEquals(resultadoMaria.getImposto(), resultadoJoao.getImposto());
	}

	@Test
	void deveManterPrecisaoDoCalculo() {

		Pessoa pessoa = new Pessoa("Maria", 3333.33, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertEquals(249.99975, resultado.getImposto(), 0.001);
	}

	@Test
	void naoDeveAlterarPessoaDuranteCalculo() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 2);

		impostoRenda.calcular(pessoa);

		assertAll(() -> assertEquals("Maria", pessoa.getNome()),

				() -> assertEquals(5000, pessoa.getSalarioMensal()),

				() -> assertEquals(2, pessoa.getDependentes()));
	}

	@Test
	void deveCriarNovoResultado() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 0);

		ResultadoImposto resultado = impostoRenda.calcular(pessoa);

		assertNotNull(resultado);
	}

	@Test
	void resultadosDevemSerObjetosDiferentes() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 0);

		ResultadoImposto primeiro = impostoRenda.calcular(pessoa);

		ResultadoImposto segundo = impostoRenda.calcular(pessoa);

		assertNotSame(primeiro, segundo);
	}

	@Test
	void deveManterObjetosIndependentes() {

		Pessoa maria = new Pessoa("Maria", 5000, 0);

		Pessoa joao = new Pessoa("João", 10000, 0);

		ResultadoImposto resultadoMaria = impostoRenda.calcular(maria);

		ResultadoImposto resultadoJoao = impostoRenda.calcular(joao);

		assertAll(() -> assertEquals(750, resultadoMaria.getImposto(), 0.001),

				() -> assertEquals(2750, resultadoJoao.getImposto(), 0.001));
	}
}