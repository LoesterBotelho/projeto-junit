package br.com.exemplo.contabil;

import java.util.Objects;

public class ServicoConsultaContabil {
    private final RepositorioContabilidade repositorio;

    public ServicoConsultaContabil(RepositorioContabilidade repositorio) {
        this.repositorio = Objects.requireNonNull(repositorio, "Repositorio nao pode ser nulo");
    }

    public ContaContabil obterContaPorCodigo(String codigo) {
        return repositorio.buscarConta(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Conta contábil não encontrada: " + codigo));
    }
}