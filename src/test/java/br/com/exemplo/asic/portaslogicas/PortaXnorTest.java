package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaXnorTest {
    private Xnor portaXnor;

    @BeforeEach
    void setUp() { portaXnor = new Xnor(); }

    @DisplayName("Tabela-Verdade Porta XNOR")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Saída: {2}")
    @CsvSource({
        "LOW, LOW, HIGH",
        "LOW, HIGH, LOW",
        "HIGH, LOW, LOW",
        "HIGH, HIGH, HIGH"
    })
    void testXnor(NivelLogico a, NivelLogico b, NivelLogico esperado) {
        assertEquals(esperado, portaXnor.processar(a, b));
    }
}