package br.com.exemplo.notificacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CanalNotificacaoTest {

    @Test
    void deveValidarLimitesDeCaracteresCorretos() {
        assertEquals(10000, CanalNotificacao.EMAIL.getLimiteCaracteres());
        assertEquals(160, CanalNotificacao.SMS.getLimiteCaracteres());
        assertEquals(256, CanalNotificacao.PUSH.getLimiteCaracteres());
        assertEquals(4096, CanalNotificacao.WHATSAPP.getLimiteCaracteres());
    }

    @Test
    void deveRetornarFalsoParaTextoNuloNoSuporteDeTamanho() {
        assertFalse(CanalNotificacao.SMS.suportaTamanho(null));
    }

    @Test
    void deveSuportarTextoDentroDoLimiteDoCanal() {
        String textoPequeno = "Oi";
        assertTrue(CanalNotificacao.SMS.suportaTamanho(textoPequeno));
    }

    @Test
    void naoDeveSuportarTextoAcimaDoLimiteDoCanal() {
        String textoLongo = "A".repeat(161);
        assertFalse(CanalNotificacao.SMS.suportaTamanho(textoLongo));
    }
}