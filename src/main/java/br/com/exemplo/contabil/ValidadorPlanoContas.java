package br.com.exemplo.contabil;

public class ValidadorPlanoContas {

	public void validar(LancamentoContabil lancamento) {
		for (ItemLancamento item : lancamento.getItens()) {
			ContaContabil conta = item.getConta();

			if (!conta.isAtiva()) {
				throw new IllegalStateException("Conta inativa nao pode receber lancamentos: " + conta.getCodigo());
			}

			if (!conta.isAnalitica()) {
				throw new IllegalStateException("Lancamento nao permitido em conta sintetica: " + conta.getCodigo());
			}
		}
	}
}