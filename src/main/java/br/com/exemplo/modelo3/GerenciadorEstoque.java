package br.com.exemplo.modelo3;

public class GerenciadorEstoque {

    private int estoqueAtual;

    public GerenciadorEstoque(int estoqueInicial) {
        if (estoqueInicial < 0) {
            throw new IllegalArgumentException("Estoque inicial não pode ser negativo");
        }
        this.estoqueAtual = estoqueInicial;
    }

    public void adicionar(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        this.estoqueAtual += quantidade;
    }

    public void remover(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        if (quantidade > this.estoqueAtual) {
            throw new IllegalStateException("Estoque insuficiente para baixa");
        }
        this.estoqueAtual -= quantidade;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }
}