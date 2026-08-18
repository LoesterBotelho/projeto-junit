package br.com.exemplo.asic.registradores;

import br.com.exemplo.asic.base.NivelLogico;

public class FlipFlopD {
    private NivelLogico estadoArmazenado = NivelLogico.LOW;
    private NivelLogico ultimoClock = NivelLogico.LOW;

    public void clock(NivelLogico sinalClock, NivelLogico dadoEntrada) {

        boolean bordaDeSubida = (ultimoClock == NivelLogico.LOW) && (sinalClock == NivelLogico.HIGH);
        
        if (bordaDeSubida) {
            this.estadoArmazenado = (dadoEntrada != null) ? dadoEntrada : NivelLogico.LOW;
        }
        
        this.ultimoClock = sinalClock;
    }

    public NivelLogico getSaida() {
        return estadoArmazenado;
    }
}