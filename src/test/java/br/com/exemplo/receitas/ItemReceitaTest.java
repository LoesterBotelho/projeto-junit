package br.com.exemplo.receitas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemReceitaTest {

    @Test
    void deveCriarItemReceitaComSucesso() {
        var medicamento = new Medicamento(
                1L,
                "Medicamento A",
                "Principio ativo A",
                "500 mg");

        var item = new ItemReceita(
                medicamento,
                "Conforme prescrição",
                20,
                "Seguir as instruções do prescritor.");

        assertSame(medicamento, item.getMedicamento());
        assertEquals("Conforme prescrição", item.getDosagem());
        assertEquals(20, item.getQuantidade());
        assertEquals(
                "Seguir as instruções do prescritor.",
                item.getInstrucoesUso());
    }

    @Test
    void deveRejeitarQuantidadeZero() {
        var medicamento = new Medicamento(
                1L,
                "Medicamento A",
                "Principio ativo A",
                "500 mg");

        assertThrows(
                IllegalArgumentException.class,
                () -> new ItemReceita(
                        medicamento,
                        "Conforme prescrição",
                        0,
                        "Seguir as instruções do prescritor."));
    }

    @Test
    void deveRejeitarDosagemVazia() {
        var medicamento = new Medicamento(
                1L,
                "Medicamento A",
                "Principio ativo A",
                "500 mg");

        assertThrows(
                IllegalArgumentException.class,
                () -> new ItemReceita(
                        medicamento,
                        " ",
                        10,
                        "Seguir as instruções do prescritor."));
    }
    
}