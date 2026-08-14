package br.com.exemplo.projeto;

import org.junit.jupiter.api.Test;

import br.com.exemplo.projeto.Projeto;
import br.com.exemplo.projeto.Tarefa;
import br.com.exemplo.projeto.Tarefa.Prioridade;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

	@Test
	void deveCalcularProgressoDoProjetoCorretamente() {
		Projeto projeto = new Projeto("Sistema Contábil");
		Tarefa t1 = new Tarefa("Modelagem", Tarefa.Prioridade.ALTA, LocalDate.now());
		Tarefa t2 = new Tarefa("Testes", Tarefa.Prioridade.ALTA, LocalDate.now());

		t1.concluir();
		projeto.adicionarTarefa(t1);
		projeto.adicionarTarefa(t2);

		assertEquals(50.0, projeto.calcularProgresso());
	}
}