package br.com.exemplo.saas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DescontoAssinaturaServiceTest {

    private DescontoAssinaturaService service;
    private LocalDate dataReferencia;

    @BeforeEach
    void setUp() {
        service = new DescontoAssinaturaService();
        dataReferencia = LocalDate.of(2026, 9, 21);
    }

    @ParameterizedTest
    @DisplayName("Deve aplicar corretamente diferentes cupons de desconto sem cliente especial")
    @CsvSource({
        "100.00, PROMO10, 90.00",
        "200.00, PROMO20, 160.00",
        "100.00, BLACKFRIDAY, 50.00"
    })
    void deveAplicarCuponsCorretamente(String valorStr, String cupom, String esperadoStr) {
        BigDecimal valorOriginal = new BigDecimal(valorStr);
        BigDecimal esperado = new BigDecimal(esperadoStr);

        BigDecimal resultado = service.calcularValorComDesconto(valorOriginal, cupom, null, dataReferencia);

        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Deve aplicar desconto de fidelidade para cliente com mais de 1 ano de cadastro")
    void deveAplicarDescontoFidelidade() {
        LocalDate dataCadastroAntiga = LocalDate.of(2024, 1, 10); // Mais de 2 anos de casa
        ClienteSaaS cliente = new ClienteSaaS("Maria", dataCadastroAntiga, false);

        BigDecimal valorOriginal = new BigDecimal("100.00");
        // 100 com cupom PROMO10 vai para 90.00, com 5% de fidelidade vai para 85.50
        BigDecimal resultado = service.calcularValorComDesconto(valorOriginal, "PROMO10", cliente, dataReferencia);

        assertEquals(new BigDecimal("85.50"), resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao informar um cupom inválido")
    void deveLancarExcecaoParaCupomInvalido() {
        BigDecimal valorOriginal = new BigDecimal("100.00");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.calcularValorComDesconto(valorOriginal, "CUPOM_FALSO", null, dataReferencia);
        });

        assertEquals("Cupom inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se o valor original for inválido (zero ou negativo)")
    void deveLancarExcecaoParaValorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularValorComDesconto(BigDecimal.ZERO, "PROMO10", null, dataReferencia);
        });
    }
}