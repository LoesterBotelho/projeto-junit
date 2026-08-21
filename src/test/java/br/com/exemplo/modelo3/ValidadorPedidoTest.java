package br.com.exemplo.modelo3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorPedidoTest {

    private ValidadorPedido validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorPedido();
    }

    @Test
    @DisplayName("Deve passar com sucesso para um pedido válido")
    void devePassarPedidoValido() {
        assertDoesNotThrow(() -> validador.validar("João Silva", 150.0, 3));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lançar exceção se o nome do cliente for nulo ou vazio")
    void deveLancarExcecaoClienteInvalido(String clienteInvalido) {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            validador.validar(clienteInvalido, 100.0, 2);
        });
        assertEquals("Cliente não pode ser nulo ou vazio", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.5, -100.0})
    @DisplayName("Deve lançar exceção para valores totais inválidos (zero ou negativos)")
    void deveLancarExcecaoValorInvalido(double valor) {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar("Maria", valor, 1);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção se a quantidade de itens for zero ou negativa")
    void deveLancarExcecaoQuantidadeInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.validar("Carlos", 50.0, 0);
        });
    }
}