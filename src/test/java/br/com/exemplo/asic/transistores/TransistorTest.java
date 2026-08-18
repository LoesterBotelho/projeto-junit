package br.com.exemplo.asic.transistores;

import br.com.exemplo.asic.base.NivelLogico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransistorTest {

    @DisplayName("Teste de condução: Transistor NPN (Conduz quando a base é HIGH)")
    @ParameterizedTest(name = "Base: {0} -> Conduz: {1}")
    @CsvSource({
        "LOW, false",
        "HIGH, true"
    })
    void testTransistorNpn(NivelLogico base, boolean esperado) {
        Transistor npn = new Transistor(Transistor.Tipo.NPN);
        npn.aplicarTensaoBase(base);
        assertEquals(esperado, npn.conduz(), "NPN deveria conduzir apenas em HIGH");
    }

    @DisplayName("Teste de condução: Transistor PNP (Conduz quando a base é LOW)")
    @ParameterizedTest(name = "Base: {0} -> Conduz: {1}")
    @CsvSource({
        "LOW, true",
        "HIGH, false"
    })
    void testTransistorPnp(NivelLogico base, boolean esperado) {
        Transistor pnp = new Transistor(Transistor.Tipo.PNP);
        pnp.aplicarTensaoBase(base);
        assertEquals(esperado, pnp.conduz(), "PNP deveria conduzir apenas em LOW");
    }
}