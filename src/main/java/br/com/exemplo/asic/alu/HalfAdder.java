package br.com.exemplo.asic.alu;

import br.com.exemplo.asic.base.*;
import br.com.exemplo.asic.portaslogicas.*;

public class HalfAdder {
    private final Xor portaXor = new Xor();
    private final And portaAnd = new And();

    private NivelLogico soma = NivelLogico.LOW;
    private NivelLogico carry = NivelLogico.LOW;

    public void somar(NivelLogico a, NivelLogico b) {

        this.soma = portaXor.processar(a, b);

        this.carry = portaAnd.processar(a, b);
    }

    public NivelLogico getSoma() {
        return soma;
    }

    public NivelLogico getCarry() {
        return carry;
    }
}