package br.com.exemplo.rh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescontoTest {

	@Test
	@DisplayName("Deve somar corretamente outros descontos")
	void deveSomarDescontos() {
		Desconto d = new Desconto(50.0, 150.0);
		assertEquals(200.0, d.getTotalOutrosDescontos());
	}

	@Test
	@DisplayName("Deve rejeitar descontos negativos")
	void deveRejeitarDescontoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Desconto(-5.0, 0.0));
	}
}