package br.com.exemplo.asic.registradores;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlipFlopDTest {

    @Test
    @DisplayName("Deve armazenar o dado apenas na borda de subida do clock")
    void testFlipFlopDBordaDeSubida() {
        FlipFlopD ff = new FlipFlopD();


        assertEquals(NivelLogico.LOW, ff.getSaida());


        ff.clock(NivelLogico.LOW, NivelLogico.HIGH);
        assertEquals(NivelLogico.LOW, ff.getSaida(), "Não deve mudar se o clock não subiu");


        ff.clock(NivelLogico.HIGH, NivelLogico.HIGH);
        assertEquals(NivelLogico.HIGH, ff.getSaida(), "Deve capturar o HIGH na borda de subida");


        ff.clock(NivelLogico.HIGH, NivelLogico.LOW);
        assertEquals(NivelLogico.HIGH, ff.getSaida(), "Deve manter o valor anterior se não houve nova borda de subida");


        ff.clock(NivelLogico.LOW, NivelLogico.LOW); 
        ff.clock(NivelLogico.HIGH, NivelLogico.LOW); 
        assertEquals(NivelLogico.LOW, ff.getSaida(), "Deve capturar o LOW na nova borda de subida");
    }
}