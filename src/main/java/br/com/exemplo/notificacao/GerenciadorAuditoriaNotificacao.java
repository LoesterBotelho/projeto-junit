package br.com.exemplo.notificacao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorAuditoriaNotificacao {
	private final List<Notificacao> historico = new ArrayList<>();

	public void registrar(Notificacao notificacao) {
		if (notificacao != null) {
			historico.add(notificacao);
		}
	}

	public List<Notificacao> getHistorico() {
		return List.copyOf(historico);
	}

	public List<String> getLogs() {
		return historico.stream()
				.map(n -> "ID: " + n.id() + " | Destinatário: " + n.destinatario() + " | Status: " + n.status())
				.collect(Collectors.toList());
	}

	public long contarPorStatus(StatusNotificacao status) {
		return historico.stream().filter(n -> n.status() == status).count();
	}

	public void limparHistorico() {
		historico.clear();
	}
}