package br.com.exemplo.modelo1;

import java.math.BigDecimal;

public record Produto(String codigo, String nome, BigDecimal preco) {
    public Produto {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
        }
    }
}