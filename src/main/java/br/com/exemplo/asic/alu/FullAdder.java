package br.com.exemplo.asic.alu;

import br.com.exemplo.asic.base.NivelLogico;
import br.com.exemplo.asic.portaslogicas.Or;

public class FullAdder {
    private final HalfAdder ha1 = new HalfAdder();
    private final HalfAdder ha2 = new HalfAdder();
    private final Or portaOr = new Or();

    private NivelLogico soma = NivelLogico.LOW;
    private NivelLogico carryOut = NivelLogico.LOW;

    public void somar(NivelLogico a, NivelLogico b, NivelLogico carryIn) {

        ha1.somar(a, b);


        ha2.somar(ha1.getSoma(), carryIn);
        this.soma = ha2.getSoma();


        this.carryOut = portaOr.processar(ha1.getCarry(), ha2.getCarry());
    }

    public NivelLogico getSoma() {
        return soma;
    }

    public NivelLogico getCarryOut() {
        return carryOut;
    }
}