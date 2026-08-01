package br.com.exemplo.calculadora;

public class CalculadoraIMC {

	public double calcular(double peso, double altura) {

		if (altura <= 0) {
			throw new IllegalArgumentException("Altura inválida");
		}

		return peso / (altura * altura);
	}

	public String classificacao(double imc) {

		if (imc < 18.5) {
			return "Abaixo do peso";
		}

		if (imc < 25) {
			return "Peso normal";
		}

		if (imc < 30) {
			return "Sobrepeso";
		}

		return "Obesidade";
	}

}