package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LancamentoContabilTest {
    @Test
    void deveCriarLancamentoComSucesso() {
        LocalDate hoje = LocalDate.now();
        LancamentoContabil lancamento = new LancamentoContabil("999", hoje, "Pagamento");
        assertEquals("999", lancamento.getId());
        assertEquals("Pagamento", lancamento.getHistorico());
    }
}