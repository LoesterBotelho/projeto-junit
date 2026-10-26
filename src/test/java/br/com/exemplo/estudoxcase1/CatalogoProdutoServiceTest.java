package br.com.exemplo.estudoxcase1;

import java.math.BigDecimal;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatalogoProdutoServiceTest {

    CatalogoProdutoService service;

    Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto(
                1L, "Monitor", BigDecimal.valueOf(900));

        service = new CatalogoProdutoService(
                Map.of(produto.getId(), produto));
    }

    @Test
    void deveBuscarProdutoExistente() {

        // Arrange
        Long id = 1L;

        // Act
        Produto resultado = service.buscarPorId(id);

        // Assert
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado.getId()).isEqualTo(id);
        Assertions.assertThat(resultado.getNome()).isEqualTo("Monitor");
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {

        // Arrange
        Long id = 99L;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.buscarPorId(id))
                .isInstanceOf(ProdutoNaoEncontradoException.class)
                .hasMessage("Produto não encontrado: 99");
    }
    
}