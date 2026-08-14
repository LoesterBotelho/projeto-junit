package br.com.exemplo.contabancaria;

public class Main {

	public static void main(String[] args) {

		ContaBancaria contaA = new ContaBancaria(1000);

		ContaBancaria contaB = new ContaBancaria(500);

		contaA.depositar(250);

		contaA.sacar(100);

		contaA.transferir(contaB, 300);

		System.out.println("Saldo Conta A: " + contaA.getSaldo());

		System.out.println("Saldo Conta B: " + contaB.getSaldo());

	}

}