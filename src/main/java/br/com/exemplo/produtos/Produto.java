
package br.com.exemplo.produtos;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {

    private final Long id;
    private final String nome;
    private final BigDecimal preco;
    private int estoque;

    public Produto(Long id, String nome, BigDecimal preco, int estoque) {
        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
        this.preco = Objects.requireNonNull(preco);
        this.estoque = estoque;

        if (nome.isBlank() || preco.signum() <= 0 || estoque < 0) {
            throw new IllegalArgumentException("Dados inválidos.");
        }
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade <= 0 || quantidade > estoque) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }

        estoque -= quantidade;
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

    public int getEstoque() {
        return estoque;
    }
}