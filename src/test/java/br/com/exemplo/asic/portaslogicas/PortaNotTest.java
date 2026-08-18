package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortaNotTest {

    private Not portaNot;

    @BeforeEach
    void setUp() {
        portaNot = new Not();
    }

    @DisplayName("Teste da Tabela-Verdade da Porta NOT baseada em Transistores")
    @ParameterizedTest(name = "Entrada: {0} -> Saída Esperada: {1}")
    @CsvSource({
        "LOW, HIGH",
        "HIGH, LOW"
    })
    void testNot(NivelLogico entrada, NivelLogico saidaEsperada) {
        assertEquals(saidaEsperada, portaNot.processar(entrada));
    }
}