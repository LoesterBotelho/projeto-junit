package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;
import br.com.exemplo.asic.transistores.Transistor;

public class Not {
    private final Transistor pnp = new Transistor(Transistor.Tipo.PNP);
    private final Transistor npn = new Transistor(Transistor.Tipo.NPN);

    public NivelLogico processar(NivelLogico entrada) {
        pnp.aplicarTensaoBase(entrada);
        npn.aplicarTensaoBase(entrada);

        if (npn.conduz()) {
            return NivelLogico.LOW;
        }
        if (pnp.conduz()) {
            return NivelLogico.HIGH;
        }
        return NivelLogico.LOW;
    }
}