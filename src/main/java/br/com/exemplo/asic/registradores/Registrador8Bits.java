package br.com.exemplo.asic.registradores;

import br.com.exemplo.asic.base.NivelLogico;

public class Registrador8Bits {
    private final FlipFlopD[] bits = new FlipFlopD[8];

    public Registrador8Bits() {
        for (int i = 0; i < 8; i++) {
            bits[i] = new FlipFlopD();
        }
    }

    public void clock(NivelLogico sinalClock, int valor) {
        for (int i = 0; i < 8; i++) {
            boolean bitLigado = ((valor >> i) & 1) == 1;
            NivelLogico nivelBit = bitLigado ? NivelLogico.HIGH : NivelLogico.LOW;
            
            // Simula a borda de subida do clock (transição LOW -> HIGH) para forçar o FlipFlopD a capturar o valor
            if (sinalClock == NivelLogico.HIGH) {
                bits[i].clock(NivelLogico.LOW, nivelBit);
            }
            bits[i].clock(sinalClock, nivelBit);
        }
    }

    public int getValor() {
        int valor = 0;
        for (int i = 0; i < 8; i++) {
            if (bits[i].getSaida() == NivelLogico.HIGH) {
                valor |= (1 << i);
            }
        }
        return valor;
    }
}