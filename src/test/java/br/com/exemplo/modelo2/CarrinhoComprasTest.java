package br.com.exemplo.modelo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoComprasTest {

    private CarrinhoCompras carrinho;

    @BeforeEach
    void setUp() {
        carrinho = new CarrinhoCompras("CLI-999");
    }

    @Test
    @DisplayName("Deve inicializar o carrinho corretamente vazio e aberto")
    void deveInicializarCarrinho() {
        assertEquals("CLI-999", carrinho.getClienteId());
        assertTrue(carrinho.getItens().isEmpty());
        assertFalse(carrinho.isFechado());
        assertEquals(0, BigDecimal.ZERO.compareTo(carrinho.calcularTotalBruto()));
    }

    @Test
    @DisplayName("Deve adicionar novos itens e acumular quantidade se o produto já existir")
    void deveAdicionarItensComSucesso() {
        carrinho.adicionarItem("P1", "Camiseta", new BigDecimal("50.00"), 2);
        carrinho.adicionarItem("P1", "Camiseta", new BigDecimal("50.00"), 1); // Soma quantidade -> 3

        assertEquals(1, carrinho.getItens().size());
        assertEquals(3, carrinho.getItens().get("P1").quantidade());
        assertEquals(0, new BigDecimal("150.00").compareTo(carrinho.calcularTotalBruto()));
    }

    @Test
    @DisplayName("Deve remover item do carrinho com sucesso")
    void deveRemoverItem() {
        carrinho.adicionarItem("P1", "Camiseta", new BigDecimal("50.00"), 2);
        carrinho.removerItem("P1");

        assertTrue(carrinho.getItens().isEmpty());
        assertEquals(0, BigDecimal.ZERO.compareTo(carrinho.calcularTotalBruto()));
    }

    @ParameterizedTest(name = "Total bruto de {0} deve resultar em frete {1}")
    @CsvSource({
        "100.00, 20.00",
        "249.99, 20.00",
        "250.00, 0.00",
        "500.00, 0.00"
    })
    void deveCalcularFreteCorretamente(BigDecimal valorProduto, BigDecimal freteEsperado) {
        carrinho.adicionarItem("P1", "Produto Teste", valorProduto, 1);
        assertEquals(0, freteEsperado.compareTo(carrinho.calcularFreteFinal()));
    }

    @Test
    @DisplayName("Deve impedir adicionar itens se o carrinho estiver fechado")
    void deveFalharAdicionarEmCarrinhoFechado() {
        carrinho.adicionarItem("P1", "Tenis", new BigDecimal("100.00"), 1);
        carrinho.fecharCarrinho();

        assertTrue(carrinho.isFechado());
        assertThrows(IllegalStateException.class, () -> 
            carrinho.adicionarItem("P2", "Meia", new BigDecimal("15.00"), 1)
        );
    }

    @Test
    @DisplayName("Deve falhar ao tentar fechar um carrinho vazio")
    void deveFalharFecharCarrinhoVazio() {
        assertThrows(IllegalStateException.class, () -> carrinho.fecharCarrinho());
    }

    @Test
    @DisplayName("Deve lançar exceção se tentar adicionar quantidade ou preço inválidos")
    void deveFalharValoresInvalidosNoItem() {
        assertThrows(IllegalArgumentException.class, () -> 
            carrinho.adicionarItem("P1", "Erro", new BigDecimal("-10.00"), 1)
        );
        assertThrows(IllegalArgumentException.class, () -> 
            carrinho.adicionarItem("P2", "Erro", new BigDecimal("50.00"), 0)
        );
    }
}