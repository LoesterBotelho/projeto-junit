package br.com.exemplo.projeto;

import java.time.LocalDate;

public class Tarefa {
	public enum Prioridade {
		BAIXA, MEDIA, ALTA
	}

	private String titulo;
	private Prioridade prioridade;
	private LocalDate dataVencimento;
	private boolean concluida;

	public Tarefa(String titulo, Prioridade prioridade, LocalDate dataVencimento) {
		this.titulo = titulo;
		this.prioridade = prioridade;
		this.dataVencimento = dataVencimento;
		this.concluida = false;
	}

	public void concluir() {
		this.concluida = true;
	}

	public boolean isAtrasada(LocalDate dataAtual) {
		return !concluida && dataVencimento.isBefore(dataAtual);
	}

	public boolean isConcluida() {
		return concluida;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

}
