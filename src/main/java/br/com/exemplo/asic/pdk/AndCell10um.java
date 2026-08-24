package br.com.exemplo.asic.pdk;

import java.util.ArrayList;
import java.util.List;

public class AndCell10um  implements CelulaAsic {

    private final String nomeCelulas = "and2_10um";

    private final Pmos p1 = new Pmos("P1", 40, 10, 100, 100, "net_nand", "in1", "vdd", "vdd");
    private final Pmos p2 = new Pmos("P2", 40, 10, 400, 100, "net_nand", "in2", "vdd", "vdd");
    private final Nmos n1 = new Nmos("N1", 20, 10, 250, 600, "net_nand", "in1", "net1", "gnd");
    private final Nmos n2 = new Nmos("N2", 20, 10, 250, 900, "net1", "in2", "gnd", "gnd");
    
    private final Pmos pInv = new Pmos("PINV", 40, 10, 700, 100, "out", "net_nand", "vdd", "vdd");
    private final Nmos nInv = new Nmos("NINV", 20, 10, 700, 600, "out", "net_nand", "gnd", "gnd");

    public String getNome() {
        return nomeCelulas;
    }

    public List<GeometriaCamada> gerarLayoutFisico() {
        List<GeometriaCamada> layout = new ArrayList<>();
        layout.addAll(p1.desenhar());
        layout.addAll(p2.desenhar());
        layout.addAll(n1.desenhar());
        layout.addAll(n2.desenhar());
        layout.addAll(pInv.desenhar());
        layout.addAll(nInv.desenhar());
        return layout;
    }

    public String gerarNetlistCompleta() {
        return """
            Simulador NGSPICE - Teste de Porta AND 10um
            
            .model nmos nmos level=1 kp=20u vto=1.0
            .model pmos pmos level=1 kp=10u vto=-1.0

            .subckt %s in1 in2 out vdd gnd
            %s
            %s
            %s
            %s
            %s
            %s
            .ends

            Vdd vdd 0 DC 5.0
            Va in1 0 PULSE(0 5 0 1ns 1ns 10ns 20ns)
            Vb in2 0 DC 5.0
            X1 in1 in2 saida vdd 0 %s

            .tran 0.1ns 50ns
            .end
            """.formatted(nomeCelulas, p1.gerarSpice(), p2.gerarSpice(), n1.gerarSpice(), n2.gerarSpice(), pInv.gerarSpice(), nInv.gerarSpice(), nomeCelulas);
    }
}