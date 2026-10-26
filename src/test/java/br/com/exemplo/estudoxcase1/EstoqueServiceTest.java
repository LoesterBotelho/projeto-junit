package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstoqueServiceTest {

    EstoqueService service;

    Produto produto;

    @BeforeEach
    void setUp() {
        service = new EstoqueService();
        produto = new Produto(
                1L, "Teclado", BigDecimal.valueOf(100));
    }

    @Test
    void deveAdicionarEstoque() {

        // Arrange
        int quantidade = 10;

        // Act
        service.adicionarEstoque(produto, quantidade);

        // Assert
        Assertions.assertThat(service.consultarQuantidade(produto))
                .isEqualTo(10);
    }

    @Test
    void deveReservarQuantidadeDeEstoque() {

        // Arrange
        service.adicionarEstoque(produto, 10);

        // Act
        service.reservar(produto, 3);

        // Assert
        Assertions.assertThat(service.consultarQuantidade(produto))
                .isEqualTo(7);
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueForInsuficiente() {

        // Arrange
        service.adicionarEstoque(produto, 2);

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.reservar(produto, 3))
                .isInstanceOf(EstoqueInsuficienteException.class)
                .hasMessage("Estoque insuficiente.");
    }

    @Test
    void deveRetornarZeroQuandoProdutoNaoPossuirEstoque() {

        // Arrange
        Produto produtoSemEstoque = new Produto(
                2L, "Mouse", BigDecimal.valueOf(50));

        // Act
        int quantidade = service.consultarQuantidade(produtoSemEstoque);

        // Assert
        Assertions.assertThat(quantidade).isZero();
    }

    @Test
    void naoDeveAdicionarQuantidadeNegativa() {

        // Arrange
        int quantidade = -5;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.adicionarEstoque(produto, quantidade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(
                        "Produto e quantidade válida são obrigatórios.");
    }
    
}