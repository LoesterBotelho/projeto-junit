
package br.com.exemplo.produtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private final Long id;
    private final List<ItemPedido> itens;

    public Pedido(Long id) {
        this.id = Objects.requireNonNull(id);
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(Objects.requireNonNull(item));
    }

    public Long getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return List.copyOf(itens);
    }
}