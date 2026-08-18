package br.com.exemplo.asic.registradores;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Registrador8BitsTest {

    @Test
    @DisplayName("Deve armazenar e recuperar um valor de 8 bits na borda de subida do clock")
    void testRegistradorArmazenamento() {
        Registrador8Bits reg = new Registrador8Bits();


        assertEquals(0, reg.getValor());


        reg.clock(NivelLogico.LOW, 170);
        assertEquals(0, reg.getValor(), "Não deve alterar o valor se não houve borda de subida");


        reg.clock(NivelLogico.HIGH, 170);
        assertEquals(170, reg.getValor(), "Deve armazenar o valor 170 na borda de subida");


        reg.clock(NivelLogico.LOW, 15);
        assertEquals(170, reg.getValor(), "Deve manter o valor antigo durante a descida do clock");


        reg.clock(NivelLogico.HIGH, 15);
        assertEquals(15, reg.getValor(), "Deve atualizar para o novo valor 15");
    }
}