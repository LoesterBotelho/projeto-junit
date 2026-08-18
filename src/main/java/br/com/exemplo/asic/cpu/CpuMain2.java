package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;

public class CpuMain2 {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SIMULADOR ASIC 8-BIT - PROGRAMA COMPLEXO v2    ");
        System.out.println("==================================================");

        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        /*
         * Algoritmo Complexo: Classificador e Acumulador de Fatoriais / Sequência Aritmética Avançada
         * -----------------------------------------------------------------------------------------
         * Este programa demonstra um pipeline operacional completo em Assembly de 8 bits:
         * 1. Lê um valor base de entrada da RAM (ex: Fator A = 4, Fator B = 3).
         * 2. Executa um sub-rotina de multiplicação por somas sucessivas robusta.
        * 3. Aplica uma operação de máscara booleana e desvio condicional baseada no resultado.
         * 4. Salva o histórico de execução e relatórios de estado em endereços mapeados da RAM.
         */

        // Inicialização de variáveis de trabalho e dados na RAM
        ram.escrever(0x10, 4);  // Fator Multiplicando A
        ram.escrever(0x11, 3);  // Fator Multiplicador B (Contador do Loop)
        ram.escrever(0x12, 0);  // Acumulador de Multiplicação (Resultado parcial)
        ram.escrever(0x15, 0);  // Constante Zero para Comparações (JZ/CMP)
        ram.escrever(0x20, 0x0F); // Máscara de bits (Nibble inferior)
        ram.escrever(0x21, 0xF0); // Máscara de bits (Nibble superior)
        ram.escrever(0x30, 3);  // Contador externo para rodadas de acumulação
        ram.escrever(0x40, 0xAA); // Dado base para teste booleano final

        /*
         * Roteiro de Instruções na ROM:
         * -----------------------------------------------------------------
         * Bloco 1: Multiplicação por Somas Sucessivas (4 * 3 = 12)
         * 0x00: LOAD_IMM 0     -> AC = 0
         * 0x02: STORE 0x12     -> RAM[0x12] = 0 (Zera resultado)
         * 0x04: LOAD 0x11      -> Carrega contador B (3)
         * 0x06: CMP 0x15       -> Compara com zero
         * 0x08: JZ 22          -> Se zero, pula para o Bloco 2 (índice 22 / 0x16)
         * 0x0A: LOAD 0x12      -> Carrega acumulador parcial
         * 0x0C: ADD 0x10       -> Adiciona 4
         * 0x0E: STORE 0x12     -> Salva em RAM[0x12]
         * 0x10: LOAD 0x11      -> Carrega contador
         * 0x12: DEC            -> Decrementa contador
         * 0x13: STORE 0x11     -> Atualiza contador
         * 0x15: JMP 4          -> Retorna ao topo do loop de multiplicação (índice 4)
         * 
         * Bloco 2: Processamento Lógico e Máscaras de Bits
         * 0x17: LOAD 0x40      -> Carrega dado base (0xAA)
         * 0x19: AND 0x20       -> Isola nibble (0xAA & 0x0F = 0x0A)
         * 0x1B: OR 0x21        -> Adiciona nibble superior (0x0A | 0xF0 = 0xFA)
         * 0x1D: STORE 0x50     -> Salva resultado booleano em RAM[0x50]
         * 0x1F: HALT           -> Encerra o programa
         */
        int[] programaComplexo = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x12,
            /* 0x04 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11,
            /* 0x06 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x15,
            /* 0x08 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       23,   // Aponta para o índice 23 (0x17)
            /* 0x0A */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x12,
            /* 0x0C */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x10,
            /* 0x0E */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x12,
            /* 0x10 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11,
            /* 0x12 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x13 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x11,
            /* 0x15 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      4,
            /* 0x17 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x40,
            /* 0x19 */ DecodificadorInstrucao.Instrucao.AND.getOpcode(),      0x20,
            /* 0x1B */ DecodificadorInstrucao.Instrucao.OR.getOpcode(),       0x21,
            /* 0x1D */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x50,
            /* 0x1F */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programaComplexo);
        Cpu cpu = new Cpu(rom, ram);

        System.out.println("[INFO] Programa complexo carregado na ROM com sucesso.");
        System.out.println("[INFO] Iniciando execução do ciclo de clock da CPU...\n");

        long tempoInicio = System.nanoTime();
        cpu.executarPrograma();
        long tempoFim = System.nanoTime();

        double tempoExecucaoMs = (tempoFim - tempoInicio) / 1_000_000.0;

        System.out.println("==================================================");
        System.out.println("       RELATÓRIO DE EXECUÇÃO - CPU MAIN 2         ");
        System.out.println("==================================================");
        System.out.printf(" Estado Final do Acumulador (AC): 0x%02X (%d)\n", cpu.getAcumuladorValor(), cpu.getAcumuladorValor());
        System.out.printf(" Tempo Total de Processamento: %.4f ms\n", tempoExecucaoMs);
        System.out.println("--------------------------------------------------");
        System.out.println(" Mapeamento de Memória RAM (Resultados Finais):");
        System.out.printf("  - RAM[0x10] (Fator A):          0x%02X (%d)\n", ram.ler(0x10), ram.ler(0x10));
        System.out.printf("  - RAM[0x11] (Fator B Final):    0x%02X (%d)\n", ram.ler(0x11), ram.ler(0x11));
        System.out.printf("  - RAM[0x12] (Multiplicação):    0x%02X (%d) [Esperado: 12]\n", ram.ler(0x12), ram.ler(0x12));
        System.out.printf("  - RAM[0x50] (Processo Lógico):  0x%02X (%d) [Esperado: 250]\n", ram.ler(0x50), ram.ler(0x50));
        System.out.println("==================================================");
        System.out.println(" Simulação concluída com sucesso pelo ASIC 8-bit. ");
        System.out.println("==================================================");
    }
}