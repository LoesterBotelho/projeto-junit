package br.com.exemplo.modelo2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CarrinhoCompras {

	private final String clienteId;
	private final Map<String, ItemCarrinho> itens;
	private boolean fechado;
	private static int LIMITE_MAXIMO_ITENS = 50;

	public record ItemCarrinho(String produtoId, String nome, BigDecimal precoUnitario, int quantidade) {
		public ItemCarrinho {
			if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
			}
			if (quantidade <= 0) {
				throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
			}
		}

		public BigDecimal calcularSubtotal() {
			return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
		}
	}

	public CarrinhoCompras(String clienteId) {
		if (clienteId == null || clienteId.isBlank()) {
			throw new IllegalArgumentException("Cliente ID inválido.");
		}
		this.clienteId = clienteId;
		this.itens = new HashMap<>();
		this.fechado = false;
	}

	public void adicionarItem(String produtoId, String nome, BigDecimal preco, int quantidade) {
		validarCarrinhoAberto();
		if (produtoId == null || produtoId.isBlank()) {
			throw new IllegalArgumentException("ID do produto inválido.");
		}

		int quantidadeTotalAtual = itens.values().stream().mapToInt(ItemCarrinho::quantidade).sum();
		if (quantidadeTotalAtual + quantidade > LIMITE_MAXIMO_ITENS) {
			throw new IllegalStateException("O carrinho excedeu o limite máximo de " + LIMITE_MAXIMO_ITENS + " itens.");
		}

		if (itens.containsKey(produtoId)) {
			ItemCarrinho existente = itens.get(produtoId);
			int novaQuantidade = existente.quantidade() + quantidade;
			itens.put(produtoId, new ItemCarrinho(produtoId, nome, preco, novaQuantidade));
		} else {
			itens.put(produtoId, new ItemCarrinho(produtoId, nome, preco, quantidade));
		}
	}

	public void removerItem(String produtoId) {
		validarCarrinhoAberto();
		if (!itens.containsKey(produtoId)) {
			throw new IllegalArgumentException("Produto não encontrado no carrinho.");
		}
		itens.remove(produtoId);
	}

	public BigDecimal calcularTotalBruto() {
		return itens.values().stream().map(ItemCarrinho::calcularSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal calcularFreteFinal() {
		BigDecimal total = calcularTotalBruto();
		if (total.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}

		if (total.compareTo(new BigDecimal("250.00")) >= 0) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal("20.00").setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal calcularTotalComFrete() {
		return calcularTotalBruto().add(calcularFreteFinal());
	}

	public void fecharCarrinho() {
		validarCarrinhoAberto();
		if (itens.isEmpty()) {
			throw new IllegalStateException("Não é possível fechar um carrinho vazio.");
		}
		this.fechado = true;
	}

	private void validarCarrinhoAberto() {
		if (this.fechado) {
			throw new IllegalStateException("Este carrinho já foi fechado e não aceita mais alterações.");
		}
	}

	public String getClienteId() {
		return clienteId;
	}

	public boolean isFechado() {
		return fechado;
	}

	public Map<String, ItemCarrinho> getItens() {
		return Collections.unmodifiableMap(itens);
	}
}