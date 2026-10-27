package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.function.Predicate;

public final class TarefaPredicados {

    private TarefaPredicados() {
    }

    public static Predicate<Tarefa> porStatus(StatusTarefa status) {
        return tarefa -> tarefa.getStatus() == status;
    }

    public static Predicate<Tarefa> porPrioridade(
            PrioridadeTarefa prioridade) {
        return tarefa -> tarefa.getPrioridade() == prioridade;
    }

    public static Predicate<Tarefa> atrasada() {
        LocalDate hoje = LocalDate.now();

        return tarefa ->
                tarefa.getPrazo() != null
                        && tarefa.getPrazo().isBefore(hoje)
                        && tarefa.getStatus() != StatusTarefa.CONCLUIDA
                        && tarefa.getStatus() != StatusTarefa.CANCELADA;
    }

    public static Predicate<Tarefa> tituloContem(String texto) {
        return tarefa ->
                tarefa.getTitulo()
                        .toLowerCase()
                        .contains(texto.toLowerCase());
    }
    
}