package br.com.exemplo.asic.alu;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HalfAdderTest {

    @DisplayName("Tabela verdade do Half-Adder")
    @ParameterizedTest(name = "A: {0}, B: {1} -> Soma: {2}, Carry: {3}")
    @CsvSource({
        "LOW, LOW, LOW, LOW",
        "LOW, HIGH, HIGH, LOW",
        "HIGH, LOW, HIGH, LOW",
        "HIGH, HIGH, LOW, HIGH"
    })
    void testHalfAdder(NivelLogico a, NivelLogico b, NivelLogico somaEsp, NivelLogico carryEsp) {
        HalfAdder ha = new HalfAdder();
        ha.somar(a, b);

        assertEquals(somaEsp, ha.getSoma(), "Valor da soma incorreto");
        assertEquals(carryEsp, ha.getCarry(), "Valor do carry incorreto");
    }
}