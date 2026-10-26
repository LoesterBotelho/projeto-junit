package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;
import java.util.List;

public class PedidoService {

    private final EstoqueService estoqueService;
    private final PagamentoService pagamentoService;
    private final NotificacaoService notificacaoService;

    public PedidoService(
            EstoqueService estoqueService,
            PagamentoService pagamentoService,
            NotificacaoService notificacaoService) {
        this.estoqueService = estoqueService;
        this.pagamentoService = pagamentoService;
        this.notificacaoService = notificacaoService;
    }

    public Pedido criarPedido(Cliente cliente, List<ItemPedido> itens) {

        if (cliente == null) {
            throw new PedidoInvalidoException(
                    "O cliente é obrigatório.");
        }

        if (itens == null || itens.isEmpty()) {
            throw new PedidoInvalidoException(
                    "O pedido deve possuir pelo menos um item.");
        }

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedido item : itens) {
            if (item == null || item.getProduto() == null
                    || item.getQuantidade() <= 0) {
                throw new PedidoInvalidoException(
                        "O item do pedido é inválido.");
            }

            estoqueService.validarDisponibilidade(
                    item.getProduto(), item.getQuantidade());

            valorTotal = valorTotal.add(item.getSubtotal());
        }

        boolean pagamentoAprovado =
                pagamentoService.processarPagamento(cliente, valorTotal);

        if (!pagamentoAprovado) {
            throw new PagamentoRecusadoException(
                    "O pagamento foi recusado.");
        }

        for (ItemPedido item : itens) {
            estoqueService.reservar(
                    item.getProduto(), item.getQuantidade());
        }

        Pedido pedido = new Pedido(cliente, itens, valorTotal);

        notificacaoService.enviarConfirmacao(pedido);

        return pedido;
    }
    
}