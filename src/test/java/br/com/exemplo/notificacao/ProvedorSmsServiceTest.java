package br.com.exemplo.notificacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ProvedorSmsServiceTest {

    private ProvedorSmsService smsService;

    @BeforeEach
    void setup() {
        smsService = new ProvedorSmsService();
    }

    @ParameterizedTest
    @ValueSource(strings = {"+5511988887777", "+14155552671"})
    void deveValidarTelefonesCorretos(String telefone) {
        assertTrue(smsService.validarTelefone(telefone));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11988887777", "+05511988887777", "abc", ""})
    void deveInvalidarTelefonesIncorretos(String telefone) {
        assertFalse(smsService.validarTelefone(telefone));
    }

    @Test
    void naoDeveTruncarMensagemCurtaParaSms() {
        String mensagemCurta = "Olá, tudo bem?";
        String resultado = smsService.truncarParaSms(mensagemCurta);
        assertEquals(mensagemCurta, resultado);
    }

    @Test
    void deveTruncarMensagemLongaParaSms() {
        String mensagemLonga = "A".repeat(200); // Maior que o limite do SMS (160 caracteres)
        String resultado = smsService.truncarParaSms(mensagemLonga);
        
        assertEquals(160, resultado.length());
    }

    @Test
    void deveRetornarVazioParaMensagemNulaNoSms() {
        assertEquals("", smsService.truncarParaSms(null));
    }
}