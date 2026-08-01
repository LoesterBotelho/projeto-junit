package br.com.exemplo.calculadora;

public class VerificadorNumero {

	public boolean positivo(int numero) {

		return numero > 0;

	}

	public boolean negativo(int numero) {

		return numero < 0;

	}

	public boolean zero(int numero) {

		return numero == 0;

	}

	public boolean par(int numero) {

		return numero % 2 == 0;

	}

	public boolean impar(int numero) {

		return numero % 2 != 0;

	}

}