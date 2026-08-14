package br.com.exemplo.ecommerce;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraFreteTest {

	@ParameterizedTest
	@CsvSource({ "2.0, SC, 25.0", "1.0, SP, 30.0", "4.0, AM, 60.0" })
	void deveCalcularFreteCorretamente(double peso, String estado, double esperado) {
		assertEquals(esperado, CalculadoraFrete.calcular(peso, estado));
	}
}