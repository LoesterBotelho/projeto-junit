package br.com.exemplo.saas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

public class FaturamentoAssinaturaService {

    public BigDecimal calcularProrateUpgrade(Assinatura assinatura, Plano novoPlano, java.time.LocalDate dataMudanca) {
    	
        long diasTotais = ChronoUnit.DAYS.between(assinatura.getDataInicioCiclo(), assinatura.getDataFimCiclo()) + 1;
        long diasUtilizados = ChronoUnit.DAYS.between(assinatura.getDataInicioCiclo(), dataMudanca);
        long diasRestantes = Math.max(0, diasTotais - diasUtilizados);

        if (diasTotais <= 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        // Valor diário do plano antigo e do novo plano
        BigDecimal valorDiarioAntigo = assinatura.getPlanoAtual().getValorMensal()
                .divide(BigDecimal.valueOf(diasTotais), 4, RoundingMode.HALF_UP);
        
        BigDecimal valorDiarioNovo = novoPlano.getValorMensal()
                .divide(BigDecimal.valueOf(diasTotais), 4, RoundingMode.HALF_UP);

        // Crédito proporcional do plano antigo não utilizado
        BigDecimal creditoAntigo = valorDiarioAntigo.multiply(BigDecimal.valueOf(diasRestantes));
        
        // Débito proporcional do novo plano pelos dias restantes
        BigDecimal debitoNovo = valorDiarioNovo.multiply(BigDecimal.valueOf(diasRestantes));

        // Valor a pagar pela diferença (Pró-rata)
        BigDecimal valorDiferenca = debitoNovo.subtract(creditoAntigo);

        return valorDiferenca.setScale(2, RoundingMode.HALF_UP);
    }
}