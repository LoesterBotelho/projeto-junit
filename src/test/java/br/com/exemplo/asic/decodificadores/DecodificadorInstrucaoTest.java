package br.com.exemplo.asic.decodificadores;

import br.com.exemplo.asic.ula.Ula8Bits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DecodificadorInstrucaoTest {

    @DisplayName("Deve traduzir corretamente todos os opcodes da ISA")
    @ParameterizedTest(name = "Opcode 0x{0} -> {1}")
    @CsvSource({
        "01, LOAD",
        "02, STORE",
        "03, LOAD_IMM",
        "04, ADD",
        "05, SUB",
        "06, INC",
        "07, DEC",
        "08, AND",
        "09, OR",
        "0A, NOT",
        "0B, SHL",
        "0C, SHR",
        "0D, JMP",
        "0E, JZ",
        "0F, JNZ",
        "10, CMP",
        "11, HALT"
    })
    void testTraducaoTodosOpcodes(String hexOpcode, DecodificadorInstrucao.Instrucao instrucaoEsperada) {
        int opcode = Integer.parseInt(hexOpcode, 16);
        assertEquals(instrucaoEsperada, DecodificadorInstrucao.Instrucao.porOpcode(opcode));
    }

    @Test
    @DisplayName("Deve lançar exceção para opcode inválido")
    void testOpcodeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            DecodificadorInstrucao.Instrucao.porOpcode(0xFF);
        });
    }

    @Test
    @DisplayName("Deve mapear corretamente as instruções para as operações da ULA")
    void testMapeamentoUla() {
        DecodificadorInstrucao decodificador = new DecodificadorInstrucao();

        assertEquals(Ula8Bits.Operacao.ADD, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.ADD));
        assertEquals(Ula8Bits.Operacao.ADD, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.INC));
        assertEquals(Ula8Bits.Operacao.SUB, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.SUB));
        assertEquals(Ula8Bits.Operacao.SUB, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.DEC));
        assertEquals(Ula8Bits.Operacao.SUB, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.CMP));
        assertEquals(Ula8Bits.Operacao.AND, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.AND));
        assertEquals(Ula8Bits.Operacao.OR,  decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.OR));
        assertEquals(Ula8Bits.Operacao.NOT, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.NOT));
        assertEquals(Ula8Bits.Operacao.SHL, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.SHL));
        assertEquals(Ula8Bits.Operacao.SHR, decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.SHR));
        
        assertNull(decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.LOAD));
        assertNull(decodificador.obterOperacaoUla(DecodificadorInstrucao.Instrucao.HALT));
    }
}