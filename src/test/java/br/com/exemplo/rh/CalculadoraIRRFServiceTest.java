package br.com.exemplo.rh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraIRRFServiceTest {

	private final CalculadoraIRRFService irrfService = new CalculadoraIRRFService();

	@Test
	@DisplayName("Deve retornar imposto zero para salários na faixa de isenção")
	void deveSerIsentoIRRF() {
		double imposto = irrfService.calcular(2000.0, 150.0, 0);
		assertEquals(0.0, imposto);
	}
}