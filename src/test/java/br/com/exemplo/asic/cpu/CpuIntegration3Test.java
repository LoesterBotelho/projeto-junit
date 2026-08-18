package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuIntegration3Test {

    @Test
    @DisplayName("Deve executar teste avançado combinando lógica booleana e desvio incondicional JMP")
    void testFluxoLogicoJmpCpu() {
        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        ram.escrever(0x20, 0x0F); // Máscara de bits (nibble inferior ativo)
        ram.escrever(0x21, 0xF0); // Máscara de bits (nibble superior ativo)

        /*
         * Roteiro do Programa:
         * -----------------------------------------------------------------
         * 0x00: LOAD_IMM 0xAA    -> AC = 0xAA (10101010 em binário)
         * 0x02: AND 0x20         -> AC = AC & RAM[0x20] (Isola o nibble inferior -> 0x0A)
         * 0x04: JMP 0x08         -> Salta incondicionalmente para o bloco final (pula o bloco intermediário)
         * 0x06: LOAD_IMM 0xFF    -> (Bloco que DEVE SER PULADO) AC = 0xFF
         * 0x08: OR 0x21          -> (Destino do JMP) AC = AC | RAM[0x21] (0x0A | 0xF0 = 0xFA / 250)
         * 0x0A: STORE 0x55       -> RAM[0x55] = AC
         * 0x0C: HALT             -> Encerra a execução
         */
        int[] programa = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0xAA,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.AND.getOpcode(),      0x20, // Corrigido para AND
            /* 0x04 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      8,    // Salta para o índice 8
            /* 0x06 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0xFF, // Seria pulado
            /* 0x08 */ DecodificadorInstrucao.Instrucao.OR.getOpcode(),       0x21, // Índice 8: OR 0x21
            /* 0x0A */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x55,
            /* 0x0C */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programa);

        Cpu cpu = new Cpu(rom, ram);
        cpu.executarPrograma();

        assertEquals(250, cpu.getAcumuladorValor(), "O acumulador deve conter o resultado correto após a operação lógica com desvio");
        assertEquals(250, ram.ler(0x55), "O resultado final deve ser persistido na RAM no endereço 0x55");
    }
}