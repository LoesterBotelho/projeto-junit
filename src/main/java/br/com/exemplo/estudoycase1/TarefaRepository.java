package br.com.exemplo.estudoycase1;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository {

    Tarefa salvar(Tarefa tarefa);

    Optional<Tarefa> buscarPorId(Long id);

    List<Tarefa> listarTodas();

    boolean excluirPorId(Long id);

    boolean existePorId(Long id);
}