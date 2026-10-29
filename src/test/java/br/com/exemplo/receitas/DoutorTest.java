package br.com.exemplo.receitas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoutorTest {

    @Test
    void deveCriarDoutorComSucesso() {
        var doutor = new Doutor(
                1L,
                "Roberto Lima",
                "123456",
                "SC");

        assertEquals(1L, doutor.getId());
        assertEquals("Roberto Lima", doutor.getNome());
        assertEquals("123456", doutor.getCrm());
        assertEquals("SC", doutor.getUf());
    }

    @Test
    void deveIdentificarRegistroEmSantaCatarina() {
        var doutor = new Doutor(
                2L,
                "Fernanda Alves",
                "654321",
                "SC");

        assertEquals("SC", doutor.getUf());
    }
    
}