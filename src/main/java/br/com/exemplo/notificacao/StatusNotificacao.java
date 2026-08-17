package br.com.exemplo.notificacao;

public enum StatusNotificacao {
	PENDENTE, EM_PROCESSAMENTO, ENVIADO, FALHA, CANCELADO;

	public boolean isFinalizado() {
		return this == ENVIADO || this == FALHA || this == CANCELADO;
	}
}