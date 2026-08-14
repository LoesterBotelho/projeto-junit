package br.com.exemplo.impostorenda;

public class FaixaImposto {

	private final double salarioMinimo;
	private final double salarioMaximo;
	private final double aliquota;

	public FaixaImposto(double salarioMinimo, double salarioMaximo, double aliquota) {

		if (salarioMinimo < 0) {
			throw new IllegalArgumentException("Salário mínimo da faixa não pode ser negativo.");
		}

		if (salarioMaximo < salarioMinimo) {
			throw new IllegalArgumentException("Salário máximo não pode ser menor que o mínimo.");
		}

		if (aliquota < 0 || aliquota > 1) {
			throw new IllegalArgumentException("Alíquota deve estar entre 0 e 1.");
		}

		this.salarioMinimo = salarioMinimo;
		this.salarioMaximo = salarioMaximo;
		this.aliquota = aliquota;
	}

	public double getSalarioMinimo() {
		return salarioMinimo;
	}

	public double getSalarioMaximo() {
		return salarioMaximo;
	}

	public double getAliquota() {
		return aliquota;
	}

	@Override
	public String toString() {
		return "FaixaImposto{" + "salarioMinimo=" + salarioMinimo + ", salarioMaximo=" + salarioMaximo + ", aliquota="
				+ aliquota + '}';
	}
}