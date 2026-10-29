package br.com.exemplo.receitas;

import java.util.Objects;

public class Doutor {

    private final Long id;
    private final String nome;
    private final String crm;
    private final String uf;

    public Doutor(
            Long id,
            String nome,
            String crm,
            String uf) {

        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
        this.crm = Objects.requireNonNull(crm);
        this.uf = Objects.requireNonNull(uf);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public String getUf() {
        return uf;
    }
    
}