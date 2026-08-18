package br.com.exemplo.asic.alu;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullAdderTest {

    @DisplayName("Tabela verdade do Full-Adder (A + B + CarryIn)")
    @ParameterizedTest(name = "A: {0}, B: {1}, Cin: {2} -> Soma: {3}, Cout: {4}")
    @CsvSource({
        "LOW, LOW, LOW, LOW, LOW",
        "LOW, LOW, HIGH, HIGH, LOW",
        "LOW, HIGH, LOW, HIGH, LOW",
        "LOW, HIGH, HIGH, LOW, HIGH",
        "HIGH, LOW, LOW, HIGH, LOW",
        "HIGH, LOW, HIGH, LOW, HIGH",
        "HIGH, HIGH, LOW, LOW, HIGH",
        "HIGH, HIGH, HIGH, HIGH, HIGH"
    })
    void testFullAdder(NivelLogico a, NivelLogico b, NivelLogico cin, NivelLogico somaEsp, NivelLogico coutEsp) {
        FullAdder fa = new FullAdder();
        fa.somar(a, b, cin);

        assertEquals(somaEsp, fa.getSoma(), "Soma incorreta");
        assertEquals(coutEsp, fa.getCarryOut(), "Carry-out incorreto");
    }
}