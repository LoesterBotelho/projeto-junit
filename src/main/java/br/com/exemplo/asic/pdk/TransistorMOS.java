package br.com.exemplo.asic.pdk;

import java.util.List;

public sealed interface TransistorMOS permits Nmos, Pmos {
    String id();
    int wUf(); // Largura do canal em micrômetros (ex: 20 para 20um)
    int lUf(); // Comprimento do canal em micrômetros (ex: 10 para 10um)
    
    List<GeometriaCamada> desenhar();
    String gerarSpice();
}