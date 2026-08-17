package br.com.exemplo.notificacao;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("Notificações");

		AgendadorNotificacao agendador = new AgendadorNotificacao();
		GerenciadorAuditoriaNotificacao auditoria = new GerenciadorAuditoriaNotificacao();
		NotificacaoService service = new NotificacaoService(agendador, auditoria);

		Notificacao n1 = new Notificacao("1", "Usuario1", "Bem-vindo!", CanalNotificacao.EMAIL, PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, LocalDateTime.now());

		Notificacao n2 = new Notificacao("2", "Usuario2", "Seu pedido foi enviado.", CanalNotificacao.SMS, PrioridadeNotificacao.MEDIA, StatusNotificacao.PENDENTE, LocalDateTime.now());

		Notificacao n3 = new Notificacao("3", "Usuario3", "Alerta de segurança.", CanalNotificacao.EMAIL, PrioridadeNotificacao.URGENTE, StatusNotificacao.PENDENTE, LocalDateTime.now());

		service.processarNotificacao(n1);
		service.processarNotificacao(n2);
		service.processarNotificacao(n3);

		System.out.println("\nNotificações : Alta Prioridade");
		List<Notificacao> filtradas = FiltroNotificacaoService.filtrarPorPrioridade(agendador.getNotificacoes(), PrioridadeNotificacao.ALTA);

		filtradas .forEach(n -> System.out.println("Destinatário: " + n.destinatario() + " | Conteúdo: " + n.conteudo()));

		System.out.println("\n Logs");
		auditoria.getLogs().forEach(System.out::println);

		System.out.println("\nProcesso Concluído.");
	}
}