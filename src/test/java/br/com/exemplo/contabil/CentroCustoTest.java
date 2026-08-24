package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CentroCustoTest {
    @Test
    void deveCriarCentroCustoAtivo() {
        CentroCusto cc = new CentroCusto("10", "Administrativo");
        assertEquals("10", cc.getCodigo());
        assertTrue(cc.isAtivo());
    }

    @Test
    void deveInativarCentroCusto() {
        CentroCusto cc = new CentroCusto("10", "Administrativo");
        cc.inativar();
        assertFalse(cc.isAtivo());
    }
}