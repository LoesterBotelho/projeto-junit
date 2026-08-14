package br.com.exemplo.ecommerce;

public class CalculadoraFrete {
	public static double calcular(double pesoTotal, String estado) {
		if (pesoTotal < 0)
			throw new IllegalArgumentException("Peso inválido.");

		double taxaBase = switch (estado.toUpperCase()) {
		case "SC", "PR", "RS" -> 15.0;
		case "SP", "RJ" -> 25.0;
		default -> 40.0;
		};

		return taxaBase + (pesoTotal * 5.0);
	}
}