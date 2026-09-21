package br.com.exemplo.saas;

import java.time.LocalDate;

public class ClienteSaaS {
    private String nome;
    private LocalDate dataCadastro;
    private boolean vip;

    public ClienteSaaS(String nome, LocalDate dataCadastro, boolean vip) {
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.vip = vip;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public boolean isVip() {
        return vip;
    }
}