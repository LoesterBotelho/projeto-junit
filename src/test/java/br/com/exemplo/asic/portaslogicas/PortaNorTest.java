package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaNorTest {
    private Nor portaNor;

    @BeforeEach
    void setUp() { portaNor = new Nor(); }

    @DisplayName("Tabela-Verdade Porta NOR")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Saída: {2}")
    @CsvSource({
        "LOW, LOW, HIGH",
        "LOW, HIGH, LOW",
        "HIGH, LOW, LOW",
        "HIGH, HIGH, LOW"
    })
    void testNor(NivelLogico a, NivelLogico b, NivelLogico esperado) {
        assertEquals(esperado, portaNor.processar(a, b));
    }
}