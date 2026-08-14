package br.com.exemplo.impostorenda;

public class Pessoa {

	private final String nome;
	private final double salarioMensal;
	private final int dependentes;

	public Pessoa(String nome, double salarioMensal, int dependentes) {

		if (nome == null || nome.isBlank()) {
			throw new IllegalArgumentException("Nome não pode ser vazio.");
		}

		if (salarioMensal < 0) {
			throw new IllegalArgumentException("Salário não pode ser negativo.");
		}

		if (dependentes < 0) {
			throw new IllegalArgumentException("Número de dependentes não pode ser negativo.");
		}

		this.nome = nome;
		this.salarioMensal = salarioMensal;
		this.dependentes = dependentes;
	}

	public String getNome() {
		return nome;
	}

	public double getSalarioMensal() {
		return salarioMensal;
	}

	public int getDependentes() {
		return dependentes;
	}

	@Override
	public String toString() {
		return "Pessoa{" + "nome='" + nome + '\'' + ", salarioMensal=" + salarioMensal + ", dependentes=" + dependentes
				+ '}';
	}
}