package br.com.exemplo.receitas;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // -------------------------------------------------------------------------------------------

        var paciente = new Paciente(
                1L,
                "Ana Silva",
                "12345678901",
                LocalDate.of(1995, 5, 12));

        var doutor = new Doutor(
                1L,
                "Roberto Lima",
                "123456",
                "SC");

        var medicamento = new Medicamento(
                1L,
                "Medicamento A",
                "Principio ativo A",
                "500 mg");

        var receita = new Receita(
                1L,
                "SC-100",
                TipoReceita.BRANCA,
                paciente,
                doutor,
                LocalDate.of(2026, 10, 28),
                "hash-documento-100",
                true);

        receita.adicionarItem(new ItemReceita(
                medicamento,
                "Conforme prescrição",
                10,
                "Seguir as instruções do prescritor."));

        var receitaRegistrada = new Receita(
                2L,
                "SC-100",
                TipoReceita.BRANCA,
                paciente,
                doutor,
                LocalDate.of(2026, 10, 27),
                "hash-documento-100",
                true);

        var receitasRegistradas = List.of(receitaRegistrada);

        var detector = new DetectorFraude();

        var indicios = detector.detectar(
                receita,
                receitasRegistradas,
                LocalDate.of(2026, 10, 28));

        // -------------------------------------------------------------------------------------------

        System.out.println("SISTEMA DE CONTROLE DE RECEITAS");
        System.out.println("Paciente: " + receita.getPaciente().getNome());
        System.out.println("Prescritor: " + receita.getDoutor().getNome());
        System.out.println("Número: " + receita.getNumero());
        System.out.println("Tipo: " + receita.getTipo());
        System.out.println("Medicamentos: " + receita.getItens().size());
        System.out.println("Indícios encontrados: " + indicios.size());

        if (indicios.isEmpty()) {
            System.out.println("Nenhum indício identificado.");
        } else {
            indicios.forEach(System.out::println);
        }

        // -------------------------------------------------------------------------------------------
    }
}
