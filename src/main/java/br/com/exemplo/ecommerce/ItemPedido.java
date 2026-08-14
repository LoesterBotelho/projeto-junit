package br.com.exemplo.ecommerce;

public class ItemPedido {
	private Produto produto;
	private int quantidade;

	public ItemPedido(Produto produto, int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
		}
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public double calcularSubtotal() {
		return produto.getPreco() * quantidade;
	}

	public double calcularPesoTotal() {
		return produto.getPeso() * quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}
}