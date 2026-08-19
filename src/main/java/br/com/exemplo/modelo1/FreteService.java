package br.com.exemplo.modelo1;

import java.math.BigDecimal;

public class FreteService {

    public BigDecimal calcularFrete(String regiao, BigDecimal valorPedido) {

        if (valorPedido != null && valorPedido.compareTo(new BigDecimal("300.00")) >= 0) {
            return BigDecimal.ZERO;
        }

        return switch (regiao.toUpperCase()) {
            case "SUDESTE" -> new BigDecimal("25.00");
            case "SUL" -> new BigDecimal("30.00");
            case "NORDESTE" -> new BigDecimal("45.00");
            default -> new BigDecimal("50.00");
        };
    }
}