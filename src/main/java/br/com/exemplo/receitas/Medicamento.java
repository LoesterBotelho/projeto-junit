package br.com.exemplo.receitas;

import java.util.Objects;

public class Medicamento {

    private final Long id;
    private final String nome;
    private final String principioAtivo;
    private final String concentracao;

    public Medicamento(
            Long id,
            String nome,
            String principioAtivo,
            String concentracao) {

        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
        this.principioAtivo = Objects.requireNonNull(principioAtivo);
        this.concentracao = Objects.requireNonNull(concentracao);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public String getConcentracao() {
        return concentracao;
    }
    
}