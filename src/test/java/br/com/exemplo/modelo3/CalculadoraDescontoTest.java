package br.com.exemplo.modelo3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDescontoTest {

    private final CalculadoraDesconto calculadora = new CalculadoraDesconto();

    @Test
    @DisplayName("Não deve aplicar desconto para quantidade menor que 10")
    void naoDeveAplicarDescontoAbaixoDeDez() {
        double valorFinal = calculadora.calcular(100.0, 5);
        assertEquals(100.0, valorFinal);
    }

    @Test
    @DisplayName("Deve aplicar 10% de desconto exatamente a partir de 10 unidades (Caso Limite)")
    void deveAplicarDescontoExatamenteEmDezUnidades() {
        double valorFinal = calculadora.calcular(100.0, 10);
        assertEquals(90.0, valorFinal, 0.001); // 90 reais
    }

    @Test
    @DisplayName("Deve aplicar 10% de desconto para quantidade acima de 10")
    void deveAplicarDescontoAcimaDeDezUnidades() {
        double valorFinal = calculadora.calcular(100.0, 15);
        assertEquals(90.0, valorFinal, 0.001);
    }

    @Test
    @DisplayName("Deve lançar exceção se o preço for negativo")
    void deveLancarExcecaoParaPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcular(-10.0, 5);
        });
    }

    // Bônus: Teste Parametrizado (vários cenários rodando em um único método)
    @ParameterizedTest(name = "Preço {0} com qtd {1} deve resultar em {2}")
    @CsvSource({
        "50.0, 2, 50.0",
        "50.0, 9, 50.0",
        "50.0, 10, 45.0",
        "200.0, 50, 180.0"
    })
    void testarVariosCenariosDeDesconto(double preco, int qtd, double esperado) {
        assertEquals(esperado, calculadora.calcular(preco, qtd), 0.001);
    }
}