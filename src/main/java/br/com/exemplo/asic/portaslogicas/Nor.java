package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;

public class Nor {
    private final Or portaOr = new Or();
    private final Not portaNot = new Not();

    public NivelLogico processar(NivelLogico a, NivelLogico b) {
        NivelLogico resultadoOr = portaOr.processar(a, b);
        return portaNot.processar(resultadoOr);
    }
}