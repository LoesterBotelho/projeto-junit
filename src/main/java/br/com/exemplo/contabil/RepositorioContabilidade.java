package br.com.exemplo.contabil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RepositorioContabilidade {
    private final Map<String, ContaContabil> contas = new HashMap<>();
    private final Map<String, LancamentoContabil> lancamentos = new HashMap<>();

    public void salvarConta(ContaContabil conta) {
        contas.put(conta.getCodigo(), conta);
    }

    public Optional<ContaContabil> buscarConta(String codigo) {
        return Optional.ofNullable(contas.get(codigo));
    }

    public void salvarLancamento(LancamentoContabil lancamento) {
        lancamentos.put(lancamento.getId(), lancamento);
    }

    public Optional<LancamentoContabil> buscarLancamento(String id) {
        return Optional.ofNullable(lancamentos.get(id));
    }
}