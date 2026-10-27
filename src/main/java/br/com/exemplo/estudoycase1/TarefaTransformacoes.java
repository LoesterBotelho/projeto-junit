package br.com.exemplo.estudoycase1;

import java.util.function.Function;

public final class TarefaTransformacoes {

    private TarefaTransformacoes() {
    }

    public static Function<Tarefa, String> paraTitulo() {
        return Tarefa::getTitulo;
    }

    public static Function<Tarefa, String> paraResumo() {
        return tarefa -> tarefa.getId()
                + " - "
                + tarefa.getTitulo()
                + " - "
                + tarefa.getStatus();
    }

    public static Function<Tarefa, Long> paraId() {
        return Tarefa::getId;
    }
    
}