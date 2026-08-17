package br.com.exemplo.notificacao;

public class ProvedorEmailService {

	public boolean validarEmail(String email) {
		return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}

	public String formatarMensagemHTML(String titulo, String corpo) {
		if (titulo == null || corpo == null) {
			throw new IllegalArgumentException("Título e corpo são obrigatórios.");
		}
		return """
				<html>
				    <body>
				        <h2>%s</h2>
				        <p>%s</p>
				    </body>
				</html>
				""".formatted(titulo, corpo).strip();
	}
}