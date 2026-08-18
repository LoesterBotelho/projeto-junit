package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;

public class Nand {
    private final And portaAnd = new And();
    private final Not portaNot = new Not();

    public NivelLogico processar(NivelLogico a, NivelLogico b) {
        NivelLogico resultadoAnd = portaAnd.processar(a, b);
        return portaNot.processar(resultadoAnd);
    }
}