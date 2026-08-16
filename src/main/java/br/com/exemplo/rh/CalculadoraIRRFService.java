package br.com.exemplo.rh;

public class CalculadoraIRRFService {
	private static final double DEDUCAO_POR_DEPENDENTE = 189.59;

	public double calcular(double salarioBruto, double inss, int dependentes) {
		double baseCalculo = salarioBruto - inss - (dependentes * DEDUCAO_POR_DEPENDENTE);

		if (baseCalculo <= 2259.20) {
			return 0.0;
		} else if (baseCalculo <= 2826.65) {
			return (baseCalculo * 0.075) - 169.44;
		} else if (baseCalculo <= 3751.05) {
			return (baseCalculo * 0.15) - 381.44;
		} else if (baseCalculo <= 4664.68) {
			return (baseCalculo * 0.225) - 662.77;
		} else {
			return (baseCalculo * 0.275) - 896.00;
		}
	}
}