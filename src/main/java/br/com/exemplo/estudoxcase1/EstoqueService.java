
package br.com.exemplo.estudoxcase1;

import java.util.HashMap;
import java.util.Map;

public class EstoqueService {

    private final Map<Long, Integer> quantidades = new HashMap<>();

    public void adicionarEstoque(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            throw new IllegalArgumentException(
                    "Produto e quantidade válida são obrigatórios.");
        }

        quantidades.merge(produto.getId(), quantidade, Integer::sum);
    }

    public int consultarQuantidade(Produto produto) {
        return quantidades.getOrDefault(produto.getId(), 0);
    }

    public void validarDisponibilidade(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            throw new IllegalArgumentException(
                    "Produto e quantidade válida são obrigatórios.");
        }

        if (consultarQuantidade(produto) < quantidade) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente.");
        }
    }

	public void reservar(Produto produto, int quantidade) {
	
	    validarDisponibilidade(produto, quantidade);
	
	    Long produtoId = produto.getId();
	    int quantidadeAtual = quantidades.getOrDefault(produtoId, 0);
	
	    quantidades.put(produtoId, quantidadeAtual - quantidade);
	}

}