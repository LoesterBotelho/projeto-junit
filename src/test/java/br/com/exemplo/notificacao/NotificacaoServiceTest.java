package br.com.exemplo.notificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificacaoServiceTest {

    @Mock private AgendadorNotificacao agendador;
    @Mock private GerenciadorAuditoriaNotificacao auditoria;
    @InjectMocks private NotificacaoService service;

    @Test
    void deveProcessarNotificacaoValida() {
        Notificacao n = new Notificacao("1", "User", "Ok", CanalNotificacao.EMAIL, 
                                        PrioridadeNotificacao.BAIXA, StatusNotificacao.PENDENTE, LocalDateTime.now());
        
        Notificacao resultado = service.processarNotificacao(n);
        
        assertEquals(StatusNotificacao.ENVIADO, resultado.status());
        verify(agendador).agendar(any());
        verify(auditoria).registrar(any());
    }
}