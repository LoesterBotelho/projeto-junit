package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RelatorioTarefasServiceTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private RelatorioTarefasService service;

    private Tarefa pendente;
    private Tarefa emAndamento;
    private Tarefa concluida;

    @BeforeEach
    void configurar() {
        pendente = new Tarefa(
                1L,
                "Estudar Java",
                "Praticar coleções",
                PrioridadeTarefa.ALTA,
                LocalDate.now().plusDays(2));

        emAndamento = new Tarefa(
                2L,
                "Estudar JUnit",
                "Criar testes",
                PrioridadeTarefa.MEDIA,
                LocalDate.now().plusDays(3));

        emAndamento.iniciar();

        concluida = new Tarefa(
                3L,
                "Estudar Mockito",
                "Praticar mocks",
                PrioridadeTarefa.CRITICA,
                LocalDate.now());

        concluida.iniciar();
        concluida.concluir();
    }

    @Test
    void deveContarTodasAsTarefas() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(pendente, emAndamento, concluida));

        // Act
        int resultado = service.contarTodas();

        // Assert
        Assertions.assertThat(resultado).isEqualTo(3);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveContarTarefasPorStatus() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(pendente, emAndamento, concluida));

        // Act
        Map<StatusTarefa, Long> resultado = service.contarPorStatus();

        // Assert
        Assertions.assertThat(resultado)
                .containsEntry(StatusTarefa.PENDENTE, 1L)
                .containsEntry(StatusTarefa.EM_ANDAMENTO, 1L)
                .containsEntry(StatusTarefa.CONCLUIDA, 1L);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveListarTitulosAgrupadosPorStatus() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(pendente, emAndamento, concluida));

        // Act
        Map<StatusTarefa, List<String>> resultado =
                service.listarTitulosPorStatus();

        // Assert
        Assertions.assertThat(resultado.get(StatusTarefa.PENDENTE))
                .containsExactly("Estudar Java");

        Assertions.assertThat(resultado.get(StatusTarefa.EM_ANDAMENTO))
                .containsExactly("Estudar JUnit");

        Assertions.assertThat(resultado.get(StatusTarefa.CONCLUIDA))
                .containsExactly("Estudar Mockito");

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveCalcularPercentualDeConclusao() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(pendente, emAndamento, concluida));

        // Act
        var resultado = service.calcularPercentualConcluido();

        // Assert
        Assertions.assertThat(resultado)
                .isEqualByComparingTo("33.33");

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveRetornarZeroQuandoNaoExistiremTarefas() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of());

        // Act
        var resultado = service.calcularPercentualConcluido();

        // Assert
        Assertions.assertThat(resultado)
                .isEqualByComparingTo("0");

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveSepararTarefasConcluidasENaoConcluidas() {

        // Arrange
        Mockito.when(repository.listarTodas())
                .thenReturn(List.of(pendente, emAndamento, concluida));

        // Act
        Map<Boolean, List<Tarefa>> resultado =
                service.separarPorConclusao();

        // Assert
        Assertions.assertThat(resultado.get(true))
                .containsExactly(concluida);

        Assertions.assertThat(resultado.get(false))
                .containsExactlyInAnyOrder(pendente, emAndamento);

        // Verify
        Mockito.verify(repository).listarTodas();
    }
    
}