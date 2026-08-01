package br.com.exemplo.calculadora;

import java.util.Arrays;

public class Vetores {

	public int soma(int[] vetor) {

		int resultado = 0;

		for (int numero : vetor) {

			resultado += numero;

		}

		return resultado;

	}

	public int maior(int[] vetor) {

		return Arrays.stream(vetor).max().orElseThrow();

	}

	public int menor(int[] vetor) {

		return Arrays.stream(vetor).min().orElseThrow();

	}

	public double media(int[] vetor) {

		return Arrays.stream(vetor).average().orElseThrow();

	}

	public int tamanho(int[] vetor) {

		return vetor.length;

	}

}