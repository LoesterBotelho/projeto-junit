package br.com.exemplo.asic.pdk;

import java.util.ArrayList;
import java.util.List;

public class NotCell10um implements CelulaAsic {

    private final String nomeCelulas = "not_10um";

    private final Pmos p1 = new Pmos("P1", 40, 10, 100, 100, "out", "in", "vdd", "vdd");
    private final Nmos n1 = new Nmos("N1", 20, 10, 250, 600, "out", "in", "gnd", "gnd");

    public String getNome() {
        return nomeCelulas;
    }

    public List<GeometriaCamada> gerarLayoutFisico() {
        List<GeometriaCamada> layout = new ArrayList<>();
        layout.addAll(p1.desenhar());
        layout.addAll(n1.desenhar());
        return layout;
    }

    public String gerarNetlistCompleta() {
        return """
            Simulador NGSPICE - Teste de Porta NOT 10um
            
            .model nmos nmos level=1 kp=20u vto=1.0
            .model pmos pmos level=1 kp=10u vto=-1.0

            .subckt %s in out vdd gnd
            %s
            %s
            .ends

            Vdd vdd 0 DC 5.0
            Va in 0 PULSE(0 5 0 1ns 1ns 10ns 20ns)
            X1 in saida vdd 0 %s

            .tran 0.1ns 50ns
            .end
            """.formatted(nomeCelulas, p1.gerarSpice(), n1.gerarSpice(), nomeCelulas);
    }
}