package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;

public class Xor {
    private final And and1 = new And();
    private final And and2 = new And();
    private final Or or = new Or();
    private final Not notA = new Not();
    private final Not notB = new Not();

    public NivelLogico processar(NivelLogico a, NivelLogico b) {
        NivelLogico invA = notA.processar(a);
        NivelLogico invB = notB.processar(b);

        NivelLogico term1 = and1.processar(a, invB);
        NivelLogico term2 = and2.processar(invA, b);

        return or.processar(term1, term2);
    }
}