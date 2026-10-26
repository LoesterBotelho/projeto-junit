package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    PedidoService service;

    @Mock
    EstoqueService estoqueService;

    @Mock
    PagamentoService pagamentoService;

    @Mock
    NotificacaoService notificacaoService;

    @Test
    void deveCriarPedidoComSucesso() {

        // Arrange
        Cliente cliente = new Cliente(
                1L, "José", "jose@email.com");

        Produto produto = new Produto(
                1L, "Teclado", BigDecimal.valueOf(100));

        ItemPedido item = new ItemPedido(produto, 2);

        List<ItemPedido> itens = List.of(item);

        // Stub
        Mockito.when(pagamentoService.processarPagamento(
                cliente, BigDecimal.valueOf(200))).thenReturn(true);

        // Act
        Pedido pedido = service.criarPedido(cliente, itens);

        // Assert
        Assertions.assertThat(pedido).isNotNull();
        Assertions.assertThat(pedido.getId()).isNotBlank();
        Assertions.assertThat(pedido.getCliente()).isEqualTo(cliente);
        Assertions.assertThat(pedido.getItens()).hasSize(1);
        Assertions.assertThat(pedido.getValorTotal())
                .isEqualByComparingTo("200");

        // Verify
        Mockito.verify(estoqueService)
                .validarDisponibilidade(produto, 2);

        Mockito.verify(estoqueService)
                .reservar(produto, 2);

        Mockito.verify(pagamentoService)
                .processarPagamento(cliente, BigDecimal.valueOf(200));

        Mockito.verify(notificacaoService)
                .enviarConfirmacao(pedido);
    }

    @Test
    void deveLancarExcecaoQuandoPagamentoForRecusado() {

        // Arrange
        Cliente cliente = new Cliente(
                2L, "Maria", "maria@email.com");

        Produto produto = new Produto(
                2L, "Mouse", BigDecimal.valueOf(80));

        List<ItemPedido> itens = List.of(
                new ItemPedido(produto, 1));

        // Stub
        Mockito.when(pagamentoService.processarPagamento(
                cliente, BigDecimal.valueOf(80))).thenReturn(false);

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.criarPedido(cliente, itens))
                .isInstanceOf(PagamentoRecusadoException.class)
                .hasMessage("O pagamento foi recusado.");

        // Verify
        Mockito.verify(estoqueService)
                .validarDisponibilidade(produto, 1);

        Mockito.verify(estoqueService, Mockito.never())
                .reservar(Mockito.any(Produto.class), Mockito.anyInt());

        Mockito.verify(notificacaoService, Mockito.never())
                .enviarConfirmacao(Mockito.any(Pedido.class));
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueForInsuficiente() {

        // Arrange
        Cliente cliente = new Cliente(
                3L, "Ana", "ana@email.com");

        Produto produto = new Produto(
                3L, "Monitor", BigDecimal.valueOf(900));

        List<ItemPedido> itens = List.of(
                new ItemPedido(produto, 2));

        // Stub
        Mockito.doThrow(new EstoqueInsuficienteException(
                "Estoque insuficiente."))
                .when(estoqueService)
                .validarDisponibilidade(produto, 2);

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.criarPedido(cliente, itens))
                .isInstanceOf(EstoqueInsuficienteException.class)
                .hasMessage("Estoque insuficiente.");

        // Verify
        Mockito.verify(pagamentoService, Mockito.never())
                .processarPagamento(
                        Mockito.any(Cliente.class),
                        Mockito.any(BigDecimal.class));

        Mockito.verify(estoqueService, Mockito.never())
                .reservar(Mockito.any(Produto.class), Mockito.anyInt());

        Mockito.verify(notificacaoService, Mockito.never())
                .enviarConfirmacao(Mockito.any(Pedido.class));
    }

    @Test
    void deveEnviarPedidoCriadoParaNotificacao() {

        // Arrange
        Cliente cliente = new Cliente(
                1L, "José", "jose@email.com");

        Produto produto = new Produto(
                1L, "Teclado", BigDecimal.valueOf(100));

        List<ItemPedido> itens = List.of(
                new ItemPedido(produto, 1));

        // Stub
        Mockito.when(pagamentoService.processarPagamento(
                cliente, BigDecimal.valueOf(100))).thenReturn(true);

        // Act
        Pedido pedido = service.criarPedido(cliente, itens);

        // Assert
        ArgumentCaptor<Pedido> captor =
                ArgumentCaptor.forClass(Pedido.class);

        Mockito.verify(notificacaoService)
                .enviarConfirmacao(captor.capture());

        Assertions.assertThat(captor.getValue()).isSameAs(pedido);
        Assertions.assertThat(captor.getValue().getValorTotal())
                .isEqualByComparingTo("100");
    }
    
}