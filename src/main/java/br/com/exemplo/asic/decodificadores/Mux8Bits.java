package br.com.exemplo.asic.decodificadores;

import br.com.exemplo.asic.base.NivelLogico;

public class Mux8Bits {

    public int selecionar(int entrada0, int entrada1, NivelLogico seletor) {
        boolean selecionarB = (seletor == NivelLogico.HIGH);
        int selecionado = selecionarB ? entrada1 : entrada0;
        return selecionado & 0xFF;
    }

    public int selecionar4vias(int ent0, int ent1, int ent2, int ent3, NivelLogico seletorA, NivelLogico seletorB) {
        int bitA = (seletorA == NivelLogico.HIGH) ? 1 : 0;
        int bitB = (seletorB == NivelLogico.HIGH) ? 2 : 0;
        int sel = bitB | bitA;

        return switch (sel) {
            case 0 -> ent0 & 0xFF;
            case 1 -> ent1 & 0xFF;
            case 2 -> ent2 & 0xFF;
            case 3 -> ent3 & 0xFF;
            default -> 0;
        };
    }
}