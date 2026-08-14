package br.com.exemplo.projeto;

import org.junit.jupiter.api.Test;

import br.com.exemplo.projeto.Tarefa;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

	@Test
	void deveMarcarTarefaComoConcluida() {
		Tarefa t = new Tarefa("Estudar JUnit", Tarefa.Prioridade.ALTA, LocalDate.now().plusDays(2));
		assertFalse(t.isConcluida());
		t.concluir();
		assertTrue(t.isConcluida());
	}

	@Test
	void deveIdentificarSeTarefaEstaAtrasada() {
		LocalDate hoje = LocalDate.of(2026, 8, 14);
		Tarefa t = new Tarefa("Entrega Antiga", Tarefa.Prioridade.MEDIA, LocalDate.of(2026, 8, 10));

		assertTrue(t.isAtrasada(hoje));
	}
}