package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ItemLancamentoTest {
    @Test
    void deveRejeitarValorZeroOuNegativo() {
        ContaContabil conta = new ContaContabil("1.1", "Caixa", TipoNaturezaConta.DEVEDORA, true);
        assertThrows(IllegalArgumentException.class, () -> new ItemLancamento(conta, BigDecimal.ZERO, TipoLancamentoItem.DEBITO));
        assertThrows(IllegalArgumentException.class, () -> new ItemLancamento(conta, new BigDecimal("-10.00"), TipoLancamentoItem.DEBITO));
    }
}