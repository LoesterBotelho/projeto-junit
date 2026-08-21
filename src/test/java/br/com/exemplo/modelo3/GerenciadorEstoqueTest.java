package br.com.exemplo.modelo3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorEstoqueTest {

    @Test
    @DisplayName("Deve iniciar o estoque com valor válido")
    void deveCriarEstoqueValido() {
        GerenciadorEstoque estoque = new GerenciadorEstoque(10);
        assertEquals(10, estoque.getEstoqueAtual());
    }

    @Test
    @DisplayName("Não deve permitir estoque inicial negativo")
    void naoDevePermitirEstoqueInicialNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GerenciadorEstoque(-5);
        });
    }

    @Test
    @DisplayName("Deve adicionar itens ao estoque corretamente")
    void deveAdicionarItens() {
        GerenciadorEstoque estoque = new GerenciadorEstoque(10);
        estoque.adicionar(5);
        assertEquals(15, estoque.getEstoqueAtual());
    }

    @Test
    @DisplayName("Deve remover itens do estoque se houver saldo suficiente")
    void deveRemoverItensComSaldo() { // Nota: nome do método pode ser 'deveRemoverItensComSaldo'
        GerenciadorEstoque estoque = new GerenciadorEstoque(20);
        estoque.remover(5);
        assertEquals(15, estoque.getEstoqueAtual());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar remover mais do que o estoque possui")
    void naoDevePermitirRemocaoAcimaDoEstoque() {
        GerenciadorEstoque estoque = new GerenciadorEstoque(10);
        assertThrows(IllegalStateException.class, () -> {
            estoque.remover(15);
        });
        assertEquals(10, estoque.getEstoqueAtual()); // Garante que o estoque não mudou após o erro
    }
}