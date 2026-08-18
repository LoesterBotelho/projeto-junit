package br.com.exemplo.asic.memoria;

public class MemoriaRom {
    private final int[] memoria = new int[256]; // Capacidade padrão de 256 bytes para a ROM

    public void carregarPrograma(int[] programa) {
        for (int i = 0; i < programa.length && i < memoria.length; i++) {
            memoria[i] = programa[i] & 0xFF;
        }
    }

    public int lerInstrucao(int endereco) {
        if (endereco < 0 || endereco >= memoria.length) {
            throw new IndexOutOfBoundsException("Endereço de ROM inválido: 0x" + Integer.toHexString(endereco));
        }
        return memoria[endereco];
    }
}