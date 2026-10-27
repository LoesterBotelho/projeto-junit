package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    private Tarefa tarefa;

    @BeforeEach
    void configurar() {
        tarefa = new Tarefa(
                1L,
                "Estudar Java 25",
                "Praticar testes unitários",
                PrioridadeTarefa.ALTA,
                LocalDate.now().plusDays(5)
        );
    }

    @Test
    void deveCriarTarefaComSucesso() {

        // Arrange
        Mockito.when(repository.existePorId(1L))
                .thenReturn(false);

        Mockito.when(repository.salvar(tarefa))
                .thenReturn(tarefa);

        // Act
        Tarefa resultado = service.criar(tarefa);

        // Assert
        Assertions.assertThat(resultado).isSameAs(tarefa);

        // Verify
        Mockito.verify(repository).existePorId(1L);
        Mockito.verify(repository).salvar(tarefa);
    }

    @Test
    void deveLancarExcecaoQuandoTarefaForNula() {

        // Arrange
        Tarefa tarefaNula = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.criar(tarefaNula))
                .isInstanceOf(TarefaInvalidaException.class)
                .hasMessage("A tarefa é obrigatória.");

        // Verify
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    void deveLancarExcecaoQuandoIdJaExistir() {

        // Arrange
        Mockito.when(repository.existePorId(1L))
                .thenReturn(true);

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.criar(tarefa))
                .isInstanceOf(TarefaInvalidaException.class)
                .hasMessage("Já existe uma tarefa com esse ID.");

        // Verify
        Mockito.verify(repository).existePorId(1L);
        Mockito.verify(repository, Mockito.never())
                .salvar(Mockito.any(Tarefa.class));
    }

    @Test
    void deveBuscarTarefaExistente() {

        // Arrange
        Mockito.when(repository.buscarPorId(1L))
                .thenReturn(Optional.of(tarefa));

        // Act
        Tarefa resultado = service.buscarPorId(1L);

        // Assert
        Assertions.assertThat(resultado).isSameAs(tarefa);

        // Verify
        Mockito.verify(repository).buscarPorId(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTarefaNaoExistir() {

        // Arrange
        Mockito.when(repository.buscarPorId(99L))
                .thenReturn(Optional.empty());

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.buscarPorId(99L))
                .isInstanceOf(TarefaNaoEncontradaException.class)
                .hasMessage("Tarefa não encontrada: 99");

        // Verify
        Mockito.verify(repository).buscarPorId(99L);
    }

    @Test
    void deveListarTodasAsTarefas() {

        // Arrange
        List<Tarefa> tarefas = List.of(tarefa);

        Mockito.when(repository.listarTodas())
                .thenReturn(tarefas);

        // Act
        List<Tarefa> resultado = service.listarTodas();

        // Assert
        Assertions.assertThat(resultado)
                .containsExactly(tarefa);

        // Verify
        Mockito.verify(repository).listarTodas();
    }

    @Test
    void deveIniciarTarefaPendente() {

        // Arrange
        Mockito.when(repository.buscarPorId(1L))
                .thenReturn(Optional.of(tarefa));

        Mockito.when(repository.salvar(tarefa))
                .thenReturn(tarefa);

        // Act
        Tarefa resultado = service.iniciar(1L);

        // Assert
        Assertions.assertThat(resultado.getStatus())
                .isEqualTo(StatusTarefa.EM_ANDAMENTO);

        // Verify
        Mockito.verify(repository).salvar(tarefa);
    }

    @Test
    void deveConcluirTarefaEmAndamento() {

        // Arrange
        tarefa.iniciar();

        Mockito.when(repository.buscarPorId(1L))
                .thenReturn(Optional.of(tarefa));

        Mockito.when(repository.salvar(tarefa))
                .thenReturn(tarefa);

        // Act
        Tarefa resultado = service.concluir(1L);

        // Assert
        Assertions.assertThat(resultado.getStatus())
                .isEqualTo(StatusTarefa.CONCLUIDA);

        // Verify
        Mockito.verify(repository).salvar(tarefa);
    }

    @Test
    void deveExcluirTarefaExistente() {

        // Arrange
        Mockito.when(repository.excluirPorId(1L))
                .thenReturn(true);

        // Act
        Assertions.assertThatCode(() -> service.excluir(1L))
                .doesNotThrowAnyException();

        // Assert
        // Verify
        Mockito.verify(repository).excluirPorId(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirTarefaInexistente() {

        // Arrange
        Mockito.when(repository.excluirPorId(99L))
                .thenReturn(false);

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.excluir(99L))
                .isInstanceOf(TarefaNaoEncontradaException.class)
                .hasMessage("Tarefa não encontrada: 99");

        // Verify
        Mockito.verify(repository).excluirPorId(99L);
    }

    @Test
    void deveLancarExcecaoQuandoIdForInvalido() {

        // Arrange
        Long id = 0L;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> service.buscarPorId(id))
                .isInstanceOf(TarefaInvalidaException.class)
                .hasMessage("O ID deve ser positivo.");

        // Verify
        Mockito.verifyNoInteractions(repository);
    }
}