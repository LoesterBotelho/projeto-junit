package br.com.exemplo.impostorenda;

import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		Scanner scanner = new Scanner(System.in);

		ImpostoRenda impostoRenda = new ImpostoRenda();

		System.out.println("==================================");
		System.out.println("       CALCULADORA IMPOSTO RENDA");
		System.out.println("==================================");

		boolean continuar = true;

		while (continuar) {

			System.out.print("\nNome: ");
			String nome = scanner.nextLine();

			System.out.print("Salário mensal: ");
			double salario = scanner.nextDouble();

			System.out.print("Quantidade de dependentes: ");
			int dependentes = scanner.nextInt();

			scanner.nextLine();

			try {

				Pessoa pessoa = new Pessoa(nome, salario, dependentes);

				ResultadoImposto resultado = impostoRenda.calcular(pessoa);

				System.out.println();
				System.out.println("========== RESULTADO ==========");

				System.out.printf("Nome: %s%n", pessoa.getNome());

				System.out.printf("Salário mensal: R$ %.2f%n", resultado.getSalarioBruto());

				System.out.printf("Salário anual: R$ %.2f%n", resultado.getSalarioAnual());

				System.out.printf("Dependentes: %d%n", pessoa.getDependentes());

				System.out.printf("Desconto dependentes: R$ %.2f%n", resultado.getDescontoDependentes());

				System.out.printf("Base de cálculo: R$ %.2f%n", resultado.getBaseCalculo());

				System.out.printf("Alíquota: %.2f%%%n", resultado.getAliquota() * 100);

				System.out.printf("Imposto: R$ %.2f%n", resultado.getImposto());

				System.out.println("===============================");

			} catch (IllegalArgumentException | NullPointerException e) {

				System.out.println();
				System.out.println("ERRO: " + e.getMessage());
			}

			System.out.print("\nDeseja realizar outro cálculo? (S/N): ");

			String resposta = scanner.nextLine();

			continuar = resposta.equalsIgnoreCase("S");
		}

		scanner.close();

		System.out.println("\nPrograma encerrado.");
	}
}