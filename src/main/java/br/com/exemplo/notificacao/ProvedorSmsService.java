package br.com.exemplo.notificacao;

public class ProvedorSmsService {

	public boolean validarTelefone(String telefone) {
		return telefone != null && telefone.matches("^\\+[1-9]\\d{1,14}$");
	}

	public String truncarParaSms(String mensagem) {
		if (mensagem == null)
			return "";
		int limite = CanalNotificacao.SMS.getLimiteCaracteres();
		return mensagem.length() <= limite ? mensagem : mensagem.substring(0, limite);
	}
}