package br.com.exemplo.notificacao;

import java.util.ArrayList;
import java.util.List;

public class AgendadorNotificacao {
	private final List<Notificacao> fila = new ArrayList<>();

	public void agendar(Notificacao notificacao) {
		if (notificacao.status() != StatusNotificacao.PENDENTE) {
			throw new IllegalArgumentException("Apenas notificações no estado PENDENTE podem ser agendadas.");
		}
		fila.add(notificacao);
	}

	public List<Notificacao> obterFila() {
		return List.copyOf(fila);
	}

	public List<Notificacao> getNotificacoes() {
		return obterFila();
	}

	public boolean cancelar(String id) {
		return fila.removeIf(n -> n.id().equals(id));
	}

	public int quantidadePendente() {
		return fila.size();
	}

	public void limparFila() {
		fila.clear();
	}
}