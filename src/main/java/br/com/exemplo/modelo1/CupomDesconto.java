package br.com.exemplo.modelo1;


import java.math.BigDecimal;

public record CupomDesconto(String codigo, BigDecimal percentual) {
    public CupomDesconto {
        if (percentual == null || percentual.compareTo(BigDecimal.ZERO) < 0 || percentual.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("O percentual de desconto deve estar entre 0 e 100.");
        }
    }
}