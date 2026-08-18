package br.com.exemplo.asic.transistores;

import br.com.exemplo.asic.base.NivelLogico;

public class Transistor {
    public enum Tipo { NPN, PNP }

    private final Tipo tipo;
    private NivelLogico estadoBase = NivelLogico.LOW;

    public Transistor(Tipo tipo) {
        this.tipo = tipo;
    }

    public void aplicarTensaoBase(NivelLogico nivel) {
        this.estadoBase = nivel;
    }

    public boolean conduz() {
        if (tipo == Tipo.NPN) {
            return estadoBase == NivelLogico.HIGH;
        } else {
            return estadoBase == NivelLogico.LOW;
        }
    }
}