package br.com.exemplo.contabancaria;

public class ContaBancaria {

	private double saldo;

	public ContaBancaria(double saldoInicial) {

		if (saldoInicial < 0) {
			throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
		}

		this.saldo = saldoInicial;
	}

	public void depositar(double valor) {

		if (valor <= 0) {
			throw new IllegalArgumentException("Valor do depósito deve ser maior que zero.");
		}

		saldo += valor;
	}

	public void sacar(double valor) {

		if (valor <= 0) {
			throw new IllegalArgumentException("Valor do saque deve ser maior que zero.");
		}

		if (valor > saldo) {
			throw new IllegalStateException("Saldo insuficiente.");
		}

		saldo -= valor;
	}

	public void transferir(ContaBancaria destino, double valor) {

		if (destino == null) {
			throw new NullPointerException("Conta destino não pode ser nula.");
		}

		if (destino == this) {
			throw new IllegalArgumentException("Não é permitido transferir para a própria conta.");
		}

		sacar(valor);
		destino.depositar(valor);
	}

	public double getSaldo() {
		return saldo;
	}

}