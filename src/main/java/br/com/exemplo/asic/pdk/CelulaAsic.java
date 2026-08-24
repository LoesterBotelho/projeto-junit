package br.com.exemplo.asic.pdk;

import java.util.List;

public interface CelulaAsic {
    String getNome();
    List<GeometriaCamada> gerarLayoutFisico();
    String gerarNetlistCompleta();
}