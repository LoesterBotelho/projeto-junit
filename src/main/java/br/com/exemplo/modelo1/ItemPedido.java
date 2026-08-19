package br.com.exemplo.modelo1;

import java.math.BigDecimal;

public record ItemPedido(Produto produto, int quantidade) {
    public ItemPedido {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
    }

    public BigDecimal calcularSubtotal() {
        return produto.preco().multiply(BigDecimal.valueOf(quantidade));
    }
}