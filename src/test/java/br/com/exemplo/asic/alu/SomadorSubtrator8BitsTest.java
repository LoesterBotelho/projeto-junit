package br.com.exemplo.asic.alu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SomadorSubtrator8BitsTest {

    @Test
    @DisplayName("Deve realizar soma de 8 bits corretamente (A + B)")
    void testSomaSimples() {
        SomadorSubtrator8Bits calc = new SomadorSubtrator8Bits();
        
        calc.calcular(10, 20, false); // 10 + 20
        assertEquals(30, calc.getResultado());
    }

    @Test
    @DisplayName("Deve realizar subtração de 8 bits utilizando o Full-Adder (A - B)")
    void testSubtracaoReaproveitandoFullAdder() {
        SomadorSubtrator8Bits calc = new SomadorSubtrator8Bits();
        
        calc.calcular(50, 20, true); // 50 - 20
        assertEquals(30, calc.getResultado());

        calc.calcular(10, 15, true); // 10 - 15 (Resultado negativo em complemento de 2)
        assertEquals(251, calc.getResultado()); // 251 em 8 bits sem sinal representa -5
    }
}