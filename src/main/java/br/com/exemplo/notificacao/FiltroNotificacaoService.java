package br.com.exemplo.notificacao;

import java.util.List;

public class FiltroNotificacaoService {

	public static List<Notificacao> filtrarPorPrioridade(List<Notificacao> lista, PrioridadeNotificacao prioridade) {
		if (lista == null || prioridade == null)
			return List.of();
		return lista.stream().filter(n -> n.prioridade() == prioridade).toList();
	}

	public static List<Notificacao> filtrarPorCanal(List<Notificacao> lista, CanalNotificacao canal) {
		if (lista == null || canal == null)
			return List.of();
		return lista.stream().filter(n -> n.canal() == canal).toList();
	}

	public static List<Notificacao> filtrarPorStatus(List<Notificacao> lista, StatusNotificacao status) {
		if (lista == null || status == null)
			return List.of();
		return lista.stream().filter(n -> n.status() == status).toList();
	}

	public static List<Notificacao> filtrarUrgentes(List<Notificacao> lista) {
		if (lista == null)
			return List.of();
		return lista.stream().filter(n -> n.prioridade().isUrgente()).toList();
	}
}