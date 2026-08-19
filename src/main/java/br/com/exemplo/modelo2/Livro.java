package br.com.exemplo.modelo2;

public record Livro(String isbn, String titulo, String autor, int totalExemplares, int exemplaresDisponiveis) {
    public Livro {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN inválido.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título inválido.");
        }
        if (totalExemplares < 0 || exemplaresDisponiveis < 0 || exemplaresDisponiveis > totalExemplares) {
            throw new IllegalArgumentException("Quantidade de exemplares inválida.");
        }
    }

    public Livro emprestarExemplar() {
        if (exemplaresDisponiveis <= 0) {
            throw new IllegalStateException("Não há exemplares disponíveis para empréstimo.");
        }
        return new Livro(isbn, titulo, autor, totalExemplares, exemplaresDisponiveis - 1);
    }

    public Livro devolverExemplar() {
        if (exemplaresDisponiveis >= totalExemplares) {
            throw new IllegalStateException("Todos os exemplares já estão na biblioteca.");
        }
        return new Livro(isbn, titulo, autor, totalExemplares, exemplaresDisponiveis + 1);
    }
}