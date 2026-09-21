package br.com.exemplo.saas;

import java.math.BigDecimal;

public class Plano {
    private String nome;
    private BigDecimal valorMensal;

    public Plano(String nome, BigDecimal valorMensal) {
        this.nome = nome;
        this.valorMensal = valorMensal;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }
}