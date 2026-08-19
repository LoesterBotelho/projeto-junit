package br.com.exemplo.modelo2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    @DisplayName("Deve realizar empréstimo decrementando o estoque disponível")
    void deveEmprestarExemplarComSucesso() {
        var livro = new Livro("978-3-16-148410-0", "Clean Code", "Robert Martin", 3, 3);
        var livroAtualizado = livro.emprestarExemplar();

        assertEquals(2, livroAtualizado.exemplaresDisponiveis());
    }

    @Test
    @DisplayName("Deve falhar ao tentar emprestar livro sem exemplares disponíveis")
    void deveFalharEmprestimoSemEstoque() {
        var livro = new Livro("978-3-16-148410-0", "Clean Code", "Robert Martin", 1, 0);
        
        assertThrows(IllegalStateException.class, livro::emprestarExemplar);
    }

    @Test
    @DisplayName("Deve devolver exemplar incrementando o estoque corretamente")
    void deveDevolverExemplarComSucesso() {
        var livro = new Livro("978-3-16-148410-0", "Clean Code", "Robert Martin", 3, 1);
        var livroAtualizado = livro.devolverExemplar();

        assertEquals(2, livroAtualizado.exemplaresDisponiveis());
    }
}