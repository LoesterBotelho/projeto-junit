package br.com.exemplo.asic.registradores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistradorFlagsTest {

    @Test
    @DisplayName("Deve atualizar e refletir corretamente os estados das flags de hardware")
    void testAtualizacaoFlags() {
        RegistradorFlags flags = new RegistradorFlags();

        // Caso 1: Resultado Zero
        flags.atualizar(0, false, false);
        assertTrue(flags.isZero(), "Flag Z deve estar ativa para 0");
        assertFalse(flags.isNegativo());

        // Caso 2: Resultado Negativo (Bit 7 ativado, ex: 130 em decimal = 0x82)
        flags.atualizar(130, false, false);
        assertFalse(flags.isZero());
        assertTrue(flags.isNegativo(), "Flag N deve estar ativa quando o MSB for 1");

        // Caso 3: Com Carry e Overflow
        flags.atualizar(50, true, true);
        assertTrue(flags.isCarry());
        assertTrue(flags.isOverflow());
    }
}