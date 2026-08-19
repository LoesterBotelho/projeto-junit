package br.com.exemplo.modelo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceTest {

    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService();
    }

    @Test
    @DisplayName("Deve calcular corretamente o valor total de um pedido com múltiplos itens")
    void deveCalcularTotalPedido() {
        var p1 = new Produto("P01", "Notebook", new BigDecimal("3500.00"));
        var p2 = new Produto("P02", "Mouse", new BigDecimal("150.00"));

        var itens = List.of(
                new ItemPedido(p1, 1),
                new ItemPedido(p2, 2)
        );

        BigDecimal total = pedidoService.calcularTotalPedido(itens);

        assertEquals(0, new BigDecimal("3800.00").compareTo(total));
    }

    @Test
    @DisplayName("Deve retornar zero para lista de itens vazia ou nula")
    void deveRetornarZeroParaPedidoVazio() {
        assertEquals(BigDecimal.ZERO, pedidoService.calcularTotalPedido(List.of()));
        assertEquals(BigDecimal.ZERO, pedidoService.calcularTotalPedido(null));
    }

    @ParameterizedTest(name = "Forma de pagamento {0} deve processar com sucesso")
    @CsvSource({
        "PIX, Pagamento de R$ 100.00 via PIX aprovado com desconto.",
        "CREDITO, Pagamento de R$ 100.00 no Cartão de Crédito processado.",
        "BOLETO, Boleto gerado no valor de R$ 100.00."
    })
    void deveProcessarFormasDePagamentoValidas(String forma, String mensagemEsperada) {
        String resultado = pedidoService.processarPagamento(new BigDecimal("100.00"), forma);
        assertEquals(mensagemEsperada, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção para forma de pagamento desconhecida")
    void deveLancarExcecaoParaPagamentoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.processarPagamento(new BigDecimal("50.00"), "BITCOIN");
        });
    }
}