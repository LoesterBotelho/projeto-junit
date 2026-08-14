package br.com.exemplo.ecommerce;

public class Produto {
	private String id;
	private String nome;
	private double preco;
	private double peso;
	private int estoque;

	public Produto(String id, String nome, double preco, double peso, int estoque) {
		if (preco < 0 || peso < 0 || estoque < 0) {
			throw new IllegalArgumentException("Preço, peso e estoque não podem ser negativos.");
		}
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.peso = peso;
		this.estoque = estoque;
	}

	public void removerEstoque(int quantidade) {
		if (quantidade > estoque) {
			throw new IllegalArgumentException("Estoque insuficiente.");
		}
		this.estoque -= quantidade;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public double getPeso() {
		return peso;
	}

	public int getEstoque() {
		return estoque;
	}
}