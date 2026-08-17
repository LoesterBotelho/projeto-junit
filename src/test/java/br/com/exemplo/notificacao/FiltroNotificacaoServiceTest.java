package br.com.exemplo.notificacao;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class FiltroNotificacaoServiceTest {

    @Test
    void deveFiltrarPorPrioridade() {
        Notificacao n1 = new Notificacao("1", "U1", "C1", CanalNotificacao.EMAIL, PrioridadeNotificacao.ALTA, StatusNotificacao.PENDENTE, LocalDateTime.now());
        Notificacao n2 = new Notificacao("2", "U2", "C2", CanalNotificacao.EMAIL, PrioridadeNotificacao.BAIXA, StatusNotificacao.PENDENTE, LocalDateTime.now());
        
        List<Notificacao> filtradas = FiltroNotificacaoService.filtrarPorPrioridade(List.of(n1, n2), PrioridadeNotificacao.ALTA);
        
        assertEquals(1, filtradas.size());
        assertEquals("1", filtradas.get(0).id());
    }
}