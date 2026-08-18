package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaAndTest {

    private And portaAnd;

    @BeforeEach
    void setUp() {
        portaAnd = new And();
    }

    @DisplayName("Teste da Tabela-Verdade da Porta AND")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Saída: {2}")
    @CsvSource({
        "LOW, LOW, LOW",
        "LOW, HIGH, LOW",
        "HIGH, LOW, LOW",
        "HIGH, HIGH, HIGH"
    })
    void testAnd(NivelLogico a, NivelLogico b, NivelLogico saidaEsperada) {
        assertEquals(saidaEsperada, portaAnd.processar(a, b));
    }
}