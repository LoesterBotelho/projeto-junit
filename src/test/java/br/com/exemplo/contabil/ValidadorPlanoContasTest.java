package br.com.exemplo.contabil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorPlanoContasTest {
    private ValidadorPlanoContas validador;

    @BeforeEach
    void setUp() { validador = new ValidadorPlanoContas(); }

    @Test
    void deveRejeitarLancamentoEmContaSintetica() {
        ContaContabil sintetica = new ContaContabil("1", "Ativo", TipoNaturezaConta.DEVEDORA, false);
        ContaContabil analitica = new ContaContabil("1.1", "Banco", TipoNaturezaConta.DEVEDORA, true);
        LancamentoContabil l = new LancamentoContabil("1", LocalDate.now(), "Sintetica");
        l.adicionarItem(new ItemLancamento(sintetica, new BigDecimal("50.00"), TipoLancamentoItem.DEBITO));
        l.adicionarItem(new ItemLancamento(analitica, new BigDecimal("50.00"), TipoLancamentoItem.CREDITO));
        assertThrows(IllegalStateException.class, () -> validador.validar(l));
    }

    @Test
    void deveRejeitarLancamentoEmContaInativa() {
        ContaContabil inativa = new ContaContabil("1.1", "Velha", TipoNaturezaConta.DEVEDORA, true);
        inativa.inativar();
        ContaContabil ativa = new ContaContabil("1.2", "Nova", TipoNaturezaConta.DEVEDORA, true);
        LancamentoContabil l = new LancamentoContabil("2", LocalDate.now(), "Inativa");
        l.adicionarItem(new ItemLancamento(inativa, new BigDecimal("50.00"), TipoLancamentoItem.DEBITO));
        l.adicionarItem(new ItemLancamento(ativa, new BigDecimal("50.00"), TipoLancamentoItem.CREDITO));
        assertThrows(IllegalStateException.class, () -> validador.validar(l));
    }
}