package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpuIntegration2Test {

    @Test
    @DisplayName("Deve executar teste de estresse e validação de fluxo reverso com JNZ e JMP")
    void testFluxoJnzJmpCpu() {
        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        ram.escrever(0x30, 5); // Contador regressivo inicial

        /*
         * Roteiro do Programa (Loop de contagem regressiva até zero):
         * -----------------------------------------------------------------
         * 0x00: LOAD 0x30        -> AC = RAM[0x30] (Carrega 5)
         * 0x02: STORE 0x40       -> RAM[0x40] = AC (Salva o contador atual)
         * 0x04: DEC              -> AC = AC - 1
         * 0x05: STORE 0x30       -> RAM[0x30] = AC (Atualiza o contador na RAM)
         * 0x07: CMP 0x50         -> Compara AC com 0 (RAM[0x50] = 0)
         * 0x09: JNZ 0x00         -> Se não for zero, pula de volta para o início (índice 0)
         * 0x0B: HALT             -> Encerra quando o contador chega a zero
         */
        int[] programa = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x30,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x40,
            /* 0x04 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x05 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x30,
            /* 0x07 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x50, // RAM[0x50] é 0 por padrão
            /* 0x09 */ DecodificadorInstrucao.Instrucao.JNZ.getOpcode(),      0,    // Salta para o índice 0 enquanto AC != 0
            /* 0x0B */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programa);

        Cpu cpu = new Cpu(rom, ram);
        cpu.executarPrograma();

        assertEquals(0, cpu.getAcumuladorValor(), "O acumulador deve zerar ao término do loop");
        assertEquals(0, ram.ler(0x30), "O contador na RAM deve ser zero");
    }
}