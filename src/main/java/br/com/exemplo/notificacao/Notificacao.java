package br.com.exemplo.notificacao;

import java.time.LocalDateTime;
import java.util.Objects;

public record Notificacao(String id, String destinatario, String conteudo, CanalNotificacao canal,
		PrioridadeNotificacao prioridade, StatusNotificacao status, LocalDateTime dataCriacao) {
	public Notificacao {
		Objects.requireNonNull(id, "ID não pode ser nulo");
		Objects.requireNonNull(destinatario, "Destinatário não pode ser nulo");
		Objects.requireNonNull(conteudo, "Conteúdo não pode ser nulo");
		Objects.requireNonNull(canal, "Canal não pode ser nulo");
		Objects.requireNonNull(prioridade, "Prioridade não pode ser nula");
		Objects.requireNonNull(status, "Status não pode ser nulo");
		Objects.requireNonNull(dataCriacao, "Data de criação não pode ser nula");

		if (destinatario.isBlank()) {
			throw new IllegalArgumentException("Destinatário não pode estar em branco");
		}
	}

	public Notificacao comStatus(StatusNotificacao novoStatus) {
		return new Notificacao(this.id, this.destinatario, this.conteudo, this.canal, this.prioridade, novoStatus,
				this.dataCriacao);
	}
}