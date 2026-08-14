package br.com.exemplo.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {

	@Test
	void deveCalcularSubtotalE_PesoCorretamente() {
		Produto p = new Produto("1", "Monitor", 1200.0, 4.0, 10);
		ItemPedido item = new ItemPedido(p, 2);

		assertEquals(2400.0, item.calcularSubtotal());
		assertEquals(8.0, item.calcularPesoTotal());
	}

	@Test
	void naoDevePermitirQuantidadeZeroOuNegativa() {
		Produto p = new Produto("1", "Monitor", 1200.0, 4.0, 10);
		assertThrows(IllegalArgumentException.class, () -> new ItemPedido(p, 0));
	}
}