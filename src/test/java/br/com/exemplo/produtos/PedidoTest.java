
package br.com.exemplo.produtos;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;

class PedidoTest {

    @Test
    void deveAdicionarItemAoPedido() {
        // Arrange
        var produto = new Produto(1L, "Mouse", new BigDecimal("50.00"), 10);
        var pedido = new Pedido(1L);
        var item = new ItemPedido(produto, 2);

        // Act
        pedido.adicionarItem(item);

        // Assert
        Assertions.assertThat(pedido.getItens()).hasSize(1);
        Assertions.assertThat(pedido.getItens().getFirst()).isEqualTo(item);
    }

    @Test
    void deveImpedirAlteracaoDiretaDosItens() {
        // Arrange
        var pedido = new Pedido(1L);

        // Act
        // Assert
        Assertions.assertThatThrownBy(() -> pedido.getItens().add(
                new ItemPedido(
                        new Produto(1L, "Mouse", new BigDecimal("50.00"), 10),
                        1)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}