package br.com.exemplo.modelo3;

public class Main {
    
    public static void main(String[] args) {

        // Instanciando dependências reais
        ValidadorPedido validador = new ValidadorPedido();
        ServicoFreteExterno servicoFrete = new ServicoFreteExternoImpl();
        ProcessadorFrete processadorFrete = new ProcessadorFrete(servicoFrete);

        String cliente = "Ana Souza";
        double valorProduto = 150.0;
        int quantidade = 2;
        String estadoDestino = "SP";

        try {
            // 1. Validando o pedido
            System.out.println("Validando pedido para o cliente: " + cliente);
            validador.validar(cliente, valorProduto, quantidade);
            System.out.println("-> Pedido validado com sucesso!");

            // 2. Calculando o total com frete
            double valorTotalComFrete = processadorFrete.calcularValorFinalComFrete(valorProduto, estadoDestino);
            
            System.out.println("-> Estado de destino: " + estadoDestino);
            System.out.println("-> Valor do Produto: R$ " + valorProduto);
            System.out.println("-> Valor Total (com frete): R$ " + valorTotalComFrete);

        } catch (Exception e) {
            System.err.println("Erro ao processar pedido: " + e.getMessage());
        }

    }
}