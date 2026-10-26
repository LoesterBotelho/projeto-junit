package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String DIV = "-------------------------------------------------------------------------------------------";

        IO.println(DIV);
        IO.println("EXEMPLO 1 - CRIANDO CLIENTE");
        IO.println(DIV);

        Cliente cliente = new Cliente(
                1L,
                "José",
                "jose@email.com"
        );

        IO.println("Cliente: " + cliente.getNome());
        IO.println("E-mail: " + cliente.getEmail());

        IO.println(DIV);
        IO.println("EXEMPLO 2 - CRIANDO PRODUTO");
        IO.println(DIV);

        Produto produto = new Produto(
                1L,
                "Teclado",
                BigDecimal.valueOf(100)
        );

        IO.println("Produto: " + produto.getNome());
        IO.println("Preço: R$ " + produto.getPreco());

        IO.println(DIV);
        IO.println("EXEMPLO 3 - CRIANDO PEDIDO");
        IO.println(DIV);

        ItemPedido item = new ItemPedido(produto, 2);

        Pedido pedido = new Pedido(
                cliente,
                List.of(item),
                item.getSubtotal()
        );

        IO.println("Pedido: " + pedido.getId());
        IO.println("Quantidade: " + item.getQuantidade());
        IO.println("Subtotal: R$ " + item.getSubtotal());
        IO.println("Total: R$ " + pedido.getValorTotal());

        IO.println(DIV);
        IO.println("EXEMPLO 4 - ENVIANDO NOTIFICAÇÃO");
        IO.println(DIV);

        NotificacaoService notificacaoService = new NotificacaoService();

        notificacaoService.enviarConfirmacao(pedido);
    }
    
}