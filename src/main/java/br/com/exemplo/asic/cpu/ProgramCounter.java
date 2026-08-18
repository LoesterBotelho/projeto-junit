package br.com.exemplo.asic.cpu;

public class ProgramCounter {
    private int pc = 0;

    /**
     * Incrementa o PC em 1 para apontar para a próxima instrução.
     */
    public void incrementar() {
        pc = (pc + 1) & 0xFF; // Garante o limite de 8 bits (endereço de 0 a 255)
    }

    /**
     * Realiza um salto para um endereço específico (JUMP).
     * @param novoEndereco Endereço de destino
     */
    public void saltar(int novoEndereco) {
        pc = novoEndereco & 0xFF;
    }

    /**
     * Reseta o PC para o início da memória (endereço 0).
     */
    public void resetar() {
        pc = 0;
    }

    public int getValor() {
        return pc;
    }
}