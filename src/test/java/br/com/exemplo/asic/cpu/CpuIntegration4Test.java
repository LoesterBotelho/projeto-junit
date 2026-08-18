package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuIntegration4Test {

    @Test
    @DisplayName("Deve executar algoritmo de multiplicação por somas sucessivas utilizando loops e desvios condicionais")
    void testMultiplicacaoPorSomasSucessivas() {
        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        ram.escrever(0x10, 6);  // Multiplicando (fator A)
        ram.escrever(0x11, 7);  // Multiplicador (fator B / contador)
        ram.escrever(0x12, 0);  // Resultado acumulado inicial

        int[] programa = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x12,
            /* 0x04 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11,
            /* 0x06 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x15, // RAM[0x15] é 0 por padrão
            /* 0x08 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       23,   // Índice 23 aponta para o HALT (0x17)
            /* 0x0A */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x12,
            /* 0x0C */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x10,
            /* 0x0E */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x12,
            /* 0x10 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11,
            /* 0x12 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x13 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x11,
            /* 0x15 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      4,    // Retorna ao índice 4
            /* 0x17 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programa);

        Cpu cpu = new Cpu(rom, ram);
        cpu.executarPrograma();

        assertEquals(42, ram.ler(0x12), "O resultado da multiplicação de 6 por 7 deve ser 42 na RAM");
    }
}