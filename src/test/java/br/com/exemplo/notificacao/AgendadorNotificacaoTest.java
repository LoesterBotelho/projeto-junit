package br.com.exemplo.notificacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class AgendadorNotificacaoTest {
    private AgendadorNotificacao agendador;

    @BeforeEach
    void setup() {
        agendador = new AgendadorNotificacao();
    }

    @Test
    void deveAgendarNotificacaoPendente() {
        Notificacao n = new Notificacao("1", "User", "Conteudo", CanalNotificacao.EMAIL, 
                                        PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, LocalDateTime.now());
        agendador.agendar(n);
        assertEquals(1, agendador.quantidadePendente());
    }

    @Test
    void deveLancarExcecaoParaStatusNaoPendente() {
        Notificacao n = new Notificacao("1", "User", "Conteudo", CanalNotificacao.EMAIL, 
                                        PrioridadeNotificacao.ALTA, StatusNotificacao.ENVIADO, LocalDateTime.now());
        assertThrows(IllegalArgumentException.class, () -> agendador.agendar(n));
    }
}