package br.com.exemplo.calculadora;

public class Bhaskara {

	public double calcularDelta(double a, double b, double c) {

		return (b * b) - (4 * a * c);
	}

	public double calcularX1(double a, double b, double c) {

		double delta = calcularDelta(a, b, c);

		if (delta < 0) {
			throw new IllegalArgumentException("Não existem raízes reais");
		}

		return (-b + Math.sqrt(delta)) / (2 * a);
	}

	public double calcularX2(double a, double b, double c) {

		double delta = calcularDelta(a, b, c);

		if (delta < 0) {
			throw new IllegalArgumentException("Não existem raízes reais");
		}

		return (-b - Math.sqrt(delta)) / (2 * a);
	}

}