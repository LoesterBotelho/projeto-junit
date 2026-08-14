package br.com.exemplo.impostorenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaixaImpostoTest {

	@Test
	void deveCriarFaixa() {

		FaixaImposto faixa = new FaixaImposto(2500, 4000, 0.075);

		assertAll(() -> assertEquals(2500, faixa.getSalarioMinimo()),
				() -> assertEquals(4000, faixa.getSalarioMaximo()), () -> assertEquals(0.075, faixa.getAliquota()));
	}

	@Test
	void deveAceitarAliquotaZero() {

		FaixaImposto faixa = new FaixaImposto(0, 2500, 0);

		assertEquals(0, faixa.getAliquota());
	}

	@Test
	void naoDevePermitirSalarioMinimoNegativo() {

		assertThrows(IllegalArgumentException.class, () -> new FaixaImposto(-1, 2500, 0.075));
	}

	@Test
	void naoDevePermitirMaximoMenorQueMinimo() {

		assertThrows(IllegalArgumentException.class, () -> new FaixaImposto(4000, 2500, 0.075));
	}

	@Test
	void naoDevePermitirAliquotaInvalida() {

		assertThrows(IllegalArgumentException.class, () -> new FaixaImposto(2500, 4000, 1.5));
	}

	@Test
	void deveGerarToString() {

		FaixaImposto faixa = new FaixaImposto(2500, 4000, 0.075);

		assertNotNull(faixa.toString());
	}
}