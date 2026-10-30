
package br.com.exemplo.produtos;

import java.math.BigDecimal;

public class CalculadoraPedido {

    public BigDecimal calcularTotal(Pedido pedido) {
        return pedido.getItens()
                .stream()
                .map(ItemPedido::calcularSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}