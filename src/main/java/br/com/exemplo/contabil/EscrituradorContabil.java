package br.com.exemplo.contabil;

import java.util.Objects;

public class EscrituradorContabil {
    private final ValidadorPartidasDobradas validadorPartidas;
    private final ValidadorPlanoContas validadorPlanoContas;

    public EscrituradorContabil(ValidadorPartidasDobradas validadorPartidas, ValidadorPlanoContas validadorPlanoContas) {
        this.validadorPartidas = Objects.requireNonNull(validadorPartidas);
        this.validadorPlanoContas = Objects.requireNonNull(validadorPlanoContas);
    }

    public void escriturar(LancamentoContabil lancamento) {
        Objects.requireNonNull(lancamento, "Lancamento nao pode ser nulo");
        validadorPartidas.validar(lancamento);
        validadorPlanoContas.validar(lancamento);
    }
}