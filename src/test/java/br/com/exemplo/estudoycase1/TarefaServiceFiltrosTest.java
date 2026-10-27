package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceFiltrosTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    private Tarefa tarefaPendente;
    private Tarefa tarefaEmAndamento;
    private Tarefa tarefaConcluida;

    @BeforeEach
    void configurar() {
        tarefaPendente = new Tarefa(
                1L,
                "Estudar Java",
                "Praticar Streams",
                PrioridadeTarefa.ALTA,
                LocalDate.now().plusDays(3));

        tarefaEmAndamento = new Tarefa(
                2L,
                "Estudar JUnit",
                "Criar testes",
                PrioridadeTarefa.MEDIA,
                LocalDate.now().minusDays(2));

        tarefaEmAndamento.iniciar();

        tarefaConcluida = new Tarefa(
                3L,
                "Estudar Mockito",
                "Praticar mocks",
                PrioridadeTarefa.ALTA,
                LocalDate.now().minusDays(5));

        tarefaConcluida.iniciar();
        tarefaConcluida.concluir();
    }

    @Test
    void deveFiltrarTarefasPorStatus() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(
                        tarefaPendente,
                        tarefaEmAndamento,
                        tarefaConcluida));

        // Act
        List<Tarefa> resultado =
                service.filtrarPorStatus(StatusTarefa.PENDENTE);

        // Assert
        Assertions.assertThat(resultado)
                .containsExactly(tarefaPendente);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveFiltrarTarefasPorPrioridade() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(
                        tarefaPendente,
                        tarefaEmAndamento,
                        tarefaConcluida));

        // Act
        List<Tarefa> resultado =
                service.filtrarPorPrioridade(PrioridadeTarefa.ALTA);

        // Assert
        Assertions.assertThat(resultado)
                .containsExactlyInAnyOrder(
                        tarefaPendente,
                        tarefaConcluida);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveListarSomenteTarefasAtrasadasNaoFinalizadas() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(
                        tarefaPendente,
                        tarefaEmAndamento,
                        tarefaConcluida));

        // Act
        List<Tarefa> resultado = service.listarAtrasadas();

        // Assert
        Assertions.assertThat(resultado)
                .containsExactly(tarefaEmAndamento);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveOrdenarTarefasPorPrazo() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(
                        tarefaPendente,
                        tarefaEmAndamento,
                        tarefaConcluida));

        // Act
        List<Tarefa> resultado = service.listarOrdenadasPorPrazo();

        // Assert
        Assertions.assertThat(resultado)
                .containsExactly(
                        tarefaConcluida,
                        tarefaEmAndamento,
                        tarefaPendente);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveLancarExcecaoQuandoStatusForNulo() {

        // Arrange
        StatusTarefa status = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.filtrarPorStatus(status))
                .isInstanceOf(TarefaInvalidaException.class)
                .hasMessage("O status é obrigatório.");

        // Verify
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    void deveLancarExcecaoQuandoPrioridadeForNula() {

        // Arrange
        PrioridadeTarefa prioridade = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.filtrarPorPrioridade(prioridade))
                .isInstanceOf(TarefaInvalidaException.class)
                .hasMessage("A prioridade é obrigatória.");

        // Verify
        Mockito.verifyNoInteractions(repository);
    }
    
}