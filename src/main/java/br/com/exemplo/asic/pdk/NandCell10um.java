package br.com.exemplo.asic.pdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Célula Padrão CMOS de Porta Lógica NAND (NAND2) 
 * projetada sob as regras do PDK proprietário de 10 micrômetros.
 */
public class NandCell10um {

    private final String nomeCelulas = "nand2_10um";

    // Transistores PMOS (Paralelo entre VDD e a Saída 'out')
    // Assinatura: id, wUf, lUf, x, y, nodeDrain, nodeGate, nodeSource, nodeBulk
    private final Pmos p1 = new Pmos("P1", 40, 10, 100, 100, "out", "in1", "vdd", "vdd");
    private final Pmos p2 = new Pmos("P2", 40, 10, 400, 100, "out", "in2", "vdd", "vdd");

    // Transistores NMOS (Série entre a Saída 'out' e o GND, passando por 'net1')
    private final Nmos n1 = new Nmos("N1", 20, 10, 250, 600, "out", "in1", "net1", "gnd");
    private final Nmos n2 = new Nmos("N2", 20, 10, 250, 900, "net1", "in2", "gnd", "gnd");

    /**
     * Retorna o nome identificador da célula padrão.
     */
    public String getNome() {
        return nomeCelulas;
    }

    /**
     * Gera a lista completa de geometrias de todas as camadas físicas 
     * (N_WELL, ACTIVE, POLY, METAL1) respeitando as regras do PDK 10um.
     */
    public List<GeometriaCamada> gerarLayoutFisico() {
        List<GeometriaCamada> layout = new ArrayList<>();
        
        layout.addAll(p1.desenhar());
        layout.addAll(p2.desenhar());
        layout.addAll(n1.desenhar());
        layout.addAll(n2.desenhar());
        
        return layout;
    }

    /**
     * Gera a Netlist SPICE estrutural completa para simulação no NGSPICE.
     */
    public String gerarNetlistCompleta() {
        return """
            * Netlist SPICE gerada via Java 25 para Porta NAND (PDK 10um)
            .subckt %s in1 in2 out vdd gnd
            %s
            %s
            %s
            %s
            .ends
            """.formatted(
                nomeCelulas, 
                p1.gerarSpice(), 
                p2.gerarSpice(), 
                n1.gerarSpice(), 
                n2.gerarSpice()
            );
    }
}