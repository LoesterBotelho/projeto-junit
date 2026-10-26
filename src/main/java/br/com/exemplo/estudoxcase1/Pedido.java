package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private final String id;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
    private final BigDecimal valorTotal;

    public Pedido(Cliente cliente, List<ItemPedido> itens,
            BigDecimal valorTotal) {
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.itens = List.copyOf(itens);
        this.valorTotal = valorTotal;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    
}