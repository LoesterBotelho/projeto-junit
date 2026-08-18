package br.com.exemplo.asic.base;

public enum NivelLogico {
    LOW(0),
    HIGH(1);

    private final int valor;

    NivelLogico(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public NivelLogico inverter() {
        return this == HIGH ? LOW : HIGH;
    }
}