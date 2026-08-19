package br.com.exemplo.modelo1;

import java.math.BigDecimal;
import java.util.List;

public class PedidoService {

    public BigDecimal calcularTotalPedido(List<ItemPedido> itens) {
        if (itens == null || itens.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return itens.stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String processarPagamento(BigDecimal total, String formaPagamento) {
        return switch (formaPagamento.toUpperCase()) {
            case "PIX" -> "Pagamento de R$ " + total + " via PIX aprovado com desconto.";
            case "CREDITO" -> "Pagamento de R$ " + total + " no Cartão de Crédito processado.";
            case "BOLETO" -> "Boleto gerado no valor de R$ " + total + ".";
            default -> throw new IllegalArgumentException("Forma de pagamento inválida: " + formaPagamento);
        };
    }
}