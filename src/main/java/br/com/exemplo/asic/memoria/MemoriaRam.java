package br.com.exemplo.asic.memoria;

public class MemoriaRam {
    private final int[] dados = new int[256]; // 256 bytes de memória endereçável (8 bits de endereço)

    public void escrever(int endereco, int valor) {
        int endLimpo = endereco & 0xFF;
        dados[endLimpo] = valor & 0xFF;
    }

    public int ler(int endereco) {
        int endLimpo = endereco & 0xFF;
        return dados[endLimpo] & 0xFF;
    }

    public void limpar() {
        for (int i = 0; i < dados.length; i++) {
            dados[i] = 0;
        }
    }
}