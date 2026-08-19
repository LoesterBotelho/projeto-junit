package br.com.exemplo.modelo1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreteServiceTest {

    private FreteService freteService;

    @BeforeEach
    void setUp() {
        freteService = new FreteService();
    }

    @ParameterizedTest(name = "Região {0} com pedido de {1} deve custar frete {2}")
    @CsvSource({
        "SUDESTE, 100.00, 25.00",
        "SUL, 150.00, 30.00",
        "NORDESTE, 200.00, 45.00",
        "CENTRO-OESTE, 50.00, 50.00"
    })
    void deveCalcularFretePorRegiao(String regiao, BigDecimal valorPedido, BigDecimal freteEsperado) {
        BigDecimal freteCalculado = freteService.calcularFrete(regiao, valorPedido);
        assertEquals(0, freteEsperado.compareTo(freteCalculado));
    }

    @Test
    @DisplayName("Deve conceder frete grátis para pedidos iguais ou superiores a R$ 300,00")
    void deveDarFreteGratisParaPedidosGrandes() {
        BigDecimal frete = freteService.calcularFrete("NORDESTE", new BigDecimal("350.00"));
        assertEquals(0, BigDecimal.ZERO.compareTo(frete));
    }
}