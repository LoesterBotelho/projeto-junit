package br.com.exemplo.saas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DescontoAssinaturaService {

    public BigDecimal calcularValorComDesconto(BigDecimal valorOriginal, String cupom, ClienteSaaS cliente, LocalDate dataAtual) {
    	
        if (valorOriginal == null || valorOriginal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor original deve ser maior que zero.");
        }

        BigDecimal valorFinal = valorOriginal;

        // 1. Aplicação de cupons
        if (cupom != null) {
            switch (cupom.toUpperCase()) {
                case "PROMO10":
                    valorFinal = valorFinal.multiply(new BigDecimal("0.90")); // 10% de desconto
                    break;
                case "PROMO20":
                    valorFinal = valorFinal.multiply(new BigDecimal("0.80")); // 20% de desconto
                    break;
                case "BLACKFRIDAY":
                    valorFinal = valorFinal.multiply(new BigDecimal("0.50")); // 50% de desconto
                    break;
                default:
                    throw new IllegalArgumentException("Cupom inválido.");
            }
        }

        // 2. Desconto extra de fidelidade por tempo de casa (mais de 1 ano = 5% adicional)
        if (cliente != null && cliente.getDataCadastro() != null) {
            long anosDeCasa = ChronoUnit.YEARS.between(cliente.getDataCadastro(), dataAtual);
            if (anosDeCasa >= 1) {
                valorFinal = valorFinal.multiply(new BigDecimal("0.95")); 
            }
        }

        // 3. Benefício VIP adicional (mais 5% de desconto acumulado)
        if (cliente != null && cliente.isVip()) {
            valorFinal = valorFinal.multiply(new BigDecimal("0.95"));
        }

        return valorFinal.setScale(2, RoundingMode.HALF_UP);
    }
}