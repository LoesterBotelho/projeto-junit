package br.com.exemplo.contabancaria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTransferirTest {

	@Test
	void deveTransferir() {

		ContaBancaria origem = new ContaBancaria(500);
		ContaBancaria destino = new ContaBancaria(100);

		origem.transferir(destino, 200);

		assertAll(() -> assertEquals(300, origem.getSaldo(), 0.001),
				() -> assertEquals(300, destino.getSaldo(), 0.001));
	}

	@Test
	void naoDeveTransferirSemSaldo() {

		ContaBancaria origem = new ContaBancaria(100);
		ContaBancaria destino = new ContaBancaria(50);

		assertThrows(IllegalStateException.class, () -> origem.transferir(destino, 200));
	}

	@Test
	void naoDeveTransferirValorZero() {

		ContaBancaria origem = new ContaBancaria(100);
		ContaBancaria destino = new ContaBancaria(50);

		assertThrows(IllegalArgumentException.class, () -> origem.transferir(destino, 0));
	}

	@Test
	void naoDeveTransferirValorNegativo() {

		ContaBancaria origem = new ContaBancaria(100);
		ContaBancaria destino = new ContaBancaria(50);

		assertThrows(IllegalArgumentException.class, () -> origem.transferir(destino, -10));
	}

	@Test
	void naoDeveTransferirParaContaNula() {

		ContaBancaria origem = new ContaBancaria(100);

		assertThrows(NullPointerException.class, () -> origem.transferir(null, 50));
	}

	@Test
	void naoDeveTransferirParaMesmaConta() {

		ContaBancaria conta = new ContaBancaria(100);

		assertThrows(IllegalArgumentException.class, () -> conta.transferir(conta, 20));
	}

	@Test
	void deveRealizarVariasTransferencias() {

		ContaBancaria origem = new ContaBancaria(500);
		ContaBancaria destino = new ContaBancaria(0);

		origem.transferir(destino, 100);
		origem.transferir(destino, 50);
		origem.transferir(destino, 25);

		assertAll(() -> assertEquals(325, origem.getSaldo(), 0.001),
				() -> assertEquals(175, destino.getSaldo(), 0.001));
	}

}