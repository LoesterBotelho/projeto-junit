package br.com.exemplo.rh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

	@Test
	@DisplayName("Deve criar funcionário com dados válidos")
	void deveCriarFuncionarioValido() {
		Funcionario f = new Funcionario("Ana", "11122233344", 3000.0, 2);
		assertEquals("Ana", f.getNome());
		assertEquals(3000.0, f.getSalarioBase());
		assertEquals(2, f.getDependentes());
	}

	@Test
	@DisplayName("Deve lançar exceção ao instanciar funcionário com salário negativo")
	void deveFalharSalarioNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Funcionario("Ana", "111", -100.0, 0));
	}
}