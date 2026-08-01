package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ValidadorCPFTest {

	private ValidadorCPF validador;

	@BeforeEach
	void iniciar() {

		validador = new ValidadorCPF();

	}

	@Test
	@DisplayName("Deve validar CPF correto")
	void deveValidarCPFValido() {

		boolean resultado = validador.validar("52998224725");

		assertTrue(resultado);

	}

	@ParameterizedTest
	@ValueSource(strings = { "11111111111", "22222222222", "00000000000", "123", "", "abcdefghijk" })
	@DisplayName("Não deve aceitar CPFs inválidos")
	void naoDeveAceitarCPFInvalido(String cpf) {

		boolean resultado = validador.validar(cpf);

		assertFalse(resultado);

	}

	@Test
	@DisplayName("Não deve aceitar CPF com quantidade errada de números")
	void naoDeveAceitarQuantidadeInvalida() {

		assertFalse(validador.validar("123456789"));

	}

	@Test
	@DisplayName("Não deve aceitar CPF nulo")
	void naoDeveAceitarCPFNull() {

		assertFalse(validador.validar(null));

	}

}
