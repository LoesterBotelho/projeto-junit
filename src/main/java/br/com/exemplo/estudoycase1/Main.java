package br.com.exemplo.estudoycase1;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        final String DIV =
                "-------------------------------------------------------------------------------------------";

        IO.println(DIV);
        IO.println("ESTUDO Y - GERENCIAMENTO DE TAREFAS");
        IO.println(DIV);

        TarefaRepository repository = new TarefaRepositoryEmMemoria();

        Tarefa tarefa1 = new Tarefa(
                1L,
                "Estudar Java 25",
                "Praticar interfaces e coleções.",
                PrioridadeTarefa.ALTA,
                LocalDate.now().plusDays(7)
        );

        Tarefa tarefa2 = new Tarefa(
                2L,
                "Estudar JUnit 6",
                "Criar testes unitários.",
                PrioridadeTarefa.CRITICA,
                LocalDate.now().plusDays(3)
        );

        Tarefa tarefa3 = new Tarefa(
                3L,
                "Estudar Mockito 5",
                "Criar testes com mocks.",
                PrioridadeTarefa.MEDIA,
                LocalDate.now().minusDays(2)
        );

        Tarefa tarefa4 = new Tarefa(
                4L,
                "Estudar Spring Boot",
                "Desenvolver APIs REST.",
                PrioridadeTarefa.BAIXA,
                LocalDate.now().plusDays(10)
        );

        IO.println("EXEMPLO 1 - SALVAR");
        IO.println(DIV);

        repository.salvar(tarefa1);
        repository.salvar(tarefa2);
        repository.salvar(tarefa3);
        repository.salvar(tarefa4);

        IO.println("Tarefas salvas: " + repository.listarTodas().size());

        IO.println(DIV);
        IO.println("EXEMPLO 2 - BUSCAR POR ID");
        IO.println(DIV);

        repository.buscarPorId(1L).ifPresent(
                tarefa -> IO.println("Encontrada: " + tarefa.getTitulo())
        );

        IO.println(DIV);
        IO.println("EXEMPLO 3 - VERIFICAR EXISTÊNCIA");
        IO.println(DIV);

        IO.println("Existe ID 2? " + repository.existePorId(2L));
        IO.println("Existe ID 99? " + repository.existePorId(99L));

        IO.println(DIV);
        IO.println("EXEMPLO 4 - LISTAR TODAS");
        IO.println(DIV);

        repository.listarTodas().forEach(
                tarefa -> IO.println(
                        tarefa.getId() + " - "
                                + tarefa.getTitulo() + " - "
                                + tarefa.getPrioridade() + " - "
                                + tarefa.getStatus()
                )
        );

        IO.println(DIV);
        IO.println("EXEMPLO 5 - INICIAR E CONCLUIR TAREFAS");
        IO.println(DIV);

        tarefa1.iniciar();
        tarefa1.concluir();

        tarefa2.iniciar();

        repository.salvar(tarefa1);
        repository.salvar(tarefa2);

        repository.listarTodas().forEach(
                tarefa -> IO.println(
                        tarefa.getTitulo() + " - " + tarefa.getStatus()
                )
        );

        IO.println(DIV);
        IO.println("EXEMPLO 6 - FILTRAR POR STATUS");
        IO.println(DIV);

        TarefaService tarefaService = new TarefaService(repository);

        tarefaService.filtrarPorStatus(StatusTarefa.PENDENTE).forEach(
                tarefa -> IO.println(tarefa.getTitulo())
        );

        IO.println(DIV);
        IO.println("EXEMPLO 7 - FILTRAR POR PRIORIDADE");
        IO.println(DIV);

        tarefaService.filtrarPorPrioridade(PrioridadeTarefa.CRITICA).forEach(
                tarefa -> IO.println(tarefa.getTitulo())
        );

        IO.println(DIV);
        IO.println("EXEMPLO 8 - LISTAR TAREFAS ATRASADAS");
        IO.println(DIV);

        tarefaService.listarAtrasadas().forEach(
                tarefa -> IO.println(
                        tarefa.getTitulo() + " - Prazo: " + tarefa.getPrazo()
                )
        );

        IO.println(DIV);
        IO.println("EXEMPLO 9 - ORDENAR POR PRAZO");
        IO.println(DIV);

        tarefaService.listarOrdenadasPorPrazo().forEach(
                tarefa -> IO.println(
                        tarefa.getTitulo() + " - " + tarefa.getPrazo()
                )
        );

        IO.println(DIV);
        IO.println("EXEMPLO 10 - PREDICATES");
        IO.println(DIV);

        repository.listarTodas().stream()
                .filter(TarefaPredicados.porPrioridade(PrioridadeTarefa.ALTA))
                .forEach(
                        tarefa -> IO.println("Alta prioridade: " + tarefa.getTitulo())
                );

        repository.listarTodas().stream()
                .filter(TarefaPredicados.tituloContem("java"))
                .forEach(
                        tarefa -> IO.println("Título contém Java: " + tarefa.getTitulo())
                );

        IO.println(DIV);
        IO.println("EXEMPLO 11 - TRANSFORMACOES COM FUNCTION");
        IO.println(DIV);

        repository.listarTodas().stream()
                .map(TarefaTransformacoes.paraTitulo())
                .forEach(IO::println);

        repository.listarTodas().stream()
                .map(TarefaTransformacoes.paraResumo())
                .forEach(IO::println);

        IO.println(DIV);
        IO.println("EXEMPLO 12 - RELATORIOS");
        IO.println(DIV);

        RelatorioTarefasService relatorioService =
                new RelatorioTarefasService(repository);

        IO.println("Total de tarefas: " + relatorioService.contarTodas());

        IO.println("Quantidade por status:");
        relatorioService.contarPorStatus().forEach(
                (status, quantidade) ->
                        IO.println(status + ": " + quantidade)
        );

        IO.println(DIV);
        IO.println("Quantidade por prioridade:");

        relatorioService.contarPorPrioridade().forEach(
                (prioridade, quantidade) ->
                        IO.println(prioridade + ": " + quantidade)
        );

        IO.println(DIV);
        IO.println(
                "Percentual concluído: "
                        + relatorioService.calcularPercentualConcluido()
                        + "%"
        );

        IO.println(DIV);
        IO.println("EXEMPLO 13 - RELATORIO POR CONCLUSAO");
        IO.println(DIV);

        relatorioService.separarPorConclusao().forEach(
                (concluida, tarefas) -> {
                    IO.println(concluida ? "Concluídas:" : "Não concluídas:");

                    tarefas.forEach(
                            tarefa -> IO.println(" - " + tarefa.getTitulo())
                    );
                }
        );

        IO.println(DIV);
        IO.println("EXEMPLO 14 - EXCLUIR");
        IO.println(DIV);

        IO.println("Excluída: " + repository.excluirPorId(1L));
        IO.println("Total restante: " + repository.listarTodas().size());
    }
    
}