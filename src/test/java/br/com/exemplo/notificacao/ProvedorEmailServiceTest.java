package br.com.exemplo.notificacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ProvedorEmailServiceTest {

    private ProvedorEmailService emailService;

    @BeforeEach
    void setup() {
        emailService = new ProvedorEmailService();
    }

    @ParameterizedTest
    @ValueSource(strings = {"teste@exemplo.com", "usuario.nome+tag@sub.dominio.org"})
    void deveValidarEmailsCorretos(String email) {
        assertTrue(emailService.validarEmail(email));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"invalido.com", "usuario@", "@dominio.com"})
    void deveInvalidarEmailsIncorretos(String email) {
        assertFalse(emailService.validarEmail(email));
    }

    @Test
    void deveFormatarMensagemHtmlComSucesso() {
        String html = emailService.formatarMensagemHTML("Aviso", "Bem-vindo ao sistema");
        
        assertNotNull(html);
        assertTrue(html.contains("<h2>Aviso</h2>"));
        assertTrue(html.contains("<p>Bem-vindo ao sistema</p>"));
    }

    @Test
    void deveLancarExcecaoAoFormatarHtmlComParametrosNulos() {
        assertThrows(IllegalArgumentException.class, () -> emailService.formatarMensagemHTML(null, "Corpo"));
        assertThrows(IllegalArgumentException.class, () -> emailService.formatarMensagemHTML("Titulo", null));
    }
}