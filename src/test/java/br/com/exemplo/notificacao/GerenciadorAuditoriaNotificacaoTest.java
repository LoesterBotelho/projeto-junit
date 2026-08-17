package br.com.exemplo.notificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GerenciadorAuditoriaNotificacaoTest {

    private GerenciadorAuditoriaNotificacao auditoria;

    @BeforeEach
    void setup() {
        auditoria = new GerenciadorAuditoriaNotificacao();
    }

    @Test
    void deveRegistrarNotificacaoNoHistorico() {
        Notificacao n = new Notificacao("1", "Usuario1", "Teste", CanalNotificacao.EMAIL, 
                                        PrioridadeNotificacao.BAIXA, StatusNotificacao.ENVIADO, LocalDateTime.now());
        
        auditoria.registrar(n);
        
        assertEquals(1, auditoria.getHistorico().size());
        assertEquals(1, auditoria.contarPorStatus(StatusNotificacao.ENVIADO));
    }

    @Test
    void naoDeveRegistrarNotificacaoNula() {
        auditoria.registrar(null);
        assertTrue(auditoria.getHistorico().isEmpty());
    }

    @Test
    void deveGerarLogsCorretamente() {
        Notificacao n = new Notificacao("10", "DestinatarioX", "Mensagem", CanalNotificacao.SMS, 
                                        PrioridadeNotificacao.ALTA, StatusNotificacao.FALHA, LocalDateTime.now());
        
        auditoria.registrar(n);
        List<String> logs = auditoria.getLogs();

        assertEquals(1, logs.size());
        assertTrue(logs.get(0).contains("10"));
        assertTrue(logs.get(0).contains("DestinatarioX"));
        assertTrue(logs.get(0).contains("FALHA"));
    }

    @Test
    void deveLimparHistorico() {
        Notificacao n = new Notificacao("1", "Usuario1", "Teste", CanalNotificacao.EMAIL, 
                                        PrioridadeNotificacao.BAIXA, StatusNotificacao.ENVIADO, LocalDateTime.now());
        auditoria.registrar(n);
        
        auditoria.limparHistorico();
        
        assertTrue(auditoria.getHistorico().isEmpty());
    }
}