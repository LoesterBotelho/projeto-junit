package br.com.exemplo.modelo2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContaBancaria {

	private final String numeroConta;
	private final String titular;
	private BigDecimal saldo;
	private BigDecimal limiteChequeEspecial;
	private boolean ativa;
	private final List<String> historicoTransacoes;

	public ContaBancaria(String numeroConta, String titular, BigDecimal saldoInicial, BigDecimal limiteChequeEspecial) {
		if (numeroConta == null || numeroConta.isBlank()) {
			throw new IllegalArgumentException("Número da conta inválido.");
		}
		if (titular == null || titular.isBlank()) {
			throw new IllegalArgumentException("Titular inválido.");
		}
		if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
		}
		if (limiteChequeEspecial == null || limiteChequeEspecial.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Limite de cheque especial não pode ser negativo.");
		}

		this.numeroConta = numeroConta;
		this.titular = titular;
		this.saldo = saldoInicial;
		this.limiteChequeEspecial = limiteChequeEspecial;
		this.ativa = true;
		this.historicoTransacoes = new ArrayList<>();
		registrarTransacao("Conta criada com saldo inicial de: " + saldoInicial);
	}

	public void depositar(BigDecimal valor) {
		validarContaAtiva();
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
		}
		this.saldo = this.saldo.add(valor);
		registrarTransacao("Depósito de: " + valor);
	}

	public void sacar(BigDecimal valor) {
		validarContaAtiva();
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
		}

		BigDecimal saldoTotalDisponivel = this.saldo.add(this.limiteChequeEspecial);
		if (valor.compareTo(saldoTotalDisponivel) > 0) {
			throw new IllegalStateException("Saldo e limite insuficientes para realizar o saque.");
		}

		this.saldo = this.saldo.subtract(valor);
		registrarTransacao("Saque de: " + valor);
	}

	public void transferir(BigDecimal valor, ContaBancaria contaDestino) {
		validarContaAtiva();
		if (contaDestino == null || !contaDestino.isAtiva()) {
			throw new IllegalArgumentException("Conta de destino inválida ou inativa.");
		}
		if (this.numeroConta.equals(contaDestino.getNumeroConta())) {
			throw new IllegalArgumentException("Não é permitido transferir para a mesma conta.");
		}
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
		}

		BigDecimal taxa = BigDecimal.ZERO;
		if (valor.compareTo(new BigDecimal("1000.00")) > 0) {
			taxa = valor.multiply(new BigDecimal("0.01")).setScale(2, RoundingMode.HALF_EVEN);
		}

		BigDecimal valorTotalComTaxa = valor.add(taxa);
		BigDecimal saldoTotalDisponivel = this.saldo.add(this.limiteChequeEspecial);

		if (valorTotalComTaxa.compareTo(saldoTotalDisponivel) > 0) {
			throw new IllegalStateException(
					"Saldo insuficiente (incluindo taxa de transferência) para realizar a operação.");
		}

		this.saldo = this.saldo.subtract(valorTotalComTaxa);
		contaDestino.depositar(valor);

		registrarTransacao(
				"Transferência de " + valor + " para conta " + contaDestino.getNumeroConta() + " (Taxa: " + taxa + ")");
	}

	public void desativarConta() {
		if (this.saldo.compareTo(BigDecimal.ZERO) != 0) {
			throw new IllegalStateException("Não é possível encerrar conta com saldo diferente de zero.");
		}
		this.ativa = false;
		registrarTransacao("Conta encerrada.");
	}

	private void validarContaAtiva() {
		if (!this.ativa) {
			throw new IllegalStateException("Esta operação não pode ser realizada em uma conta inativa.");
		}
	}

	private void registrarTransacao(String detalhes) {
		this.historicoTransacoes.add(LocalDateTime.now() + " - " + detalhes);
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public BigDecimal getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public List<String> getHistoricoTransacoes() {
		return Collections.unmodifiableList(historicoTransacoes);
	}
}