package br.com.exemplo.asic.portaslogicas;

import br.com.exemplo.asic.base.NivelLogico;

public class Main {
    public static void main(String[] args) {
        Not not = new Not();
        And and = new And();
        Or or = new Or();

        System.out.println("SIMULAÇÃO DE PORTAS LÓGICAS (CONSOLES)");
        System.out.println("NOT(HIGH) = " + not.processar(NivelLogico.HIGH));
        System.out.println("AND(HIGH, LOW) = " + and.processar(NivelLogico.HIGH, NivelLogico.LOW));
        System.out.println("OR(HIGH, LOW) = " + or.processar(NivelLogico.HIGH, NivelLogico.LOW));
    }
}