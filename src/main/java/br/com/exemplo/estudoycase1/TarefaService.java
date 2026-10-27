package br.com.exemplo.estudoycase1;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                    "O repositório é obrigatório.");
        }

        this.repository = repository;
    }

    public Tarefa criar(Tarefa tarefa) {
        if (tarefa == null) {
            throw new TarefaInvalidaException(
                    "A tarefa é obrigatória.");
        }

        if (repository.existePorId(tarefa.getId())) {
            throw new TarefaInvalidaException(
                    "Já existe uma tarefa com esse ID.");
        }

        return repository.salvar(tarefa);
    }

    public Tarefa buscarPorId(Long id) {
        validarId(id);

        return repository.buscarPorId(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(
                        "Tarefa não encontrada: " + id));
    }

    public List<Tarefa> listarTodas() {
        return repository.listarTodas();
    }

    public Tarefa iniciar(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.iniciar();
        return repository.salvar(tarefa);
    }

    public Tarefa concluir(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.concluir();
        return repository.salvar(tarefa);
    }

    public Tarefa cancelar(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.cancelar();
        return repository.salvar(tarefa);
    }

    public void excluir(Long id) {
        validarId(id);

        if (!repository.excluirPorId(id)) {
            throw new TarefaNaoEncontradaException(
                    "Tarefa não encontrada: " + id);
        }
    }

    public List<Tarefa> filtrarPorStatus(StatusTarefa status) {
        if (status == null) {
            throw new TarefaInvalidaException(
                    "O status é obrigatório.");
        }

        Predicate<Tarefa> possuiStatus =
                tarefa -> tarefa.getStatus() == status;

        return repository.listarTodas()
                .stream()
                .filter(possuiStatus)
                .toList();
    }

    public List<Tarefa> filtrarPorPrioridade(
            PrioridadeTarefa prioridade) {

        if (prioridade == null) {
            throw new TarefaInvalidaException(
                    "A prioridade é obrigatória.");
        }

        Predicate<Tarefa> possuiPrioridade =
                tarefa -> tarefa.getPrioridade() == prioridade;

        return repository.listarTodas()
                .stream()
                .filter(possuiPrioridade)
                .toList();
    }

    public List<Tarefa> listarAtrasadas() {
        LocalDate hoje = LocalDate.now();

        Predicate<Tarefa> possuiPrazoVencido =
                tarefa -> tarefa.getPrazo() != null
                        && tarefa.getPrazo().isBefore(hoje);

        Predicate<Tarefa> naoEstaFinalizada =
                tarefa -> tarefa.getStatus() != StatusTarefa.CONCLUIDA
                        && tarefa.getStatus() != StatusTarefa.CANCELADA;

        return repository.listarTodas()
                .stream()
                .filter(possuiPrazoVencido.and(naoEstaFinalizada))
                .toList();
    }

    public List<Tarefa> listarOrdenadasPorPrazo() {
        Comparator<Tarefa> comparador =
                Comparator.comparing(
                        Tarefa::getPrazo,
                        Comparator.nullsLast(Comparator.naturalOrder()));

        return repository.listarTodas()
                .stream()
                .sorted(comparador)
                .toList();
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new TarefaInvalidaException(
                    "O ID deve ser positivo.");
        }
    }
    
}