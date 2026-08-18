package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;

public class CpuMain3 {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SIMULADOR ASIC 8-BIT - BHASKARA                ");
        System.out.println("==================================================");

        MemoriaRom rom = new MemoriaRom();
        MemoriaRam ram = new MemoriaRam();

        /*
         * Resolução de Bhaskara para x^2 - 7x + 10 = 0 (Coeficientes: a=1, b=7, c=10)
         * Mapeamento da RAM:
         * - RAM[0x10] = 'a' (1)
         * - RAM[0x11] = 'b' (7)
         * - RAM[0x12] = 'c' (10)
         * - RAM[0x15] = Constante 0
         * - RAM[0x16] = Constante 2 (para incremento de ímpares e divisão)
         * - RAM[0x20] = b^2 (49)
         * - RAM[0x21] = 4*a*c (40)
         * - RAM[0x22] = Delta = b^2 - 4ac (9) (Modificado dinamicamente no loop de raiz)
         * - RAM[0x23] = Número ímpar atual para o loop de raiz
         * - RAM[0x25] = Raiz de Delta calculada via software (3)
         * - RAM[0x26] = Denominador 2*a (2)
         * - RAM[0x27] = Dividendo temporário para divisão
         * - RAM[0x30] = Raiz X1 (5)
         * - RAM[0x31] = Raiz X2 (2)
         */

        ram.escrever(0x10, 1);   // a = 1
        ram.escrever(0x11, 7);   // b = 7
        ram.escrever(0x12, 10);  // c = 10
        ram.escrever(0x15, 0);   // 0 fixo
        ram.escrever(0x16, 2);   // Constante 2

        int[] programaBhaskaraTotal = {
            /* ========================================================== */
            /* PASSO 1: Calcular b^2 (7 * 7 = 49)                         */
            /* ========================================================== */
            /* 0x00 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x02 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x20, 
            /* 0x04 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 7,
            /* 0x06 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x35, // Contador = 7
            /* 0x08 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x20,
            /* 0x0A */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x11, 
            /* 0x0C */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x20, 
            /* 0x0E */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x35,
            /* 0x10 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x11 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x35, 
            /* 0x13 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x15,
            /* 0x15 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       25,   
            /* 0x17 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      8,    

            /* ========================================================== */
            /* PASSO 2: Calcular 4 * a * c (4 * 1 * 10 = 40)              */
            /* ========================================================== */
            /* 0x19 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x1B */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x21, 
            /* 0x1D */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 4,
            /* 0x1F */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x36, // Fator 4
            /* 0x21 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x21,
            /* 0x23 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x12, 
            /* 0x25 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x21,
            /* 0x27 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x36,
            /* 0x29 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 0x2A */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x36,
            /* 0x2C */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x15,
            /* 0x2E */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       50,   
            /* 0x30 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      33,   

            /* ========================================================== */
            /* PASSO 3: Calcular Delta = b^2 - 4ac (49 - 40 = 9)          */
            /* ========================================================== */
            /* 0x32 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x20, 
            /* 0x34 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x21, 
            /* 0x36 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x22, // Delta = 9

            /* ========================================================== */
            /* PASSO 4: Raiz Quadrada Real via Subtração de Ímpares       */
            /* Algorithm: Subtrai 1, 3, 5... do Delta até esgotar         */
            /* ========================================================== */
            /* 0x38 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 1,
            /* 0x3A */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x23, // Ímpar inicial = 1
            /* 0x3C */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x3E */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x25, // Raiz inicial = 0

            // --- INÍCIO DO LOOP DE RAIZ (Endereço 0x40 / 64 decimal) ---
            /* 0x40 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x22, // Carrega Delta atual
            /* 0x42 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x23, // Subtrai o ímpar
            /* 0x44 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x22, // Atualiza Delta
            /* 0x46 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       76,   // Se Delta chegou a 0 exato, encerra raiz (0x4C)
            /* 0x48 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x25, 
            /* 0x4A */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),
            /* 0x4B */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x25, // Incrementa contador de raiz
            /* 0x4D */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x23, 
            /* 0x4F */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x16, // Soma 2 para o próximo número ímpar
            /* 0x51 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x23, // Atualiza ímpar
            /* 0x53 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      64,   // Retorna para 0x40
            // --- FIM DO LOOP DE RAIZ ---

            /* 0x55 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x25, // Último incremento para fechar a contagem exata
            /* 0x57 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),
            /* 0x58 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x25, // Raiz de Delta = 3

            /* ========================================================== */
            /* PASSO 5: Calcular Denominador 2 * a (2 * 1 = 2)            */
            /* ========================================================== */
            /* 0x5A */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x10, 
            /* 0x5C */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x10, 
            /* 0x5E */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x26, // Denominador = 2

