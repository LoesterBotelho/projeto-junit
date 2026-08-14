package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CalculadoraJurosTest {

	private CalculadoraJuros calculadora;

	@BeforeEach
	void iniciar() {

		calculadora = new CalculadoraJuros();

	}

	@Nested
	@DisplayName("Testes de Juros Simples")
	class JurosSimplesTest {

		@Test
		@DisplayName("Deve calcular juros simples")
		void deveCalcularJurosSimples() {

			double resultado = calculadora.jurosSimples(1000, 0.10, 2);

			assertEquals(200, resultado, 0.01);

		}

		@Test
		@DisplayName("Deve calcular vários juros simples")
		void deveTestarVariacoesJurosSimples() {

			assertAll(

					() -> assertEquals(100, calculadora.jurosSimples(1000, 0.10, 1), 0.01),

					() -> assertEquals(500, calculadora.jurosSimples(5000, 0.10, 1), 0.01)

			);

		}

	}

	@Nested
	@DisplayName("Testes de Juros Compostos")
	class JurosCompostosTest {

		@Test
		@DisplayName("Deve calcular juros compostos")
		void deveCalcularJurosCompostos() {

			double resultado = calculadora.jurosCompostos(1000, 0.10, 2);

			assertEquals(1210, resultado, 0.01);

		}

		@Test
		@DisplayName("Deve calcular juros compostos com outro valor")
		void deveCalcularSegundoExemplo() {

			double resultado = calculadora.jurosCompostos(2000, 0.05, 3);

			assertEquals(2315.25, resultado, 0.01);

		}

	}

}