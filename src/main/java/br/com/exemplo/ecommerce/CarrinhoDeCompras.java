package br.com.exemplo.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
	private List<ItemPedido> itens = new ArrayList<>();

	public void adicionarItem(ItemPedido novoItem) {
		for (ItemPedido item : itens) {
			if (item.getProduto().getId().equals(novoItem.getProduto().getId())) {
				throw new IllegalStateException("Produto já adicionado ao carrinho.");
			}
		}
		itens.add(novoItem);
	}

	public double calcularTotal() {
		return itens.stream().mapToDouble(ItemPedido::calcularSubtotal).sum();
	}

	public List<ItemPedido> getItens() {
		return itens;
	}
}