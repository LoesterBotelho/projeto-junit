package br.com.exemplo.notificacao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrioridadeNotificacaoTest {

	@Test
	void deveRetornarNiveisCorretos() {
		assertEquals(1, PrioridadeNotificacao.BAIXA.getNivel());
		assertEquals(2, PrioridadeNotificacao.MEDIA.getNivel());
		assertEquals(3, PrioridadeNotificacao.ALTA.getNivel());
		assertEquals(4, PrioridadeNotificacao.CRITICA.getNivel());
		assertEquals(5, PrioridadeNotificacao.URGENTE.getNivel());
	}

	@Test
	void deveIdentificarCorretamentePrioridadesUrgentes() {
		assertTrue(PrioridadeNotificacao.ALTA.isUrgente());
		assertTrue(PrioridadeNotificacao.CRITICA.isUrgente());

		assertFalse(PrioridadeNotificacao.BAIXA.isUrgente());
		assertFalse(PrioridadeNotificacao.MEDIA.isUrgente());
		
		// Nota: Pela regra atual da classe, apenas ALTA e		
		// CRITICA retornam verdadeiro para isUrgente()
		assertFalse(PrioridadeNotificacao.URGENTE.isUrgente()); 
																
	}
}