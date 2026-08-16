package br.com.exemplo.rh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProventoTest {

	@Test
	@DisplayName("Deve calcular total de proventos corretamente")
	void deveCalcularProventos() {
		Provento p = new Provento(200.0, 150.0);
		assertEquals(3350.0, p.calcularTotalProventos(3000.0));
	}

	@Test
	@DisplayName("Deve impedir valores negativos em proventos")
	void deveFalharValoresNegativos() {
		assertThrows(IllegalArgumentException.class, () -> new Provento(-10.0, 0.0));
	}
}