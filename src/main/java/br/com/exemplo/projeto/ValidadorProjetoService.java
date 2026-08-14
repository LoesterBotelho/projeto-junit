package br.com.exemplo.projeto;

public class ValidadorProjetoService {
	public static void validarFechamento(Projeto projeto) {
		boolean temPendente = projeto.getTarefas().stream().anyMatch(t -> !t.isConcluida());
		if (temPendente) {
			throw new IllegalStateException("Não é possível fechar o projeto com tarefas pendentes.");
		}
	}
}