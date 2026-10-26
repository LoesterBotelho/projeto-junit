package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;

public class Produto {

    private final Long id;
    private final String nome;
    private final BigDecimal preco;

    public Produto(Long id, String nome, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }
    
}