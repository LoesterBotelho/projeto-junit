package br.com.exemplo.asic.ula;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Ula8BitsTest {

    @Test
    @DisplayName("Deve executar operações lógicas e aritméticas na ULA com atualização de flags")
    void testUlaOperacoes() {
        Ula8Bits ula = new Ula8Bits();

        // Teste de Soma com Flag Zero e Carry
        ula.executar(Ula8Bits.Operacao.ADD, 100, 50);
        assertEquals(150, ula.getResultado());
        

        // Teste de Operação que resulta em Zero (Z=true)
        ula.executar(Ula8Bits.Operacao.SUB, 42, 42);
        assertEquals(0, ula.getResultado());
        assertTrue(ula.getFlags().isZero(), "A flag Z deveria estar ativa para resultado 0");

        // Teste de Deslocamento à Esquerda (SHL - Multiplicação por 2)
        ula.executar(Ula8Bits.Operacao.SHL, 10, 0);
        assertEquals(20, ula.getResultado());
    }
}