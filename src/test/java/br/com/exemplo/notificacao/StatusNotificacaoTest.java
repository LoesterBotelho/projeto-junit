package br.com.exemplo.notificacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusNotificacaoTest {

    @Test
    void deveConterTodosOsEstadosEsperados() {
        StatusNotificacao[] statusList = StatusNotificacao.values();
        
        boolean possuiPendente = false;
        boolean possuiEnviado = false;
        boolean possuiFalha = false;

        for (StatusNotificacao status : statusList) {
            if (status == StatusNotificacao.PENDENTE) possuiPendente = true;
            if (status == StatusNotificacao.ENVIADO) possuiEnviado = true;
            if (status == StatusNotificacao.FALHA) possuiFalha = true;
        }

        assertTrue(possuiPendente);
        assertTrue(possuiEnviado);
        assertTrue(possuiFalha);
    }
}