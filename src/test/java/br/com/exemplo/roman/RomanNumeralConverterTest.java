package br.com.exemplo.roman;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class RomanNumeralConverterTest {

    private static final Pattern ROMAN_REGEX = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

    @ParameterizedTest
    @CsvSource({
        "1, I", "4, IV", "9, IX", "49, XLIX", "99, XCIX", 
        "399, CCCXCIX", "1984, MCMLXXXIV", "3999, MMMCMXCIX"
    })
    @DisplayName("Teste de conversão de casos conhecidos")
    void testConversoesConhecidas(int input, String expected) {
        assertEquals(expected, RomanNumeralConverter.toRoman(input));
    }

    @Test
    @DisplayName("Teste exaustivo: validando toda a faixa de 1 a 3999")
    void testExaustivo() {
        IntStream.rangeClosed(1, 3999).forEach(i -> {
            String result = RomanNumeralConverter.toRoman(i);
            assertTrue(ROMAN_REGEX.matcher(result).matches(), "Falha no número: " + i);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 4000, 5000})
    @DisplayName("Teste de exceções para valores fora do limite")
    void testExcecoes(int input) {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeralConverter.toRoman(input));
    }
}