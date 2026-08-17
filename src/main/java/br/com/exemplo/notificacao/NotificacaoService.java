package br.com.exemplo.notificacao;

public class NotificacaoService {
	private final AgendadorNotificacao agendador;
	private final GerenciadorAuditoriaNotificacao auditoria;

	public NotificacaoService(AgendadorNotificacao agendador, GerenciadorAuditoriaNotificacao auditoria) {
		this.agendador = agendador;
		this.auditoria = auditoria;
	}

	public Notificacao processarNotificacao(Notificacao notificacao) {
		if (notificacao == null) {
			throw new IllegalArgumentException("Notificação não pode ser nula.");
		}

		if (!notificacao.canal().suportaTamanho(notificacao.conteudo())) {
			Notificacao falhou = notificacao.comStatus(StatusNotificacao.FALHA);
			auditoria.registrar(falhou);
			return falhou;
		}

		agendador.agendar(notificacao);

		Notificacao enviada = notificacao.comStatus(StatusNotificacao.ENVIADO);
		auditoria.registrar(enviada);

		System.out.println("Notificação processada e agendada para: " + enviada.destinatario());
		return enviada;
	}
}