package br.com.exemplo.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarrinhoDeComprasTest {

	@Test
	void deveAdicionarItensECalcularTotal() {
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		Produto p1 = new Produto("1", "Livro", 50.0, 0.4, 10);
		Produto p2 = new Produto("2", "Caneta", 5.0, 0.05, 50);

		carrinho.adicionarItem(new ItemPedido(p1, 2));
		carrinho.adicionarItem(new ItemPedido(p2, 4));

		assertEquals(120.0, carrinho.calcularTotal());
	}

	@Test
	void naoDevePermitirProdutoDuplicadoNoCarrinho() {
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		Produto p = new Produto("1", "Livro", 50.0, 0.4, 10);

		carrinho.adicionarItem(new ItemPedido(p, 1));
		assertThrows(IllegalStateException.class, () -> carrinho.adicionarItem(new ItemPedido(p, 2)));
	}
}