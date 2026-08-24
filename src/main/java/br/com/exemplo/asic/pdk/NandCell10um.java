package br.com.exemplo.asic.pdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Célula Padrão CMOS de Porta Lógica NAND (NAND2) 
 * projetada sob as regras do PDK proprietário de 10 micrômetros.
 */
public class NandCell10um implements CelulaAsic {

    private final String nomeCelulas = "nand2_10um";

    // Transistores PMOS (Paralelo entre VDD e a Saída 'out')
    private final Pmos p1 = new Pmos("P1", 40, 10, 100, 100, "out", "in1", "vdd", "vdd");
    private final Pmos p2 = new Pmos("P2", 40, 10, 400, 100, "out", "in2", "vdd", "vdd");

    // Transistores NMOS (Série entre a Saída 'out' e o GND, passando por 'net1')
    private final Nmos n1 = new Nmos("N1", 20, 10, 250, 600, "out", "in1", "net1", "gnd");
    private final Nmos n2 = new Nmos("N2", 20, 10, 250, 900, "net1", "in2", "gnd", "gnd");

    public String getNome() {
        return nomeCelulas;
    }

    public List<GeometriaCamada> gerarLayoutFisico() {
        List<GeometriaCamada> layout = new ArrayList<>();
        layout.addAll(p1.desenhar());
        layout.addAll(p2.desenhar());
        layout.addAll(n1.desenhar());
        layout.addAll(n2.desenhar());
        return layout;
    }

    public String gerarnetlistCompleta() {
        return """
            Simulador NGSPICE - Teste de Porta NAND 10um
            
            .model nmos nmos level=1 kp=20u vto=1.0
            .model pmos pmos level=1 kp=10u vto=-1.0

            .subckt %s in1 in2 out vdd gnd
            %s
            %s
            %s
            %s
            .ends

            * Alimentação principal (VDD e Terra Global 0)
            Vdd vdd 0 DC 5.0

            * Estímulos de entrada
            Va in1 0 PULSE(0 5 0 1ns 1ns 10ns 20ns)
            Vb in2 0 DC 5.0

            * Instanciação da Célula NAND (passando '0' como pino gnd)
            X1 in1 in2 saida vdd 0 %s

            .tran 0.1ns 50ns

            .end
            """.formatted(
                nomeCelulas, 
                p1.gerarSpice(), 
                p2.gerarSpice(), 
                n1.gerarSpice(), 
                n2.gerarSpice(),
                nomeCelulas
            );
    }

    public String gerarNetlistCompleta() {
        return gerarnetlistCompleta();
    }
}