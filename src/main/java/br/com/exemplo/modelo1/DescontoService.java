package br.com.exemplo.modelo1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontoService {

    public BigDecimal aplicarDesconto(BigDecimal valorTotal, CupomDesconto cupom) {
        if (valorTotal == null || valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        if (cupom == null) {
            return valorTotal;
        }

        BigDecimal valorDesconto = valorTotal
                .multiply(cupom.percentual())
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_EVEN);

        return valorTotal.subtract(valorDesconto);
    }
}