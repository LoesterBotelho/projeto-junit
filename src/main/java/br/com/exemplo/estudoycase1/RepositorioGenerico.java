package br.com.exemplo.estudoycase1;

import java.util.List;
import java.util.Optional;

public interface RepositorioGenerico<T, ID> {

    T salvar(T entidade);

    Optional<T> buscarPorId(ID id);

    List<T> listarTodos();

    boolean excluirPorId(ID id);
}