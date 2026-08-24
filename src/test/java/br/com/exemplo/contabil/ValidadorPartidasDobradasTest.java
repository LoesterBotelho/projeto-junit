package br.com.exemplo.contabil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorPartidasDobradasTest {
    private ValidadorPartidasDobradas validador;
    private ContaContabil c1, c2;

    @BeforeEach
    void setUp() {
        validador = new ValidadorPartidasDobradas();
        c1 = new ContaContabil("1", "Banco", TipoNaturezaConta.DEVEDORA, true);
        c2 = new ContaContabil("2", "Receita", TipoNaturezaConta.CREDORA, true);
    }

    @Test
    void deveAprovarQuandoDebitosIguaisCreditos() {
        LancamentoContabil l = new LancamentoContabil("1", LocalDate.now(), "Teste");
        l.adicionarItem(new ItemLancamento(c1, new BigDecimal("100.00"), TipoLancamentoItem.DEBITO));
        l.adicionarItem(new ItemLancamento(c2, new BigDecimal("100.00"), TipoLancamentoItem.CREDITO));
        assertDoesNotThrow(() -> validador.validar(l));
    }

    @Test
    void deveLancarExcecaoQuandoDebitosDiferentesCreditos() {
        LancamentoContabil l = new LancamentoContabil("2", LocalDate.now(), "Desequilibrado");
        l.adicionarItem(new ItemLancamento(c1, new BigDecimal("150.00"), TipoLancamentoItem.DEBITO));
        l.adicionarItem(new ItemLancamento(c2, new BigDecimal("100.00"), TipoLancamentoItem.CREDITO));
        assertThrows(IllegalStateException.class, () -> validador.validar(l));
    }
}