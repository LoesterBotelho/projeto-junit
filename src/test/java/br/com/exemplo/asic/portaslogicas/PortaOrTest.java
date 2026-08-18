package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaOrTest {

    private Or portaOr;

    @BeforeEach
    void setUp() {
        portaOr = new Or();
    }

    @DisplayName("Teste da Tabela-Verdade da Porta OR")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Saída: {2}")
    @CsvSource({
        "LOW, LOW, LOW",
        "LOW, HIGH, HIGH",
        "HIGH, LOW, HIGH",
        "HIGH, HIGH, HIGH"
    })
    void testOr(NivelLogico a, NivelLogico b, NivelLogico saidaEsperada) {
        assertEquals(saidaEsperada, portaOr.processar(a, b));
    }
}