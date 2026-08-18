package br.com.exemplo.asic.memoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoriaTest {

    @Test
    @DisplayName("Deve escrever e ler valores corretamente na RAM")
    void testRamLeituraEscrita() {
        MemoriaRam ram = new MemoriaRam();

        ram.escrever(0x10, 123);
        assertEquals(123, ram.ler(0x10));

        // Valida máscara de 8 bits (estouro de byte)
        ram.escrever(0x20, 300); // 300 & 0xFF = 44
        assertEquals(44, ram.ler(0x20));
    }

    @Test
    @DisplayName("Deve carregar e ler instruções na ROM")
    void testRomPrograma() {
        MemoriaRom rom = new MemoriaRom();
        int[] programa = {0x03, 0x10, 0x04, 0x20, 0x11}; // LOAD_IMM, 16, ADD, 32, HALT

        rom.carregarPrograma(programa);

        assertEquals(0x03, rom.lerInstrucao(0));
        assertEquals(0x10, rom.lerInstrucao(1));
        assertEquals(0x04, rom.lerInstrucao(2));
        assertEquals(0x11, rom.lerInstrucao(4));
    }
}