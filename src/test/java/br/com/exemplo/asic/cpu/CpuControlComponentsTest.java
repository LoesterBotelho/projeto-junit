package br.com.exemplo.asic.cpu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuControlComponentsTest {

    @Test
    @DisplayName("Deve gerenciar corretamente o fluxo do Program Counter")
    void testProgramCounter() {
        ProgramCounter pc = new ProgramCounter();

        assertEquals(0, pc.getValor());

        pc.incrementar();
        assertEquals(1, pc.getValor());

        pc.saltar(0x42);
        assertEquals(0x42, pc.getValor());

        // Teste de overflow/ciclo de 8 bits
        pc.saltar(255);
        pc.incrementar();
        assertEquals(0, pc.getValor(), "PC deve dar a volta em 8 bits");

        pc.resetar();
        assertEquals(0, pc.getValor());
    }

    @Test
    @DisplayName("Deve armazenar o opcode atual no Registrador de Instrução")
    void testRegistradorInstrucao() {
        RegistradorInstrucao ir = new RegistradorInstrucao();

        assertEquals(0, ir.getOpcode());

        ir.carregar(0x04); // ADD
        assertEquals(0x04, ir.getOpcode());
    }
}