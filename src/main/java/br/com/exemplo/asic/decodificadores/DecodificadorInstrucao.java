package br.com.exemplo.asic.decodificadores;

import br.com.exemplo.asic.ula.Ula8Bits;

public class DecodificadorInstrucao {

    public enum Instrucao {
        LOAD(0x01),
        STORE(0x02),
        LOAD_IMM(0x03),
        ADD(0x04),
        SUB(0x05),
        INC(0x06),
        DEC(0x07),
        AND(0x08),
        OR(0x09),
        NOT(0x0A),
        SHL(0x0B),
        SHR(0x0C),
        JMP(0x0D),
        JZ(0x0E),
        JNZ(0x0F),
        JN(0x12),    // <-- ADICIONADO: Salta se negativo
        CMP(0x10),
        HALT(0x11);

        private final int opcode;

        Instrucao(int opcode) {
            this.opcode = opcode;
        }

        public int getOpcode() {
            return opcode;
        }

        public static Instrucao porOpcode(int opcode) {
            for (Instrucao inst : values()) {
                if (inst.opcode == opcode) {
                    return inst;
                }
            }
            throw new IllegalArgumentException(String.format("Opcode desconhecido ou inválido: 0x%02X", opcode));
        }
    }

    /**
     * Mapeia a instrução da CPU para a operação correspondente na ULA (quando aplicável).
     */
    public Ula8Bits.Operacao obterOperacaoUla(Instrucao instrucao) {
        return switch (instrucao) {
            case ADD, INC -> Ula8Bits.Operacao.ADD;
            case SUB, DEC, CMP -> Ula8Bits.Operacao.SUB;
            case AND -> Ula8Bits.Operacao.AND;
            case OR -> Ula8Bits.Operacao.OR;
            case NOT -> Ula8Bits.Operacao.NOT;
            case SHL -> Ula8Bits.Operacao.SHL;
            case SHR -> Ula8Bits.Operacao.SHR;
            default -> null;
        };
    }
}