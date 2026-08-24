package br.com.exemplo.contabil;

import java.math.BigDecimal;

public class ValidadorPartidasDobradas {

	public void validar(LancamentoContabil lancamento) {
		if (lancamento.getItens().isEmpty()) {
			throw new IllegalArgumentException("O lancamento contabil nao possui itens.");
		}

		BigDecimal totalDebitos = BigDecimal.ZERO;
		BigDecimal totalCreditos = BigDecimal.ZERO;

		for (ItemLancamento item : lancamento.getItens()) {
			if (item.getTipo() == TipoLancamentoItem.DEBITO) {
				totalDebitos = totalDebitos.add(item.getValor());
			} else if (item.getTipo() == TipoLancamentoItem.CREDITO) {
				totalCreditos = totalCreditos.add(item.getValor());
			}
		}

		if (totalDebitos.compareTo(totalCreditos) != 0) {
			throw new IllegalStateException(String.format(
					"Partidas dobradas violadas! Total Debitos: %s, Total Creditos: %s", totalDebitos, totalCreditos));
		}
	}
}