package br.com.exemplo.modelo3;

public class ProcessadorFrete {

    private final ServicoFreteExterno servicoFrete;

    public ProcessadorFrete(ServicoFreteExterno servicoFrete) {
        this.servicoFrete = servicoFrete;
    }

    public double calcularValorFinalComFrete(double valorProduto, String estado) {
        double taxa = servicoFrete.consultarTaxa(estado);
        return valorProduto + taxa;
    }
}