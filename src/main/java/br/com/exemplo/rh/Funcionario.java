package br.com.exemplo.rh;

public class Funcionario {
	private String nome;
	private String cpf;
	private double salarioBase;
	private int dependentes;

	public Funcionario(String nome, String cpf, double salarioBase, int dependentes) {
		if (salarioBase < 0) {
			throw new IllegalArgumentException("O salário base não pode ser negativo.");
		}
		if (dependentes < 0) {
			throw new IllegalArgumentException("O número de dependentes não pode ser negativo.");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.salarioBase = salarioBase;
		this.dependentes = dependentes;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public int getDependentes() {
		return dependentes;
	}
}