package br.com.exemplo.estudoycase1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TarefaRepositoryEmMemoria implements TarefaRepository {

    private final Map<Long, Tarefa> tarefas = new HashMap<>();

    @Override
    public Tarefa salvar(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException(
                    "A tarefa é obrigatória.");
        }

        tarefas.put(tarefa.getId(), tarefa);

        return tarefa;
    }

    @Override
    public Optional<Tarefa> buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        return Optional.ofNullable(tarefas.get(id));
    }

    @Override
    public List<Tarefa> listarTodas() {
        return new ArrayList<>(tarefas.values());
    }

    @Override
    public boolean excluirPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        return tarefas.remove(id) != null;
    }

    @Override
    public boolean existePorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "O ID é obrigatório.");
        }

        return tarefas.containsKey(id);
    }
    
}