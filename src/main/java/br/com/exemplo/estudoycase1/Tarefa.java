package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.Objects;

public class Tarefa {

    private final Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private PrioridadeTarefa prioridade;
    private final LocalDate dataCriacao;
    private LocalDate prazo;

    public Tarefa(
            Long id,
            String titulo,
            String descricao,
            PrioridadeTarefa prioridade,
            LocalDate prazo) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "O ID deve ser positivo.");
        }

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título é obrigatório.");
        }

        if (prioridade == null) {
            throw new IllegalArgumentException(
                    "A prioridade é obrigatória.");
        }

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = StatusTarefa.PENDENTE;
        this.prioridade = prioridade;
        this.dataCriacao = LocalDate.now();
        this.prazo = prazo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public PrioridadeTarefa getPrioridade() {
        return prioridade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void atualizarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título é obrigatório.");
        }

        this.titulo = titulo;
    }

    public void atualizarDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void atualizarPrioridade(PrioridadeTarefa prioridade) {
        this.prioridade = Objects.requireNonNull(
                prioridade, "A prioridade é obrigatória.");
    }

    public void atualizarPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public void iniciar() {
        if (status != StatusTarefa.PENDENTE) {
            throw new IllegalStateException(
                    "Somente tarefas pendentes podem ser iniciadas.");
        }

        status = StatusTarefa.EM_ANDAMENTO;
    }

    public void concluir() {
        if (status != StatusTarefa.EM_ANDAMENTO) {
            throw new IllegalStateException(
                    "Somente tarefas em andamento podem ser concluídas.");
        }

        status = StatusTarefa.CONCLUIDA;
    }

    public void cancelar() {
        if (status == StatusTarefa.CONCLUIDA
                || status == StatusTarefa.CANCELADA) {
            throw new IllegalStateException(
                    "A tarefa não pode ser cancelada.");
        }

        status = StatusTarefa.CANCELADA;
    }
}