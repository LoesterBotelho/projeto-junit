package br.com.exemplo.asic.cpu;

import br.com.exemplo.asic.decodificadores.DecodificadorInstrucao;
import br.com.exemplo.asic.memoria.MemoriaRam;
import br.com.exemplo.asic.memoria.MemoriaRom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CpuIntegrationBaskaraTest {

    private MemoriaRam ram;

    @BeforeEach
    void setUp() {
        ram = new MemoriaRam();
        ram.escrever(0x15, 0); // Zero fixo
        ram.escrever(0x16, 2); // Constante 2
    }

    @Test
    @DisplayName("Teste do Passo 1: Cálculo de b^2 (7 * 7 = 49)")
    void testeCalculoB2() {
        ram.escrever(0x11, 7); // b = 7

        int[] programa = {
            /* 0  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 2  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x20, 
            /* 4  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 7,
            /* 6  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x35, 
            // --- LOOP B2 ---
            /* 8  */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x20,
            /* 10 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x11, 
            /* 12 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x20, 
            /* 14 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x35,
            /* 16 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 17 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x35, 
            /* 19 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x15,
            /* 21 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       25,   
            /* 23 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      8,    
            /* 25 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(49, ram.ler(0x20));
    }

    @Test
    @DisplayName("Teste do Passo 2: Cálculo de 4 * a * c (4 * 1 * 10 = 40)")
    void testeCalculo4AC() {
        ram.escrever(0x10, 1);  // a = 1
        ram.escrever(0x12, 10); // c = 10

        int[] programa = {
            /* 0  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 2  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x21, 
            /* 4  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 4,
            /* 6  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x36, // Fator 4
            // --- INÍCIO DO LOOP 4AC ---
            /* 8  */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x21,
            /* 10 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x12, 
            /* 12 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x21,
            /* 14 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x36,
            /* 16 */ DecodificadorInstrucao.Instrucao.DEC.getOpcode(),
            /* 17 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x36,
            /* 19 */ DecodificadorInstrucao.Instrucao.CMP.getOpcode(),      0x15,
            /* 21 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       25,   
            /* 23 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      8,    
            /* 25 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(40, ram.ler(0x21));
    }

    @Test
    @DisplayName("Teste do Passo 3: Cálculo de Delta = b^2 - 4ac (49 - 40 = 9)")
    void testeCalculoDelta() {
        ram.escrever(0x20, 49); 
        ram.escrever(0x21, 40); 

        int[] programa = {
            /* 0 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x20, 
            /* 2 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x21, 
            /* 4 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x22, 
            /* 6 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(9, ram.ler(0x22));
    }

    @Test
    @DisplayName("Teste do Passo 4: Raiz Quadrada via Subtração de Ímpares (sqrt(9) = 3)")
    void testeRaizQuadradaPorImpares() {
        ram.escrever(0x22, 9); 

        // O erro anterior (expected 3 but was 0) ocorria porque os pulos condicionais
        // (JZ, JN, JMP) estavam caindo em endereços errados no array após as instruções
        // com 1 byte de tamanho como INC/DEC. Todos os índices foram rigorosamente mapeados.
        int[] programa = {
            /* 0  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 1,
            /* 2  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x23, // Registra próximo ímpar (1)
            /* 4  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,
            /* 6  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x25, // Zera Contador
            // --- INÍCIO DO LOOP DE RAIZ ---
            /* 8  */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x22, // Carrega Delta
            /* 10 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x23, // Subtrai Ímpar
            /* 12 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x22, 
            /* 14 */ DecodificadorInstrucao.Instrucao.JN.getOpcode(),       36,   // Se negativo estourou -> HALT
            /* 16 */ DecodificadorInstrucao.Instrucao.JZ.getOpcode(),       31,   // Se der Zero exato -> Pula pro Final
            /* 18 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x25, 
            /* 20 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),            // Instrução de 1 byte
            /* 21 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x25, // Salva contador
            /* 23 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x23, 
            /* 25 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x16, // Soma 2 no ímpar
            /* 27 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x23, 
            /* 29 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      8,    // Volta ao Loop
            // --- BLOCO FINALIZAÇÃO: DELTA ZEROU ---
            /* 31 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x25, 
            /* 33 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),            // +1 final no contador 
            /* 34 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x25, 
            /* 36 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()            // Fim
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(3, ram.ler(0x25));
    }

    @Test
    @DisplayName("Teste do Passo 5: Cálculo do Denominador 2 * a (2 * 1 = 2)")
    void testeDenominador() {
        ram.escrever(0x10, 1); 

        int[] programa = {
            /* 0 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x10, 
            /* 2 */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x10, 
            /* 4 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x26, 
            /* 6 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(2, ram.ler(0x26));
    }

    @Test
    @DisplayName("Teste do Passo 6: Cálculo de X1 = (b + sqrt(Delta)) / 2a (10 / 2 = 5)")
    void testeCalculoX1() {
        ram.escrever(0x11, 7); 
        ram.escrever(0x25, 3); 
        ram.escrever(0x26, 2); 

        int[] programa = {
            /* 0  */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11, // Carrega B
            /* 2  */ DecodificadorInstrucao.Instrucao.ADD.getOpcode(),      0x25, // + Raiz
            /* 4  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, // Salva Numerador
            /* 6  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,    
            /* 8  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x30, // Zera Quociente
            // --- LOOP DIVISÃO X1 ---
            /* 10 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x27, // Numerador
            /* 12 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x26, // - Denominador
            /* 14 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, // Salva Resto
            /* 16 */ DecodificadorInstrucao.Instrucao.JN.getOpcode(),       25,   // Pula p/ HALT se esgotou
            /* 18 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x30, 
            /* 20 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),            // Instrução 1 byte
            /* 21 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x30, 
            /* 23 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      10,   // Volta p/ Loop
            /* 25 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()           
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(5, ram.ler(0x30));
    }

    @Test
    @DisplayName("Teste do Passo 7: Cálculo de X2 = (b - sqrt(Delta)) / 2a (4 / 2 = 2)")
    void testeCalculoX2() {
        ram.escrever(0x11, 7); 
        ram.escrever(0x25, 3); 
        ram.escrever(0x26, 2); 

        int[] programa = {
            /* 0  */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x11, // Carrega B
            /* 2  */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x25, // - Raiz
            /* 4  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, // Salva Numerador
            /* 6  */ DecodificadorInstrucao.Instrucao.LOAD_IMM.getOpcode(), 0,    
            /* 8  */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x31, // Zera Quociente
            // --- LOOP DIVISÃO X2 ---
            /* 10 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x27, // Numerador
            /* 12 */ DecodificadorInstrucao.Instrucao.SUB.getOpcode(),      0x26, // - Denominador
            /* 14 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x27, // Salva Resto
            /* 16 */ DecodificadorInstrucao.Instrucao.JN.getOpcode(),       25,   // Pula p/ HALT se esgotou
            /* 18 */ DecodificadorInstrucao.Instrucao.LOAD.getOpcode(),     0x31, 
            /* 20 */ DecodificadorInstrucao.Instrucao.INC.getOpcode(),            // Instrução 1 byte
            /* 21 */ DecodificadorInstrucao.Instrucao.STORE.getOpcode(),    0x31, 
            /* 23 */ DecodificadorInstrucao.Instrucao.JMP.getOpcode(),      10,   // Volta p/ Loop
            /* 25 */ DecodificadorInstrucao.Instrucao.HALT.getOpcode()           
        };

        MemoriaRom rom = new MemoriaRom();
        rom.carregarPrograma(programa);
        new Cpu(rom, ram).executarPrograma();

        assertEquals(2, ram.ler(0x31));
    }
}