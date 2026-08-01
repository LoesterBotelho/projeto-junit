package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraDescontoTest {

	private CalculadoraDesconto calculadora;

	@BeforeEach
	void iniciar() {

		calculadora = new CalculadoraDesconto();

	}

	@Test
	@DisplayName("Deve calcular desconto de 10%")
	void deveCalcularDesconto() {

		double desconto = calculadora.calcularDesconto(1000, 10);

		assertEquals(100, desconto, 0.01);

	}

	@Test
	@DisplayName("Deve calcular valor final com desconto")
	void deveCalcularValorFinal() {

		double valorFinal = calculadora.valorFinal(1000, 10);

		assertEquals(900, valorFinal, 0.01);

	}

	@Test
	@DisplayName("Deve testar vários descontos")
	void deveTestarVariosDescontos() {

		assertAll(

				() -> assertEquals(50, calculadora.calcularDesconto(500, 10), 0.01),

				() -> assertEquals(200, calculadora.calcularDesconto(1000, 20), 0.01),

				() -> assertEquals(300, calculadora.calcularDesconto(1500, 20), 0.01)

		);

	}

	@Test
	@DisplayName("Deve retornar o mesmo valor quando desconto é zero")
	void deveAceitarDescontoZero() {

		double resultado = calculadora.valorFinal(500, 0);

		assertEquals(500, resultado, 0.01);

	}

}