package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EscrituradorContabilTest {
    @Test
    void deveEscriturarComSucesso() {
        EscrituradorContabil escriturador = new EscrituradorContabil(new ValidadorPartidasDobradas(), new ValidadorPlanoContas());
        ContaContabil c1 = new ContaContabil("1.1", "Caixa", TipoNaturezaConta.DEVEDORA, true);
        ContaContabil c2 = new ContaContabil("2.1", "Fornecedor", TipoNaturezaConta.CREDORA, true);

        LancamentoContabil l = new LancamentoContabil("L1", LocalDate.now(), "Pagamento");
        l.adicionarItem(new ItemLancamento(c1, new BigDecimal("200.00"), TipoLancamentoItem.CREDITO));
        l.adicionarItem(new ItemLancamento(c2, new BigDecimal("200.00"), TipoLancamentoItem.DEBITO));

        assertDoesNotThrow(() -> escriturador.escriturar(l));
    }
}