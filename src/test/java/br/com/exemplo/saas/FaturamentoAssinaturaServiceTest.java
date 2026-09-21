package br.com.exemplo.saas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FaturamentoAssinaturaServiceTest {

    private FaturamentoAssinaturaService service;

    @BeforeEach
    void setUp() {
        service = new FaturamentoAssinaturaService();
    }

    @Test
    @DisplayName("Deve calcular corretamente o valor de prorate no meio do ciclo de assinatura")
    void deveCalcularProrateCorretamenteNoMeioDoCiclo() {
    	
        Plano planoBasico = new Plano("Básico", new BigDecimal("100.00"));
        Plano planoPro = new Plano("Profissional", new BigDecimal("300.00"));

        // Ciclo de 1º de Setembro a 30 de Setembro (30 dias)
        LocalDate inicio = LocalDate.of(2026, 9, 1);
        LocalDate fim = LocalDate.of(2026, 9, 30);
        
        Assinatura assinatura = new Assinatura("Loester", planoBasico, inicio, fim);

        // Mudança no dia 16 de setembro (exatamente na metade: 15 dias passados, 15 dias restantes)
        LocalDate dataMudanca = LocalDate.of(2026, 9, 16);

        BigDecimal valorProrate = service.calcularProrateUpgrade(assinatura, planoPro, dataMudanca);

        // O valor esperado da diferença para os 15 dias restantes deve ser 100.00
        // (Plano Pro diário = 10 -> 15 dias = 150) - (Plano Básico diário = 3.3333 -> 15 dias = 50) = 100.00
        assertEquals(new BigDecimal("100.00"), valorProrate);
    }
}