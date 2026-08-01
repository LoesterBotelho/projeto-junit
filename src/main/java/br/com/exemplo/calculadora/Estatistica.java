package br.com.exemplo.calculadora;

import java.util.Arrays;

public class Estatistica {

	public double media(int[] valores) {

		if (valores == null || valores.length == 0) {
			throw new IllegalArgumentException("Lista vazia");
		}

		int soma = 0;

		for (int valor : valores) {

			soma += valor;

		}

		return (double) soma / valores.length;

	}

	public int maior(int[] valores) {

		if (valores == null || valores.length == 0) {
			throw new IllegalArgumentException("Lista vazia");
		}

		int maior = valores[0];

		for (int valor : valores) {

			if (valor > maior) {
				maior = valor;
			}

		}

		return maior;

	}

	public int menor(int[] valores) {

		if (valores == null || valores.length == 0) {
			throw new IllegalArgumentException("Lista vazia");
		}

		int menor = valores[0];

		for (int valor : valores) {

			if (valor < menor) {
				menor = valor;
			}

		}

		return menor;

	}

	public double mediana(int[] valores) {

		if (valores == null || valores.length == 0) {
			throw new IllegalArgumentException("Lista vazia");
		}

		int[] copia = valores.clone();

		Arrays.sort(copia);

		int meio = copia.length / 2;

		if (copia.length % 2 == 0) {

			return (copia[meio - 1] + copia[meio]) / 2.0;

		}

		return copia[meio];

	}

}