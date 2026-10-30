
package br.com.exemplo.produtos;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;

class CalculadoraPedidoTest {

    @Test
    void deveCalcularTotalDoPedido() {
        // Arrange
        var produtoA = new Produto(1L, "Mouse", new BigDecimal("50.00"), 10);
        var produtoB = new Produto(2L, "Teclado", new BigDecimal("150.00"), 10);

        var pedido = new Pedido(1L);
        pedido.adicionarItem(new ItemPedido(produtoA, 2));
        pedido.adicionarItem(new ItemPedido(produtoB, 1));

        var calculadora = new CalculadoraPedido();

        // Act
        var total = calculadora.calcularTotal(pedido);

        // Assert
        Assertions.assertThat(total).isEqualByComparingTo("250.00");
    }

    @Test
    void deveRetornarZeroParaPedidoVazio() {
        // Arrange
        var pedido = new Pedido(1L);
        var calculadora = new CalculadoraPedido();

        // Act
        var total = calculadora.calcularTotal(pedido);

        // Assert
        Assertions.assertThat(total).isEqualByComparingTo(BigDecimal.ZERO);
    }
}