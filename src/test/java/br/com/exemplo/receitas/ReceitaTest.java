package br.com.exemplo.receitas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class ReceitaTest {

    @Test
    void deveAdicionarMedicamentoAReceita() {
        var receita = criarReceita();
        var medicamento = criarMedicamento();

        var item = new ItemReceita(
                medicamento,
                "Conforme prescrição",
                10,
                "Seguir as instruções do prescritor.");

        receita.adicionarItem(item);

        assertEquals(1, receita.getItens().size());
        assertEquals(medicamento, receita.getItens().getFirst().getMedicamento());
    }

    @Test
    void deveAdicionarMultiplosMedicamentos() {
        var receita = criarReceita();

        var primeiro = new ItemReceita(
                criarMedicamento(),
                "Dosagem prescrita A",
                10,
                "Instruções A");

        var segundo = new ItemReceita(
                new Medicamento(
                        2L,
                        "Medicamento B",
                        "Principio ativo B",
                        "250 mg"),
                "Dosagem prescrita B",
                20,
                "Instruções B");

        receita.adicionarItem(primeiro);
        receita.adicionarItem(segundo);

        assertEquals(2, receita.getItens().size());
    }

    @Test
    void deveImpedirAlteracaoDiretaDaLista() {
        var receita = criarReceita();

        assertThrows(
                UnsupportedOperationException.class,
                () -> receita.getItens().add(
                        new ItemReceita(
                                criarMedicamento(),
                                "Dosagem prescrita",
                                10,
                                "Instruções de uso")));
    }

    @Test
    void deveRejeitarItemNulo() {
        var receita = criarReceita();

        assertThrows(
                NullPointerException.class,
                () -> receita.adicionarItem(null));
    }

    private Receita criarReceita() {
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

        return new Receita(
                1L,
                "SC-100",
                TipoReceita.BRANCA,
                paciente,
                doutor,
                LocalDate.of(2026, 10, 28),
                "hash-documento-100",
                true);
    }

    private Medicamento criarMedicamento() {
        return new Medicamento(
                1L,
                "Medicamento A",
                "Principio ativo A",
                "500 mg");
    }
    
}