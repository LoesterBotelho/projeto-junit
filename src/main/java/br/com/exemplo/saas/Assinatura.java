package br.com.exemplo.saas;

import java.time.LocalDate;

public class Assinatura {
    private String cliente;
    private Plano planoAtual;
    private LocalDate dataInicioCiclo;
    private LocalDate dataFimCiclo;

    public Assinatura(String cliente, Plano planoAtual, LocalDate dataInicioCiclo, LocalDate dataFimCiclo) {
        this.cliente = cliente;
        this.planoAtual = planoAtual;
        this.dataInicioCiclo = dataInicioCiclo;
        this.dataFimCiclo = dataFimCiclo;
    }

    public Plano getPlanoAtual() {
        return planoAtual;
    }

    public void setPlanoAtual(Plano planoAtual) {
        this.planoAtual = planoAtual;
    }

    public LocalDate getDataInicioCiclo() {
        return dataInicioCiclo;
    }

    public LocalDate getDataFimCiclo() {
        return dataFimCiclo;
    }
}