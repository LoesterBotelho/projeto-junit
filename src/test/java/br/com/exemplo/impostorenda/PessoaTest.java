package br.com.exemplo.impostorenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

	@Test
	void deveCriarPessoa() {

		Pessoa pessoa = new Pessoa("Maria", 5000, 2);

		assertAll(() -> assertEquals("Maria", pessoa.getNome()), () -> assertEquals(5000, pessoa.getSalarioMensal()),
				() -> assertEquals(2, pessoa.getDependentes()));
	}

	@Test
	void deveAceitarSalarioZero() {

		Pessoa pessoa = new Pessoa("Maria", 0, 0);

		assertEquals(0, pessoa.getSalarioMensal());
	}

	@Test
	void deveAceitarPessoaSemDependentes() {

		Pessoa pessoa = new Pessoa("João", 3000, 0);

		assertEquals(0, pessoa.getDependentes());
	}

	@Test
	void naoDevePermitirNomeNulo() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa(null, 3000, 0));
	}

	@Test
	void naoDevePermitirNomeVazio() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("", 3000, 0));
	}

	@Test
	void naoDevePermitirSalarioNegativo() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Maria", -1, 0));
	}

	@Test
	void naoDevePermitirDependentesNegativos() {

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Maria", 3000, -1));
	}

	@Test
	void deveGerarToString() {

		Pessoa pessoa = new Pessoa("Maria", 3000, 1);

		assertNotNull(pessoa.toString());
	}
}