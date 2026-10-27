package br.com.exemplo.estudoycase1;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioTarefasService {

    private final TarefaRepository repository;

    public RelatorioTarefasService(TarefaRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                    "O repositório é obrigatório.");
        }

        this.repository = repository;
    }

    public int contarTodas() {
        return repository.listarTodas().size();
    }

    public Map<StatusTarefa, Long> contarPorStatus() {
        return repository.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(
                        Tarefa::getStatus,
                        Collectors.counting()));
    }

    public Map<PrioridadeTarefa, Long> contarPorPrioridade() {
        return repository.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(
                        Tarefa::getPrioridade,
                        Collectors.counting()));
    }

    public List<Tarefa> listarPorPrioridadeEDepoisPorPrazo() {
        Comparator<Tarefa> comparador =
                Comparator.comparing(Tarefa::getPrioridade)
                        .reversed()
                        .thenComparing(
                                Tarefa::getPrazo,
                                Comparator.nullsLast(
                                        Comparator.naturalOrder()));

        return repository.listarTodas()
                .stream()
                .sorted(comparador)
                .toList();
    }

    public Map<StatusTarefa, List<String>> listarTitulosPorStatus() {
        return repository.listarTodas()
                .stream()
                .collect(Collectors.groupingBy(
                        Tarefa::getStatus,
                        Collectors.mapping(
                                Tarefa::getTitulo,
                                Collectors.toList())));
    }

    public BigDecimal calcularPercentualConcluido() {
        List<Tarefa> tarefas = repository.listarTodas();

        if (tarefas.isEmpty()) {
            return BigDecimal.ZERO;
        }

        long concluidas = tarefas.stream()
                .filter(tarefa ->
                        tarefa.getStatus() == StatusTarefa.CONCLUIDA)
                .count();

        return BigDecimal.valueOf(concluidas)
                .multiply(BigDecimal.valueOf(100))
                .divide(
                        BigDecimal.valueOf(tarefas.size()),
                        2,
                        java.math.RoundingMode.HALF_UP);
    }

    public Map<Boolean, List<Tarefa>> separarPorConclusao() {
        return repository.listarTodas()
                .stream()
                .collect(Collectors.partitioningBy(
                        tarefa ->
                                tarefa.getStatus()
                                        == StatusTarefa.CONCLUIDA));
    }
    
}