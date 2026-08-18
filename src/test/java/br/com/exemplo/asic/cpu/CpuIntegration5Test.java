package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuIntegration5Test {

    @Test
    @DisplayName("Deve executar algoritmo de cálculo de Fibonacci isolando o contador de iterações")
    void testSequenciaFibonacci() {
        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        // Configuração inicial de endereços de trabalho na RAM
        ram.escrever(0x20, 0);  // F(n-2) inicial
        ram.escrever(0x21, 1);  // F(n-1) inicial
        ram.escrever(0x30, 6);  // Contador de iterações em 0x30
        ram.escrever(0x31, 0);  // Constante zero auxiliar em 0x31 para comparações

        int[] programa = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x30,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x31,
            /* 0x04 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       27,   // Aponta para o HALT no índice 27
            /* 0x06 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x20,
            /* 0x08 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x21,
            /* 0x0A */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x23,
            /* 0x0C */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x21,
            /* 0x0E */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x20,
            /* 0x10 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x23,
            /* 0x12 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x21,
            /* 0x14 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x30,
            /* 0x16 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x17 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x30,
            /* 0x19 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      0,
            /* 0x1B */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()     // Índice 27
        };

        rom.carregarPrograma(programa);

        Cpu cpu = new Cpu(rom, ram);
        cpu.executarPrograma();

        assertEquals(13, ram.ler(0x23), "O termo resultante de Fibonacci na RAM no endereço 0x23 deve ser 13");
    }
}