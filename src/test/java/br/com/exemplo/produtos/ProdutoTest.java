
package br.com.exemplo.produtos;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;

class ProdutoTest {

    @Test
    void deveCriarProdutoComSucesso() {
        // Arrange
        var produto = new Produto(1L, "Teclado", new BigDecimal("150.00"), 10);

        // Act
        var nome = produto.getNome();

        // Assert
        Assertions.assertThat(nome).isEqualTo("Teclado");
        Assertions.assertThat(produto.getEstoque()).isEqualTo(10);
    }

    @Test
    void deveRetirarEstoqueComSucesso() {
        // Arrange
        var produto = new Produto(1L, "Teclado", new BigDecimal("150.00"), 10);

        // Act
        produto.retirarEstoque(3);

        // Assert
        Assertions.assertThat(produto.getEstoque()).isEqualTo(7);
    }

    @Test
    void deveRejeitarRetiradaMaiorQueEstoque() {
        // Arrange
        var produto = new Produto(1L, "Teclado", new BigDecimal("150.00"), 2);

        // Act
        // Assert
        Assertions.assertThatThrownBy(() -> produto.retirarEstoque(5))
                .isInstanceOf(IllegalArgumentException.class);
    }
}