package br.com.exemplo.projeto;

import org.junit.jupiter.api.Test;

import br.com.exemplo.projeto.MembroEquipe;

import static org.junit.jupiter.api.Assertions.*;

class MembroEquipeTest {

	@Test
	void deveCriarMembroComEmailValido() {
		MembroEquipe m = new MembroEquipe("1", "Loester", "loester@exemplo.com");
		assertEquals("loester@exemplo.com", m.getEmail());
	}

	@Test
	void deveLancarExcecaoParaEmailInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new MembroEquipe("2", "Teste", "emailsemarroba.com"));
	}
}