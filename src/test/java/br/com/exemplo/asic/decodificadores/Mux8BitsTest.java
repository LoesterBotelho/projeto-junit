package br.com.exemplo.asic.decodificadores;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Mux8BitsTest {

    @Test
    @DisplayName("Deve selecionar corretamente o canal com base no sinal lógico")
    void testMux2Para1() {
        Mux8Bits mux = new Mux8Bits();

        int valorA = 42;
        int valorB = 99;

        // Seletor em LOW (0) deve escolher a entrada 0 (valorA)
        assertEquals(valorA, mux.selecionar(valorA, valorB, NivelLogico.LOW));

        // Seletor em HIGH (1) deve escolher a entrada 1 (valorB)
        assertEquals(valorB, mux.selecionar(valorA, valorB, NivelLogico.HIGH));
    }

    @Test
    @DisplayName("Deve selecionar corretamente entre 4 vias de dados usando NivelLogico")
    void testMux4Vias() {
        Mux8Bits mux = new Mux8Bits();

        assertEquals(10, mux.selecionar4vias(10, 20, 30, 40, NivelLogico.LOW, NivelLogico.LOW));   // Sel 00 -> 10
        assertEquals(20, mux.selecionar4vias(10, 20, 30, 40, NivelLogico.HIGH, NivelLogico.LOW));  // Sel 01 -> 20
        assertEquals(30, mux.selecionar4vias(10, 20, 30, 40, NivelLogico.LOW, NivelLogico.HIGH));  // Sel 10 -> 30
        assertEquals(40, mux.selecionar4vias(10, 20, 30, 40, NivelLogico.HIGH, NivelLogico.HIGH)); // Sel 11 -> 40
    }
}