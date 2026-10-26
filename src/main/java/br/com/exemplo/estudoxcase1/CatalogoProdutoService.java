package br.com.exemplo.estudoxcase1;

import java.util.Map;

public class CatalogoProdutoService {

    private final Map<Long, Produto> produtos;

    public CatalogoProdutoService(Map<Long, Produto> produtos) {
        this.produtos = Map.copyOf(produtos);
    }

    public Produto buscarPorId(Long id) {
        Produto produto = produtos.get(id);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException(
                    "Produto não encontrado: " + id);
        }

        return produto;
    }
    
}