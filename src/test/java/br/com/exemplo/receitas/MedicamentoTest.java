package br.com.exemplo.receitas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedicamentoTest {

    @Test
    void deveCriarMedicamentoComSucesso() {
        var medicamento = new Medicamento(
                1L,
                "Medicamento A",
                "Principio ativo A",
                "500 mg");

        assertEquals(1L, medicamento.getId());
        assertEquals("Medicamento A", medicamento.getNome());
        assertEquals("Principio ativo A", medicamento.getPrincipioAtivo());
        assertEquals("500 mg", medicamento.getConcentracao());
    }

    @Test
    void deveRetornarConcentracaoDoMedicamento() {
        var medicamento = new Medicamento(
                2L,
                "Medicamento B",
                "Principio ativo B",
                "10 mg/mL");

        assertEquals("10 mg/mL", medicamento.getConcentracao());
    }
    
}