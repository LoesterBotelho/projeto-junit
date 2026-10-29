package br.com.exemplo.receitas;

import java.time.LocalDate;
import java.util.Objects;

public class Paciente {

    private final Long id;
    private final String nome;
    private final String cpf;
    private final LocalDate dataNascimento;

    public Paciente(
            Long id,
            String nome,
            String cpf,
            LocalDate dataNascimento) {

        this.id = Objects.requireNonNull(id);
        this.nome = Objects.requireNonNull(nome);
        this.cpf = Objects.requireNonNull(cpf);
        this.dataNascimento = Objects.requireNonNull(dataNascimento);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
}