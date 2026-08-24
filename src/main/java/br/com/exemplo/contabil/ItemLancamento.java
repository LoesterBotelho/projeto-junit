package br.com.exemplo.contabil;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemLancamento {
	private ContaContabil conta;
	private CentroCusto centroCusto;
	private BigDecimal valor;
	private TipoLancamentoItem tipo;

	public ItemLancamento(ContaContabil conta, BigDecimal valor, TipoLancamentoItem tipo) {
		this(conta, null, valor, tipo);
	}

	public ItemLancamento(ContaContabil conta, CentroCusto centroCusto, BigDecimal valor, TipoLancamentoItem tipo) {
		this.conta = Objects.requireNonNull(conta, "Conta contabil nao pode ser nula");
		this.valor = Objects.requireNonNull(valor, "Valor nao pode ser nulo");
		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do item deve ser maior que zero");
		}
		this.tipo = Objects.requireNonNull(tipo, "Tipo do item nao pode ser nulo");
		this.centroCusto = centroCusto;
	}

	public ContaContabil getConta() {
		return conta;
	}

	public CentroCusto getCentroCusto() {
		return centroCusto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public TipoLancamentoItem getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "ItemLancamento [conta=" + conta + 
				", centroCusto=" + centroCusto + 
				", valor=" + valor + 
				", tipo=" + tipo
				+ "]";
	}

}