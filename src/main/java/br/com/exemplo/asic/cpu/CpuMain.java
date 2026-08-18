package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;

public class CpuMain {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       INICIALIZANDO SIMULADOR ASIC 8-BIT         ");
        System.out.println("==================================================");

        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        // Configuração inicial de memória RAM (Exemplo: Sequência de Fibonacci)
        ram.escrever(0x20, 0);  // F(n-2) inicial
        ram.escrever(0x21, 1);  // F(n-1) inicial
        ram.escrever(0x30, 6);  // Contador de iterações
        ram.escrever(0x31, 0);  // Constante zero para comparações

        // Programa em Assembly (Fibonacci)
        int[] programa = {
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x30,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x31,
            /* 0x04 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       27,
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
            /* 0x1B */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programa);
        Cpu cpu = new Cpu(rom, ram);

        System.out.println("[INFO] Programa carregado na ROM com sucesso.");
        System.out.println("[INFO] Iniciando execução da CPU...\n");

        long tempoInicio = System.currentTimeMillis();
        cpu.executarPrograma();
        long tempoFim = System.currentTimeMillis();

        System.out.println("==================================================");
        System.out.println("           ESTADO FINAL DA CPU E MEMÓRIA          ");
        System.out.println("==================================================");
        System.out.printf(" Acumulador (AC): 0x%02X (%d)\n", cpu.getAcumuladorValor(), cpu.getAcumuladorValor());
        System.out.printf(" Tempo de Execução: %d ms\n", (tempoFim - tempoInicio));
        System.out.println("--------------------------------------------------");
        System.out.println(" Conteúdo dos Registradores e Posições Chave da RAM:");
        System.out.printf("  - RAM[0x20] (F(n-2)): 0x%02X (%d)\n", ram.ler(0x20), ram.ler(0x20));
        System.out.printf("  - RAM[0x21] (F(n-1)): 0x%02X (%d)\n", ram.ler(0x21), ram.ler(0x21));
        System.out.printf("  - RAM[0x23] (Resultado F(n)): 0x%02X (%d)\n", ram.ler(0x23), ram.ler(0x23));
        System.out.printf("  - RAM[0x30] (Contador Restante): 0x%02X (%d)\n", ram.ler(0x30), ram.ler(0x30));
        System.out.println("==================================================");
    }
}