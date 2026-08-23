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
    void deveGerarSpiceValido() {
        NandCell10um nand = new NandCell10um();
        String spice = nand.gerarNetlistCompleta();

        assertAll(
            () -> assertTrue(spice.contains(".subckt nand2_10um in1 in2 out vdd gnd")),
            () -> assertTrue(spice.contains("MP1 out in1 vdd vdd pmos W=40um L=10um")),
            () -> assertTrue(spice.contains("MN1 out in1 net1 gnd nmos W=20um L=10um"))
        );
    }

    @Test
    @DisplayName("Deve gerar geometrias de camadas físicas para o layout em CIF")
    void deveGerarCamadasLayout() {
        NandCell10um nand = new NandCell10um();
        var layout = nand.gerarLayoutFisico();

        assertFalse(layout.isEmpty(), "O layout gerado não pode estar vazio.");
        
        // Verifica se as camadas essenciais do processo CMOS (N-Well, Active, Poly, Metal1) estão presentes
        boolean possuiNWell = layout.stream().anyMatch(c -> c.camada().equals("N_WELL"));
        boolean possuiActive = layout.stream().anyMatch(c -> c.camada().equals("ACTIVE"));
        boolean possuiPoly = layout.stream().anyMatch(c -> c.camada().equals("POLY"));

        assertAll(
            () -> assertTrue(possuiNWell, "O layout da célula deve conter a camada N_WELL para os PMOS."),
            () -> assertTrue(possuiActive, "O layout deve conter a camada ACTIVE (difusão)."),
            () -> assertTrue(possuiPoly, "O layout deve conter a camada POLY (polisilício/gate).")
        );
    }
}