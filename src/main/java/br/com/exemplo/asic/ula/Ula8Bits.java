package br.com.exemplo.asic.ula;

import br.com.exemplo.asic.alu.SomadorSubtrator8Bits;
import br.com.exemplo.asic.registradores.RegistradorFlags;

public class Ula8Bits {
    private final SomadorSubtrator8Bits somadorSubtrator = new SomadorSubtrator8Bits();
    private final RegistradorFlags flags = new RegistradorFlags();

    private int resultado = 0;

    public enum Operacao {
        ADD, SUB, AND, OR, XOR, NOT, SHL, SHR
    }

    public void executar(Operacao op, int a, int b) {
        boolean carry = false;
        boolean overflow = false;

        switch (op) {
            case ADD:
                somadorSubtrator.calcular(a, b, false);
                resultado = somadorSubtrator.getResultado();
                carry = somadorSubtrator.isCarryOut();
                overflow = somadorSubtrator.isOverflow();
                break;

            case SUB:
                somadorSubtrator.calcular(a, b, true);
                resultado = somadorSubtrator.getResultado();
                carry = somadorSubtrator.isCarryOut();
                overflow = somadorSubtrator.isOverflow();
                break;

            case AND:
                resultado = (a & b) & 0xFF;
                break;

            case OR:
                resultado = (a | b) & 0xFF;
                break;

            case XOR:
                resultado = (a ^ b) & 0xFF;
                break;

            case NOT:
                resultado = (~a) & 0xFF;
                break;

            case SHL:
                carry = ((a & 0x80) != 0); // O bit que sai à esquerda vai para o carry
                resultado = (a << 1) & 0xFF;
                break;

            case SHR:
                carry = ((a & 0x01) != 0); // O bit que sai à direita vai para o carry
                resultado = (a >> 1) & 0xFF;
                break;
        }

        // Atualiza as flags centrais do processador
        flags.atualizar(resultado, carry, overflow);
    }

    public int getResultado() {
        return resultado;
    }

    public RegistradorFlags getFlags() {
        return flags;
    }
}