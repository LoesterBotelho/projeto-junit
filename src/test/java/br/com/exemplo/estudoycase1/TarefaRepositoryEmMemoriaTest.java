package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TarefaRepositoryEmMemoriaTest {

    private TarefaRepositoryEmMemoria repository;

    @BeforeEach
    void configurar() {
        repository = new TarefaRepositoryEmMemoria();
    }

    @Test
    void deveSalvarTarefa() {

        // Arrange
        Tarefa tarefa = criarTarefa(1L, "Estudar Java");

        // Act
        Tarefa resultado = repository.salvar(tarefa);

        // Assert
        Assertions.assertThat(resultado).isSameAs(tarefa);
        Assertions.assertThat(repository.existePorId(1L)).isTrue();
    }

    @Test
    void deveBuscarTarefaPorId() {

        // Arrange
        Tarefa tarefa = criarTarefa(1L, "Estudar JUnit");
        repository.salvar(tarefa);

        // Act
        var resultado = repository.buscarPorId(1L);

        // Assert
        Assertions.assertThat(resultado).isPresent();
        Assertions.assertThat(resultado.orElseThrow())
                .isSameAs(tarefa);
    }

    @Test
    void deveRetornarOptionalVazioQuandoTarefaNaoExistir() {

        // Arrange
        Long idInexistente = 99L;

        // Act
        var resultado = repository.buscarPorId(idInexistente);

        // Assert
        Assertions.assertThat(resultado).isEmpty();
    }

    @Test
    void deveListarTodasAsTarefas() {

        // Arrange
        Tarefa tarefa1 = criarTarefa(1L, "Estudar Java");
        Tarefa tarefa2 = criarTarefa(2L, "Estudar Mockito");

        repository.salvar(tarefa1);
        repository.salvar(tarefa2);

        // Act
        List<Tarefa> resultado = repository.listarTodas();

        // Assert
        Assertions.assertThat(resultado)
                .hasSize(2)
                .containsExactlyInAnyOrder(tarefa1, tarefa2);
    }

    @Test
    void deveExcluirTarefaExistente() {

        // Arrange
        Tarefa tarefa = criarTarefa(1L, "Estudar AssertJ");
        repository.salvar(tarefa);

        // Act
        boolean resultado = repository.excluirPorId(1L);

        // Assert
        Assertions.assertThat(resultado).isTrue();
        Assertions.assertThat(repository.existePorId(1L)).isFalse();
    }

    @Test
    void deveRetornarFalsoAoExcluirTarefaInexistente() {

        // Arrange
        Long idInexistente = 99L;

        // Act
        boolean resultado = repository.excluirPorId(idInexistente);

        // Assert
        Assertions.assertThat(resultado).isFalse();
    }

    @Test
    void deveLancarExcecaoAoSalvarTarefaNula() {

        // Arrange
        Tarefa tarefa = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> repository.salvar(tarefa))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A tarefa é obrigatória.");
    }

    @Test
    void deveLancarExcecaoAoBuscarComIdNulo() {

        // Arrange
        Long id = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> repository.buscarPorId(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O ID é obrigatório.");
    }

    @Test
    void deveLancarExcecaoAoExcluirComIdNulo() {

        // Arrange
        Long id = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> repository.excluirPorId(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O ID é obrigatório.");
    }

    @Test
    void deveLancarExcecaoAoVerificarExistenciaComIdNulo() {

        // Arrange
        Long id = null;

        // Act
        // Assert
        Assertions.assertThatThrownBy(
                () -> repository.existePorId(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("O ID é obrigatório.");
    }

    private Tarefa criarTarefa(Long id, String titulo) {
        return new Tarefa(
                id,
                titulo,
                "Descrição de teste",
                PrioridadeTarefa.MEDIA,
                LocalDate.now().plusDays(5)
        );
    }
    
}