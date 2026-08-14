package br.com.exemplo.ecommerce;

import org.junit.jupiter.api.Test;

import br.com.exemplo.ecommerce.Produto;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

	@Test
	void deveCriarProdutoComSucesso() {
		Produto p = new Produto("1", "Notebook", 3500.0, 2.5, 10);
		assertAll(() -> assertEquals("Notebook", p.getNome()), () -> assertEquals(3500.0, p.getPreco()),
				() -> assertEquals(10, p.getEstoque()));
	}

	@Test
	void deveLancarExcecaoParaValoresNegativos() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produto("2", "Mouse", -50.0, 0.2, 5);
		});
	}

	@Test
	void deveRemoverEstoqueCorretamente() {
		Produto p = new Produto("3", "Teclado", 150.0, 0.5, 20);
		p.removerEstoque(5);
		assertEquals(15, p.getEstoque());
	}
}