package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContaContabilTest {
    @Test
    void deveCriarContaAnaliticaComSucesso() {
        ContaContabil conta = new ContaContabil("1.1", "Ativo Circulante", TipoNaturezaConta.DEVEDORA, true);
        assertEquals("1.1", conta.getCodigo());
        assertTrue(conta.isAnalitica());
        assertTrue(conta.isAtiva());
    }

    @Test
    void devePermitirInativarConta() {
        ContaContabil conta = new ContaContabil("1.1", "Ativo", TipoNaturezaConta.DEVEDORA, true);
        conta.inativar();
        assertFalse(conta.isAtiva());
    }
}