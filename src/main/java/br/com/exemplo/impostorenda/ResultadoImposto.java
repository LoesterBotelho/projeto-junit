package br.com.exemplo.impostorenda;

public class ResultadoImposto {

	private final double salarioBruto;
	private final double salarioAnual;
	private final double descontoDependentes;
	private final double baseCalculo;
	private final double imposto;
	private final double aliquota;

	public ResultadoImposto(double salarioBruto, double salarioAnual, double descontoDependentes, double baseCalculo,
			double imposto, double aliquota) {

		this.salarioBruto = salarioBruto;
		this.salarioAnual = salarioAnual;
		this.descontoDependentes = descontoDependentes;
		this.baseCalculo = baseCalculo;
		this.imposto = imposto;
		this.aliquota = aliquota;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public double getSalarioAnual() {
		return salarioAnual;
	}

	public double getDescontoDependentes() {
		return descontoDependentes;
	}

	public double getBaseCalculo() {
		return baseCalculo;
	}

	public double getImposto() {
		return imposto;
	}

	public double getAliquota() {
		return aliquota;
	}

	@Override
	public String toString() {
		return "ResultadoImposto{" + "salarioBruto=" + salarioBruto + ", salarioAnual=" + salarioAnual
				+ ", descontoDependentes=" + descontoDependentes + ", baseCalculo=" + baseCalculo + ", imposto="
				+ imposto + ", aliquota=" + aliquota + '}';
	}
}