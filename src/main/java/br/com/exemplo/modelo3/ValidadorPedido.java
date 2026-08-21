package br.com.exemplo.modelo3;

public class ValidadorPedido {

    public void validar(String cliente, double valorTotal, int quantidadeItens) {
        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Cliente não pode ser nulo ou vazio");
        }
        if (valorTotal <= 0) {
            throw new IllegalArgumentException("O valor total do pedido deve ser maior que zero");
        }
        if (quantidadeItens <= 0) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos 1 item");
        }
    }
}