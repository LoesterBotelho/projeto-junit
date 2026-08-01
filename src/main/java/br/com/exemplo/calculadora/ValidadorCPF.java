package br.com.exemplo.calculadora;

public class ValidadorCPF {

	public boolean validar(String cpf) {

		if (cpf == null || cpf.length() != 11) {
			return false;
		}

		if (cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		int soma = 0;

		for (int i = 0; i < 9; i++) {

			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);

		}

		int primeiroDigito = (soma * 10) % 11;

		if (primeiroDigito == 10) {
			primeiroDigito = 0;
		}

		if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		soma = 0;

		for (int i = 0; i < 10; i++) {

			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);

		}

		int segundoDigito = (soma * 10) % 11;

		if (segundoDigito == 10) {
			segundoDigito = 0;
		}

		return segundoDigito == Character.getNumericValue(cpf.charAt(10));

	}

}