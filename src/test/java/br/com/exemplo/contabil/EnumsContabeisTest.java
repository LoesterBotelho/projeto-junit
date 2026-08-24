package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnumsContabeisTest {
    @Test
    void deveValidarEnums() {
        assertEquals(2, TipoNaturezaConta.values().length);
        assertNotNull(TipoNaturezaConta.valueOf("DEVEDORA"));
        assertEquals(2, TipoLancamentoItem.values().length);
        assertNotNull(TipoLancamentoItem.valueOf("DEBITO"));
    }
}