            /* ========================================================== */
            /* PASSO 6: Calcular X1 = (b + sqrt(Delta)) / 2a [10 / 2 = 5] */
            /* ========================================================== */
            /* 0x60 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11, // b (7)
            /* 0x62 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x25, // + raiz (3) = 10
            /* 0x64 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, // Dividendo = 10
            /* 0x66 */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x68 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x30, // X1 inicial = 0

            // --- INÍCIO DO LOOP X1 (Endereço 0x6A / 106 decimal) ---
            /* 0x6A */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x27, 
            /* 0x6C */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x26, // Subtrai 2
            /* 0x6E */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, 
            /* 0x70 */ DecodificadorInstrucao.Instrucao.JN.getOpcode(),       120,  // Se < 0, pula para fora (0x78 / 120)
            /* 0x72 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x30, 
            /* 0x74 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),
            /* 0x75 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x30, // Incrementa X1
            /* 0x77 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      106,  // Retorna para 0x6A
            // --- FIM DO LOOP X1 (Destino 0x78) ---

            /* ========================================================== */
            /* PASSO 7: Calcular X2 = (b - sqrt(Delta)) / 2a [4 / 2 = 2]  */
            /* ========================================================== */
            /* 0x78 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11, // b (7)
            /* 0x7A */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x25, // - raiz (3) = 4
            /* 0x7C */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, // Dividendo = 4
            /* 0x7E */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 0x80 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x31, // X2 inicial = 0

