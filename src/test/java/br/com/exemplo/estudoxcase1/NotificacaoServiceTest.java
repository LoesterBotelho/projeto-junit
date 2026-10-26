package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotificacaoServiceTest {

    NotificacaoService service = new NotificacaoService();

    @Test
    void deveEnviarConfirmacaoDoPedido() {

        // Arrange
        Cliente cliente = new Cliente(
                1L, "José", "jose@email.com");

        Produto produto = new Produto(
                1L, "Teclado", BigDecimal.valueOf(100));

        ItemPedido item = new ItemPedido(produto, 2);

        Pedido pedido = new Pedido(
                cliente,
                java.util.List.of(item),
                BigDecimal.valueOf(200));

        // Act
        Assertions.assertThatCode(
                () -> service.enviarConfirmacao(pedido))
                .doesNotThrowAnyException();

        // Assert
        Assertions.assertThat(pedido.getId()).isNotBlank();
        Assertions.assertThat(pedido.getCliente().getEmail())
                .isEqualTo("jose@email.com");
    }

    @Test
    void deveLancarExcecaoQuandoPedidoForNulo() {

        // Arrange
        Pedido pedido = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.enviarConfirmacao(pedido))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O pedido é obrigatório.");
    }
    
}