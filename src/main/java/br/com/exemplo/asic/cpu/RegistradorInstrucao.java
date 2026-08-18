package br.com.exemplo.asic.cpu;

public class RegistradorInstrucao {
    private int opcodeAtual = 0;

    /**
     * Carrega uma nova instrução (opcode) para ser decodificada e executada.
     */
    public void carregar(int opcode) {
        this.opcodeAtual = opcode & 0xFF;
    }

    public int getOpcode() {
        return opcodeAtual;
    }
}