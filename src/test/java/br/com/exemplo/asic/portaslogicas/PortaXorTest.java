package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaXorTest {
    private Xor portaXor;

    @BeforeEach
    void setUp() { portaXor = new Xor(); }

    @DisplayName("Tabela-Verdade Porta XOR")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Saída: {2}")
    @CsvSource({
        "LOW, LOW, LOW",
        "LOW, HIGH, HIGH",
        "HIGH, LOW, HIGH",
        "HIGH, HIGH, LOW"
    })
    void testXor(NivelLogico a, NivelLogico b, NivelLogico esperado) {
        assertEquals(esperado, portaXor.processar(a, b));
    }
}