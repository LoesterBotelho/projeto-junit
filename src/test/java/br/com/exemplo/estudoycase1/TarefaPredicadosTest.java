
package br.com.exemplo.estudoycase1;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TarefaPredicadosTest {

    @Test
    void deveIdentificarTarefaPendente() {

        // Arrange
        Tarefa tarefa = new Tarefa(
                1L,
                "Estudar Java",
                "Generics e lambdas",
                PrioridadeTarefa.ALTA,
                LocalDate.now().plusDays(2));

        // Act
        boolean resultado = TarefaPredicados
                .porStatus(StatusTarefa.PENDENTE)
                .test(tarefa);

        // Assert
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    void deveIdentificarTarefaComPrioridadeAlta() {

        // Arrange
        Tarefa tarefa = new Tarefa(
                2L,
                "Estudar Mockito",
                "Criar testes",
                PrioridadeTarefa.ALTA,
                LocalDate.now().plusDays(3));

        // Act
        boolean resultado = TarefaPredicados
                .porPrioridade(PrioridadeTarefa.ALTA)
                .test(tarefa);

        // Assert
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    void deveEncontrarTituloSemDiferenciarMaiusculas() {

        // Arrange
        Tarefa tarefa = new Tarefa(
                3L,
                "Estudar Java 25",
                "Praticar programação",
                PrioridadeTarefa.MEDIA,
                LocalDate.now().plusDays(4));

        // Act
        boolean resultado = TarefaPredicados
                .tituloContem("JAVA")
                .test(tarefa);

        // Assert
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    void naoDeveConsiderarTarefaConcluidaComoAtrasada() {

        // Arrange
        Tarefa tarefa = new Tarefa(
                4L,
                "Entregar exercício",
                "Finalizar atividade",
                PrioridadeTarefa.CRITICA,
                LocalDate.now().minusDays(2));

        tarefa.iniciar();
        tarefa.concluir();

        // Act
        boolean resultado = TarefaPredicados
                .atrasada()
                .test(tarefa);

        // Assert
        Assertions.assertThat(resultado).isFalse();
    }
}
