package br.com.exemplo.asic.pdk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do PDK Proprietário de 10um")
class Pdk10umValidacaoTest {

    @Test
    @DisplayName("Deve garantir comprimento mínimo de canal de 10um (L=10)")
    void deveValidarComprimentoMinimo() {
        Nmos nmos = new Nmos("1", 20, 10, 0, 0, "drain", "gate", "source", "bulk");
        assertEquals(10, nmos.lUf(), "O comprimento do canal deve seguir a regra estrita de 10um do PDK!");
    }

    @Test
    @DisplayName("Deve gerar netlist SPICE compatível para a célula NAND")
    void deveGerarSpiceValidoNand() {
        NandCell10um nand = new NandCell10um();
        String spice = nand.gerarNetlistCompleta();

        assertAll(
            () -> assertTrue(spice.contains(".subckt nand2_10um in1 in2 out vdd gnd")),
            () -> assertTrue(spice.contains("MP1 out in1 vdd vdd pmos W=40um L=10um")),
            () -> assertTrue(spice.contains("MN1 out in1 net1 gnd nmos W=20um L=10um"))
        );
    }

    @Test
    @DisplayName("Deve gerar netlist SPICE compatível para a célula NOT (Inversor)")
    void deveGerarSpiceValidoNot() {
        NotCell10um notCell = new NotCell10um();
        String spice = notCell.gerarNetlistCompleta();

        assertAll(
            () -> assertTrue(spice.contains(".subckt not_10um in out vdd gnd")),
            () -> assertTrue(spice.contains("MP1 out in vdd vdd pmos W=40um L=10um")),
            () -> assertTrue(spice.contains("MN1 out in gnd gnd nmos W=20um L=10um"))
        );
    }

    @Test
    @DisplayName("Deve gerar netlist SPICE compatível para a célula NOR")
    void deveGerarSpiceValidoNor() {
        NorCell10um nor = new NorCell10um();
        String spice = nor.gerarNetlistCompleta();

        assertAll(
            () -> assertTrue(spice.contains(".subckt nor2_10um in1 in2 out vdd gnd")),
            () -> assertTrue(spice.contains("MP1 net1 in1 vdd vdd pmos W=40um L=10um")),
            () -> assertTrue(spice.contains("MN1 out in1 gnd gnd nmos W=20um L=10um"))
        );
    }

    @Test
    @DisplayName("Deve gerar netlist SPICE compatível para a célula AND")
    void deveGerarSpiceValidoAnd() {
        AndCell10um and = new AndCell10um();
        String spice = and.gerarNetlistCompleta();

        assertAll(
            () -> assertTrue(spice.contains(".subckt and2_10um in1 in2 out vdd gnd")),
            () -> assertTrue(spice.contains("MP1 net_nand in1 vdd vdd pmos W=40um L=10um")),
            () -> assertTrue(spice.contains("MPINV out net_nand vdd vdd pmos W=40um L=10um"))
        );
    }

    @Test
    @DisplayName("Deve gerar geometrias de camadas físicas para todas as células do PDK")
    void deveGerarCamadasLayoutGeral() {
        var celulas = java.util.List.of(
            new NandCell10um(),
            new NotCell10um(),
            new NorCell10um(),
            new AndCell10um()
        );

        for (var celula : celulas) {
            var layout = celula.gerarLayoutFisico();

            assertFalse(layout.isEmpty(), "O layout gerado para " + celula.getNome() + " não pode estar vazio.");
            
            boolean possuiNWell = layout.stream().anyMatch(c -> c.camada().equals("N_WELL"));
            boolean possuiActive = layout.stream().anyMatch(c -> c.camada().equals("ACTIVE"));
            boolean possuiPoly = layout.stream().anyMatch(c -> c.camada().equals("POLY"));

            assertAll(
                () -> assertTrue(possuiNWell, "Layout de " + celula.getNome() + " deve conter N_WELL."),
                () -> assertTrue(possuiActive, "Layout de " + celula.getNome() + " deve conter ACTIVE."),
                () -> assertTrue(possuiPoly, "Layout de " + celula.getNome() + " deve conter POLY.")
            );
        }
    }
}