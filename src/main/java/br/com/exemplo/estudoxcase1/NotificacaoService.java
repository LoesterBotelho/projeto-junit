
package br.com.exemplo.estudoxcase1;

public class NotificacaoService {

    public void enviarConfirmacao(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException(
                    "O pedido é obrigatório.");
        }

        IO.println("Pedido confirmado!");
        IO.println("Pedido: " + pedido.getId());
        IO.println("Cliente: " + pedido.getCliente().getNome());
        IO.println("E-mail: " + pedido.getCliente().getEmail());
        IO.println("Valor total: R$ " + pedido.getValorTotal());
    }
}