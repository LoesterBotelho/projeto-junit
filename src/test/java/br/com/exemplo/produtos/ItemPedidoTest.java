
package br.com.exemplo.produtos;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;

class ItemPedidoTest {

    @Test
    void deveCalcularSubtotalComSucesso() {
        // Arrange
        var produto = new Produto(1L, "Mouse", new BigDecimal("50.00"), 10);
        var item = new ItemPedido(produto, 3);

        // Act
        var subtotal = item.calcularSubtotal();

        // Assert
        Assertions.assertThat(subtotal).isEqualByComparingTo("150.00");
    }

    @Test
    void deveRejeitarQuantidadeInvalida() {
        // Arrange
        var produto = new Produto(1L, "Mouse", new BigDecimal("50.00"), 10);

        // Act
        // Assert
        Assertions.assertThatThrownBy(() -> new ItemPedido(produto, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}