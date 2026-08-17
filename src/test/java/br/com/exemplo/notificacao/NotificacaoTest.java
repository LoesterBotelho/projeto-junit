package br.com.exemplo.notificacao;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class NotificacaoTest {

    @Test
    void deveCriarNotificacaoValida() {
        LocalDateTime agora = LocalDateTime.now();
        Notificacao n = new Notificacao("1", "Destinatario", "Conteudo", CanalNotificacao.EMAIL, 
                                        PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, agora);

        assertEquals("1", n.id());
        assertEquals("Destinatario", n.destinatario());
        assertEquals(StatusNotificacao.PENDENTE, n.status());
    }

    @Test
    void deveLancarExcecaoSeDestinatarioEstiverEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Notificacao("1", "   ", "Conteudo", CanalNotificacao.EMAIL, 
                            PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, LocalDateTime.now())
        );
    }

    @Test
    void deveLancarExcecaoParaAtributosNulosNoRecord() {
        assertThrows(NullPointerException.class, () -> 
            new Notificacao(null, "Destinatario", "Conteudo", CanalNotificacao.EMAIL, 
                            PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, LocalDateTime.now())
        );
    }

    @Test
    void deveRetornarNovaInstanciaComNovoStatus() {
        Notificacao original = new Notificacao("1", "Destinatario", "Conteudo", CanalNotificacao.EMAIL, 
                                              PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, LocalDateTime.now());
        
        Notificacao atualizada = original.comStatus(StatusNotificacao.ENVIADO);

        assertEquals(StatusNotificacao.PENDENTE, original.status());
        assertEquals(StatusNotificacao.ENVIADO, atualizada.status());
        assertEquals(original.id(), atualizada.id());
    }
}