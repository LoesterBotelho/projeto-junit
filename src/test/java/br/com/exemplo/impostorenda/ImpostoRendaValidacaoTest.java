package br.com.exemplo.impostorenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpostoRendaValidacaoTest {

	private final ImpostoRenda impostoRenda = new ImpostoRenda();

	@Test
	void naoDeveCalcularPessoaNula() {

		assertThrows(NullPointerException.class, () -> impostoRenda.calcular(null));
	}

	@Test
	void naoDeveCriarPessoaComSalarioNegativo() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Maria", -500, 0));
	}

	@Test
	void naoDeveCriarPessoaComDependentesNegativos() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Maria", 5000, -1));
	}

	@Test
	void naoDeveAceitarNomeVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("", 5000, 0));
	}

	@Test
	void naoDeveAceitarNomeNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa(null, 5000, 0));
	}

	@Test
	void deveAceitarSalarioZero() {

		assertDoesNotThrow(() -> new Pessoa("Maria", 0, 0));
	}

	@Test
	void deveAceitarSalarioMuitoAlto() {

		assertDoesNotThrow(() -> new Pessoa("Maria", 1_000_000, 0));
	}

	@Test
	void deveAceitarSalarioMinimoDaFaixa() {

		assertDoesNotThrow(() -> new Pessoa("Maria", 2500, 0));
	}
}