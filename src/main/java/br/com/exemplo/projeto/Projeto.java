package br.com.exemplo.projeto;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
	private String nome;
	private List<Tarefa> tarefas = new ArrayList<>();

	public Projeto(String nome) {
		this.nome = nome;
	}

	public void adicionarTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
	}

	public double calcularProgresso() {
		if (tarefas.isEmpty())
			return 0.0;
		long concluidas = tarefas.stream().filter(Tarefa::isConcluida).count();
		return (double) concluidas / tarefas.size() * 100.0;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}