package br.com.exemplo.asic.registradores;

public class RegistradorFlags {
    private boolean zero = false;      // Z: Ativado se o resultado for 0
    private boolean negativo = false;  // N: Ativado se o bit mais significativo (MSB) for 1
    private boolean carry = false;     // C: Ativado em caso de estouro ou vai-um aritmético
    private boolean overflow = false;  // V: Ativado em caso de estouro de sinal (Complemento de 2)

    /**
     * Atualiza o estado das flags com base no resultado da ULA e nas condições de hardware.
     */
    public void atualizar(int resultado8Bits, boolean ocorreuCarry, boolean ocorreuOverflow) {
        this.zero = (resultado8Bits == 0);
        this.negativo = (resultado8Bits & 0x80) != 0; // Verifica se o bit 7 (MSB) está em 1
        this.carry = ocorreuCarry;
        this.overflow = ocorreuOverflow;
    }

    public boolean isZero() {
        return zero;
    }

    public boolean isNegativo() {
        return negativo;
    }

    public boolean isCarry() {
        return carry;
    }

    public boolean isOverflow() {
        return overflow;
    }

    @Override
    public String toString() {
        return String.format("[Flags -> Z:%d N:%d C:%d V:%d]", 
            zero ? 1 : 0, 
            negativo ? 1 : 0, 
            carry ? 1 : 0, 
            overflow ? 1 : 0
        );
    }
}