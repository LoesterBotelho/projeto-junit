package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import br.com.exemplo.asic.transistores.Transistor;

public class And {
    private final Transistor t1 = new Transistor(Transistor.Tipo.NPN);
    private final Transistor t2 = new Transistor(Transistor.Tipo.NPN);

    public NivelLogico processar(NivelLogico entradaA, NivelLogico entradaB) {
        t1.aplicarTensaoBase(entradaA);
        t2.aplicarTensaoBase(entradaB);

        if (t1.conduz() && t2.conduz()) {
            return NivelLogico.HIGH;
        }
        return NivelLogico.LOW;
    }
}