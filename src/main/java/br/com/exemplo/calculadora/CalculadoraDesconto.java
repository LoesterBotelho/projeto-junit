package br.com.exemplo.calculadora;

public class CalculadoraDesconto {

	public double calcularDesconto(double valor, double percentual) {

		return valor * percentual / 100;
	}

	public double valorFinal(double valor, double percentual) {

		return valor - calcularDesconto(valor, percentual);
	}

}