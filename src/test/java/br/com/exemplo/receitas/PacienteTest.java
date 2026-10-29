package br.com.exemplo.receitas;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PacienteTest {

    @Test
    void deveCriarPacienteComSucesso() {
        var paciente = new Paciente(
                1L,
                "Ana Silva",
                "12345678901",
                LocalDate.of(1995, 5, 12));

        assertEquals(1L, paciente.getId());
        assertEquals("Ana Silva", paciente.getNome());
        assertEquals("12345678901", paciente.getCpf());
        assertEquals(
                LocalDate.of(1995, 5, 12),
                paciente.getDataNascimento());
    }

    @Test
    void deveRetornarDataNascimentoDoPaciente() {
        var paciente = new Paciente(
                2L,
                "Carlos Souza",
                "98765432100",
                LocalDate.of(1982, 9, 20));

        assertNotNull(paciente.getDataNascimento());
        assertEquals(1982, paciente.getDataNascimento().getYear());
    }
    
}