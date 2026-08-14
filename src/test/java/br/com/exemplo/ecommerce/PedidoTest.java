package br.com.exemplo.ecommerce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

	@Test
	void deveMudarStatusCorretamenteNoCicloDeVida() {
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		Pedido pedido = new Pedido("100", carrinho);

		assertEquals(Pedido.Status.CRIADO, pedido.getStatus());

		pedido.pagar();
		assertEquals(Pedido.Status.PAGO, pedido.getStatus());

		pedido.enviar();
		assertEquals(Pedido.Status.ENVIADO, pedido.getStatus());
	}

	@Test
	void naoDevePermitirEnviarPedidoNaoPago() {
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		Pedido pedido = new Pedido("101", carrinho);

		assertThrows(IllegalStateException.class, pedido::enviar);
	}
}