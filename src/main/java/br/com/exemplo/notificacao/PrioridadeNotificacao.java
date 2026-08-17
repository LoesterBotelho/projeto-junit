package br.com.exemplo.notificacao;

public enum PrioridadeNotificacao {
	BAIXA(1), MEDIA(2), ALTA(3), CRITICA(4), URGENTE(5);

	private final int nivel;

	PrioridadeNotificacao(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}

	public boolean isUrgente() {
		return this == ALTA || this == CRITICA;
	}
}