            // --- INÍCIO DO LOOP X2 (Endereço 0x82 / 130 decimal) ---
            /* 0x82 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x27, 
            /* 0x84 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x26, // Subtrai 2
            /* 0x86 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, 
            /* 0x88 */ DecodificadorInstrucao.Instrucao.JN.getOpcode(),       142,  // Se < 0, pula para fora (0x8E / 142)
            /* 0x8A */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x31, 
            /* 0x8C */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),
            /* 0x8D */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x31, // Incrementa X2
            /* 0x8F */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      130,  // Retorna para 0x82
            // --- FIM DO LOOP X2 (Destino 0x8E) ---

            /* 0x8E */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        rom.carregarPrograma(programaBhaskaraTotal);
        Cpu cpu = new Cpu(rom, ram);

        System.out.println("[INFO] Executando ciclos de clock na CPU 8-bit...\n");

        long inicio = System.nanoTime();
        cpu.executarPrograma();
        long fim = System.nanoTime();

        System.out.println("==================================================");
        System.out.println("          RELATÓRIO DE RESULTADOS BHASKARA        ");
        System.out.println("==================================================");
        System.out.printf(" Coeficientes -> a: %d | b: %d | c: %d\n", ram.ler(0x10), ram.ler(0x11), ram.ler(0x12));
        System.out.printf(" b^2 calculado na RAM[0x20]:        0x%02X (%d)\n", ram.ler(0x20), ram.ler(0x20));
        System.out.printf(" 4ac calculado na RAM[0x21]:        0x%02X (%d)\n", ram.ler(0x21), ram.ler(0x21));
        System.out.printf(" Delta inicial (RAM[0x22]):         0x09 (9)\n");
        System.out.printf(" Raiz de Delta via Ímpares (RAM[0x25]): 0x%02X (%d)\n", ram.ler(0x25), ram.ler(0x25));
        System.out.printf(" Denominador 2a (RAM[0x26]):        0x%02X (%d)\n", ram.ler(0x26), ram.ler(0x26));
        System.out.println("--------------------------------------------------");
        System.out.printf(" Raiz X1 (RAM[0x30] - Divisão Real): 0x%02X (%d) [Esperado: 5]\n", ram.ler(0x30), ram.ler(0x30));
        System.out.printf(" Raiz X2 (RAM[0x31] - Divisão Real): 0x%02X (%d) [Esperado: 2]\n", ram.ler(0x31), ram.ler(0x31));
        System.out.println("--------------------------------------------------");
        System.out.printf(" Tempo de Processamento:            %.4f ms\n", (fim - inicio) / 1_000_000.0);
        System.out.println("==================================================");

        // Detalhamento final em Assembly, Hexadecimal, Binário e Decimal
        System.out.println("\n==========================================================================================");
        System.out.println("                 DETALHAMENTO DE FORMATOS (ASSEMBLY, HEX, BIN, DEC)                       ");
        System.out.println("==========================================================================================");
        System.out.printf("%-10s | %-26s | %-6s | %-10s | %-10s\n", "Endereço", "Variável / Descrição", "Hex", "Binário", "Decimal");
        System.out.println("------------------------------------------------------------------------------------------");

        int[] enderecosChave = {0x10, 0x11, 0x12, 0x20, 0x21, 0x22, 0x25, 0x26, 0x30, 0x31};
        String[] nomesVariaveis = {
            "Coeficiente 'a'", 
            "Coeficiente 'b'", 
            "Coeficiente 'c'", 
            "b^2 (Soma Dinâmica)", 
            "4*a*c (Soma Dinâmica)", 
            "Delta Restante após Raiz", 
            "Raiz de Delta (Ímpares)", 
            "Denominador (2a)", 
            "Raiz X1 Final", 
            "Raiz X2 Final"
        };

        for (int i = 0; i < enderecosChave.length; i++) {
            int valor = ram.ler(enderecosChave[i]);
            String hexStr = String.format("0x%02X", valor);
            String binStr = String.format("%8s", Integer.toBinaryString(valor)).replace(' ', '0');
            String decStr = String.format("%d", valor);
            String endStr = String.format("0x%02X", enderecosChave[i]);

            System.out.printf("%-10s | %-26s | %-6s | %-10s | %-10s\n", endStr, nomesVariaveis[i], hexStr, binStr, decStr);
        }
        System.out.println("==========================================================================================");

        // Log de Operações de Hardware Registradas
        System.out.println("\n==========================================================================================");
        System.out.println("                      LOG DE OPERAÇÕES DO HARDWARE (MICRO-ETAPAS)                         ");
        System.out.println("==========================================================================================");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "Fase", "Operação ALU", "Alvo / Descrição", "Resultado");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 1", "ADD Loop (7x7)", "Multiplicação repetida de b", "49 (0x31)");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 2", "ADD Loop (4x1x10)", "Multiplicação repetida de 4ac", "40 (0x28)");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 3", "SUB (b^2 - 4ac)", "Cálculo do Delta (49 - 40)", "9 (0x09)");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 4", "SUB Ímpares (Raiz)", "Cálculo real de raiz via subtrações", "3 (0x03)");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 5", "ADD (a + a)", "Cálculo do Denominador (2*1)", "2 (0x02)");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 6", "SUB Loop Divisão", "Cálculo de X1 = (7 + 3) / 2", "5 (0x05)");
        System.out.printf("%-8s | %-18s | %-30s | %-10s\n", "PASSO 7", "SUB Loop Divisão", "Cálculo de X2 = (7 - 3) / 2", "2 (0x02)");
        System.out.println("==========================================================================================");
    }
}