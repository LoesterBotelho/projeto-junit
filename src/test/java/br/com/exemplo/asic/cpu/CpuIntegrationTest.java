package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuIntegrationTest {

    @Test
    @DisplayName("Deve executar um programa completo na CPU utilizando todas as principais instruções")
    void testExecucaoTodasInstrucoesCpu() {
        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        ram.escrever(0x50, 10);
        ram.escrever(0x51, 5);
        ram.escrever(0x52, 42);

        int[] programa = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 20,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x50,
            /* 0x04 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x51,
            /* 0x06 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),
            /* 0x07 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x08 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x60,
            /* 0x0A */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x60,
            /* 0x0C */ DecodificadorInstrucao.Instrucao.AND.getOpcode(),      0x51,
            /* 0x0E */ DecodificadorInstrucao.Instrucao.OR.getOpcode(),       0x50,
            /* 0x10 */ DecodificadorInstrucao.Instrucao.SHL.getOpcode(),
            /* 0x11 */ DecodificadorInstrucao.Instrucao.SHR.getOpcode(),
            /* 0x12 */ DecodificadorInstrucao.Instrucao.NOT.getOpcode(),
            /* 0x13 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 42,
            /* 0x15 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x52,
            /* 0x17 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       27,   // Salta para o índice 27 (LOAD_IMM 99)
            /* 0x19 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      99,
            /* 0x1B */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 99,   // Início em 27
            /* 0x1D */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x70,
            /* 0x1F */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programa);

        Cpu cpu = new Cpu(rom, ram);
        cpu.executarPrograma();

        assertEquals(99, cpu.getAcumuladorValor(), "O acumulador deve conter o valor final 99 após o desvio condicional JZ");
        assertEquals(99, ram.ler(0x70), "O valor final 99 deve ter sido armazenado na RAM no endereço 0x70");
    }
}