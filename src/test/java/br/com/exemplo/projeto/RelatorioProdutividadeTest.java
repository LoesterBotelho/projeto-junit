package br.com.exemplo.projeto;

import org.junit.jupiter.api.Test;

import br.com.exemplo.projeto.RelatorioProdutividade;
import br.com.exemplo.projeto.Tarefa;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RelatorioProdutividadeTest {

	@Test
	void deveContarTarefasPendentesCorretamente() {
		Tarefa t1 = new Tarefa("T1", Tarefa.Prioridade.BAIXA, LocalDate.now());
		Tarefa t2 = new Tarefa("T2", Tarefa.Prioridade.BAIXA, LocalDate.now());
		t1.concluir();

		long pendentes = RelatorioProdutividade.contarTarefasPendentes(List.of(t1, t2));
		assertEquals(1, pendentes);
	}

	@Test
	void deveRetornarZeroParaListaNula() {
		assertEquals(0, RelatorioProdutividade.contarTarefasPendentes(null));
	}
}