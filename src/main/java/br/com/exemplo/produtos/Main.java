
package br.com.exemplo.produtos;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        var produto = new Produto(
                1L,
                "Teclado",
                new BigDecimal("150.00"),
                10);

        var pedido = new Pedido(1L);

        pedido.adicionarItem(new ItemPedido(produto, 2));

        var calculadora = new CalculadoraPedido();

        System.out.println("Produto: " + produto.getNome());
        System.out.println("Quantidade: " + pedido.getItens().getFirst().getQuantidade());
        System.out.println("Total: R$ " + calculadora.calcularTotal(pedido));
    }
}