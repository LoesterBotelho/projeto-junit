package br.com.exemplo.projeto;

import java.util.List;

public class RelatorioProdutividade {
	public static long contarTarefasPendentes(List<Tarefa> tarefas) {
		if (tarefas == null)
			return 0;
		return tarefas.stream().filter(t -> !t.isConcluida()).count();
	}
}