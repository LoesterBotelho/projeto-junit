
package br.com.exemplo.produtos;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemPedido {

    private final Produto produto;
    private final int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = Objects.requireNonNull(produto);

        if (quantidade <= 0 || quantidade > produto.getEstoque()) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }

        this.quantidade = quantidade;
    }

    public BigDecimal calcularSubtotal() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}