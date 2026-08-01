package br.com.exemplo.calculadora;

public class CalculadoraJuros {

	public double jurosSimples(double capital, double taxa, int tempo) {

		return capital * taxa * tempo;
	}

	public double jurosCompostos(double capital, double taxa, int tempo) {

		return capital * Math.pow(1 + taxa, tempo);
	}

}