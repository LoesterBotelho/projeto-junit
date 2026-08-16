package br.com.exemplo.rh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CalculadoraINSSServiceTest {

	private final CalculadoraINSSService inssService = new CalculadoraINSSService();

	@DisplayName("Deve calcular o INSS corretamente baseado nas faixas salariais")
	@ParameterizedTest(name = "Salário Bruto: R$ {0} -> INSS Esperado: R$ {1}")
	@CsvSource({ "1000.00, 75.00", "2000.00, 180.00", "3000.00, 360.00" })
	void deveCalcularINSSPorFaixa(double salarioBruto, double inssEsperado) {
		double resultado = inssService.calcular(salarioBruto);
		assertEquals(inssEsperado, resultado, 0.01);
	}
}