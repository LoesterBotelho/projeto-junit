package br.com.exemplo.contabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaDepositarTest {

	@Test
	void deveDepositarValor() {
		ContaBancaria conta = new ContaBancaria(100);

		conta.depositar(50);

		assertEquals(150, conta.getSaldo(), 0.001);
	}

	@Test
	void deveDepositarCentavos() {
		ContaBancaria conta = new ContaBancaria(10);

		conta.depositar(0.75);

		assertEquals(10.75, conta.getSaldo(), 0.001);
	}

	@Test
	void deveDepositarValorGrande() {
		ContaBancaria conta = new ContaBancaria(0);

		conta.depositar(1_000_000);

		assertEquals(1_000_000, conta.getSaldo(), 0.001);
	}

	@Test
	void naoDevePermitirDepositoZero() {
		ContaBancaria conta = new ContaBancaria(100);

		assertThrows(IllegalArgumentException.class, () -> conta.depositar(0));
	}

	@Test
	void naoDevePermitirDepositoNegativo() {
		ContaBancaria conta = new ContaBancaria(100);

		assertThrows(IllegalArgumentException.class, () -> conta.depositar(-50));
	}

	@Test
	void deveRealizarVariosDepositos() {
		ContaBancaria conta = new ContaBancaria(0);

		conta.depositar(10);
		conta.depositar(20);
		conta.depositar(30);

		assertEquals(60, conta.getSaldo(), 0.001);
	}

}