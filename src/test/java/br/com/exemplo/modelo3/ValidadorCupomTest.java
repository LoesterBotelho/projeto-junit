package br.com.exemplo.modelo3;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidadorCupomTest {

    private ValidadorCupom validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorCupom();
    }

    @Test
    @DisplayName("Deve validar com sucesso o cupom BLACKFRIDAY acima do valor mínimo")
    void deveValidarBlackFridayValida() {
        boolean valido = validador.validarCupom("BLACKFRIDAY", 250.0, LocalDate.of(2026, 11, 26));
        assertTrue(valido);
    }

    @Test
    @DisplayName("Deve falhar o cupom BLACKFRIDAY se o valor da compra for abaixo do mínimo")
    void deveLancarErroBlackFridayAbaixoMinimo() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            validador.validarCupom("BLACKFRIDAY", 150.0, LocalDate.of(2026, 11, 26));
        });
        assertEquals("Compra mínima para este cupom é de R$ 200,00", ex.getMessage());
    }

    @Test
    @DisplayName("Deve validar cupom NATAL10 se estiver dentro da data limite")
    void deveValidarNatalNoPrazo() {
        LocalDate dataTeste = LocalDate.of(2026, 12, 20);
        boolean valido = validador.validarCupom("NATAL10", 100.0, dataTeste);
        assertTrue(valido);
    }

    @Test
    @DisplayName("Deve expirar cupom NATAL10 se a data atual for posterior a 25 de dezembro")
    void deveLancarErroNatalExpirado() {
        LocalDate dataTeste = LocalDate.of(2026, 12, 26);
        assertThrows(IllegalStateException.class, () -> {
            validador.validarCupom("NATAL10", 100.0, dataTeste); 
        });
    }

    @ParameterizedTest(name = "Cupom '{0}' com valor {1} deve lançar exceção")
    @CsvSource({
        " , 100.0",
        "'' , 100.0",
        "CUPOM_INEXISTENTE, 100.0"
    })
    void deveLancarExcecaoParaCuponsInvalidos(String codigo, double valor) {
        assertThrows(IllegalArgumentException.class, () -> {
            validador.validarCupom(codigo, valor, LocalDate.now());
        });
    }
}
