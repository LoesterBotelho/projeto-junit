package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaNandTest {
    private Nand portaNand;

    @BeforeEach
    void setUp() { portaNand = new Nand(); }

    @DisplayName("Tabela-Verdade Porta NAND")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Saída: {2}")
    @CsvSource({
        "LOW, LOW, HIGH",
        "LOW, HIGH, HIGH",
        "HIGH, LOW, HIGH",
        "HIGH, HIGH, LOW"
    })
    void testNand(NivelLogico a, NivelLogico b, NivelLogico esperado) {
        assertEquals(esperado, portaNand.processar(a, b));
    }
}