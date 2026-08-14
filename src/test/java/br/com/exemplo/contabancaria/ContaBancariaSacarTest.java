package br.com.exemplo.contabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaSacarTest {

	@Test
	void deveSacarValor() {
		ContaBancaria conta = new ContaBancaria(200);

		conta.sacar(50);

		assertEquals(150, conta.getSaldo(), 0.001);
	}

	@Test
	void deveSacarTodoSaldo() {
		ContaBancaria conta = new ContaBancaria(200);

		conta.sacar(200);

		assertEquals(0, conta.getSaldo(), 0.001);
	}

	@Test
	void naoDeveSacarSemSaldo() {
		ContaBancaria conta = new ContaBancaria(100);

		assertThrows(IllegalStateException.class, () -> conta.sacar(150));
	}

	@Test
	void naoDevePermitirSaqueNegativo() {
		ContaBancaria conta = new ContaBancaria(100);

		assertThrows(IllegalArgumentException.class, () -> conta.sacar(-10));
	}

	@Test
	void naoDevePermitirSaqueZero() {
		ContaBancaria conta = new ContaBancaria(100);

		assertThrows(IllegalArgumentException.class, () -> conta.sacar(0));
	}

	@Test
	void deveSacarCentavos() {
		ContaBancaria conta = new ContaBancaria(100);

		conta.sacar(15.75);

		assertEquals(84.25, conta.getSaldo(), 0.001);
	}

	@Test
	void deveRealizarVariosSaques() {
		ContaBancaria conta = new ContaBancaria(100);

		conta.sacar(10);
		conta.sacar(20);
		conta.sacar(30);

		assertEquals(40, conta.getSaldo(), 0.001);
	}

}