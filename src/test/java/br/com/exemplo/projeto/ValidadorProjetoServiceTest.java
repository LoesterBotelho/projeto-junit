package br.com.exemplo.projeto;

import org.junit.jupiter.api.Test;

import br.com.exemplo.projeto.Projeto;
import br.com.exemplo.projeto.Tarefa;
import br.com.exemplo.projeto.ValidadorProjetoService;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorProjetoServiceTest {

    @Test
    void deveLancarExcecaoSeHouverTarefaPendenteNoFechamento() {
        Projeto projeto = new Projeto("Auditoria");
        projeto.adicionarTarefa(new Tarefa("Revisar Logs", Tarefa.Prioridade.ALTA, LocalDate.now()));

        assertThrows(IllegalStateException.class, () -> ValidadorProjetoService.validarFechamento(projeto));
    }

    @Test
    void devePassarSeTodasAsTarefasEstiveremConcluidas() {
        Projeto projeto = new Projeto("Auditoria");
        Tarefa t = new Tarefa("Revisar Logs", Tarefa.Prioridade.ALTA, LocalDate.now());
        t.concluir();
        projeto.adicionarTarefa(t);

        assertDoesNotThrow(() -> ValidadorProjetoService.validarFechamento(projeto));
    }
}