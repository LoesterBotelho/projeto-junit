package br.com.exemplo.contabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaEstadoTest {

	@Test
	void deveIniciarComSaldo() {

		ContaBancaria conta = new ContaBancaria(500);

		assertEquals(500, conta.getSaldo(), 0.001);
	}

	@Test
	void deveIniciarComSaldoZero() {

		ContaBancaria conta = new ContaBancaria(0);

		assertEquals(0, conta.getSaldo(), 0.001);
	}

	@Test
	void naoDeveCriarContaComSaldoNegativo() {

		assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(-1));
	}

	@Test
	void depositoDeveAlterarSaldo() {

		ContaBancaria conta = new ContaBancaria(100);

		conta.depositar(50);

		assertEquals(150, conta.getSaldo(), 0.001);
	}

	@Test
	void saqueDeveAlterarSaldo() {

		ContaBancaria conta = new ContaBancaria(100);

		conta.sacar(40);

		assertEquals(60, conta.getSaldo(), 0.001);
	}

	@Test
	void transferenciaDeveAlterarAsDuasContas() {

		ContaBancaria origem = new ContaBancaria(300);
		ContaBancaria destino = new ContaBancaria(50);

		origem.transferir(destino, 100);

		assertAll(() -> assertEquals(200, origem.getSaldo(), 0.001),
				() -> assertEquals(150, destino.getSaldo(), 0.001));
	}

	@Test
	void deveExecutarVariasOperacoes() {

		ContaBancaria conta = new ContaBancaria(100);

		conta.depositar(50);
		conta.sacar(20);
		conta.depositar(30);
		conta.sacar(10);

		assertEquals(150, conta.getSaldo(), 0.001);
	}

	@Test
	void deveManterPrecisaoNosCentavos() {

		ContaBancaria conta = new ContaBancaria(100);

		conta.depositar(0.25);
		conta.sacar(0.10);

		assertEquals(100.15, conta.getSaldo(), 0.001);
	}

}