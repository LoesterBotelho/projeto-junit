package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;

public class Xnor {
    private final Xor portaXor = new Xor();
    private final Not portaNot = new Not();

    public NivelLogico processar(NivelLogico a, NivelLogico b) {
        NivelLogico resultadoXor = portaXor.processar(a, b);
        return portaNot.processar(resultadoXor);
    }
}