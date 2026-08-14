package br.com.exemplo.impostorenda;

public class ImpostoRenda {

	private static final double DESCONTO_POR_DEPENDENTE = 250.00;

	private static final double LIMITE_ISENTO = 2500.00;
	private static final double LIMITE_7_5 = 4000.00;
	private static final double LIMITE_15 = 6000.00;
	private static final double LIMITE_22_5 = 9000.00;

	public ResultadoImposto calcular(Pessoa pessoa) {

		if (pessoa == null) {
			throw new NullPointerException("Pessoa não pode ser nula.");
		}

		double salarioBruto = pessoa.getSalarioMensal();

		double salarioAnual = salarioBruto * 12;

		double descontoDependentes = pessoa.getDependentes() * DESCONTO_POR_DEPENDENTE;

		double baseCalculo = Math.max(0, salarioBruto - descontoDependentes);

		double aliquota = calcularAliquota(baseCalculo);

		double imposto = baseCalculo * aliquota;

		return new ResultadoImposto(salarioBruto, salarioAnual, descontoDependentes, baseCalculo, imposto, aliquota);
	}

	private double calcularAliquota(double baseCalculo) {

		if (baseCalculo <= LIMITE_ISENTO) {
			return 0.0;
		}

		if (baseCalculo <= LIMITE_7_5) {
			return 0.075;
		}

		if (baseCalculo <= LIMITE_15) {
			return 0.15;
		}

		if (baseCalculo <= LIMITE_22_5) {
			return 0.225;
		}

		return 0.275;
	}
}