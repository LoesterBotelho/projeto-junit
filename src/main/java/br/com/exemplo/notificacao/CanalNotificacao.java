package br.com.exemplo.notificacao;

public enum CanalNotificacao {
	EMAIL(10000), SMS(160), PUSH(256), WHATSAPP(4096);

	private final int limiteCaracteres;

	CanalNotificacao(int limiteCaracteres) {
		this.limiteCaracteres = limiteCaracteres;
	}

	public int getLimiteCaracteres() {
		return limiteCaracteres;
	}

	public boolean suportaTamanho(String texto) {
		return texto != null && texto.length() <= limiteCaracteres;
	}
}