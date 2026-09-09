package br.com.exemplo.credito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AnaliseCreditoServiceTest {

    private AnaliseCreditoService service;

    @BeforeEach
    void setUp() {
        service = new AnaliseCreditoService();
    }

    @ParameterizedTest(name = "Idade {0} deve ser rejeitada")
    @CsvSource({ "17", "81", "10", "90" })
    void deveRejeitarPorIdadeInvalida(int idade) {
        var cliente = new Cliente("Teste", idade, 5000.0, 700, false, 12, 0.2);
        var resultado = service.analisar(cliente, 1000.0, "PESSOAL");
        assertFalse(resultado.aprovado());
        assertEquals("Idade fora da faixa permitida", resultado.motivo());
    }

    @ParameterizedTest(name = "Renda {0} deve ser rejeitada")
    @CsvSource({ "0.0", "-100.0" })
    void deveRejeitarPorRendaInvalida(double renda) {
        var cliente = new Cliente("Teste", 30, renda, 700, false, 12, 0.2);
        var resultado = service.analisar(cliente, 1000.0, "PESSOAL");
        assertFalse(resultado.aprovado());
        assertEquals("Renda mensal inválida", resultado.motivo());
    }

    @Test
    @DisplayName("Deve rejeitar cliente com histórico de inadimplência")
    void deveRejeitarPorInadimplencia() {
        var cliente = new Cliente("Teste", 30, 5000.0, 700, true, 12, 0.2);
        var resultado = service.analisar(cliente, 1000.0, "PESSOAL");
        assertFalse(resultado.aprovado());
        assertEquals("Histórico de inadimplência encontrado", resultado.motivo());
    }

    @ParameterizedTest(name = "Score {0} deve ser rejeitado")
    @CsvSource({ "299", "200", "0" })
    void deveRejeitarPorScoreBaixo(int score) {
        var cliente = new Cliente("Teste", 30, 5000.0, score, false, 12, 0.2);
        var resultado = service.analisar(cliente, 1000.0, "PESSOAL");
        assertFalse(resultado.aprovado());
        assertEquals("Score de crédito muito baixo", resultado.motivo());
    }

    @ParameterizedTest(name = "Meses de emprego {0} deve ser rejeitado")
    @CsvSource({ "0", "5" })
    void deveRejeitarPorTempoEmpregoInsuficiente(int meses) {
        var cliente = new Cliente("Teste", 30, 5000.0, 700, false, meses, 0.2);
        var resultado = service.analisar(cliente, 1000.0, "PESSOAL");
        assertFalse(resultado.aprovado());
        assertEquals("Tempo mínimo de emprego inferior a 6 meses", resultado.motivo());
    }

    @ParameterizedTest(name = "Comprometimento de renda {0} deve ser rejeitado")
    @CsvSource({ "0.36", "0.50", "1.0" })
    void deveRejeitarPorComprometimentoExcedido(double comprometimento) {
        var cliente = new Cliente("Teste", 30, 5000.0, 700, false, 12, comprometimento);
        var resultado = service.analisar(cliente, 1000.0, "PESSOAL");
        assertFalse(resultado.aprovado());
        assertEquals("Comprometimento de renda excede o limite de 35%", resultado.motivo());
    }

    @Test
    @DisplayName("Deve tratar tipo de empréstimo nulo ou desconhecido como padrão PESSOAL")
    void deveTratarTipoEmprestimoPadrao() {
        var cliente = new Cliente("Teste", 30, 5000.0, 600, false, 12, 0.2);
        
        var resultadoNulo = service.analisar(cliente, 1000.0, null);
        var resultadoDesconhecido = service.analisar(cliente, 1000.0, "DESCONHECIDO");

        assertTrue(resultadoNulo.aprovado());
        assertTrue(resultadoDesconhecido.aprovado());
    }

    @ParameterizedTest(name = "Cliente IDADE={0}, RENDA={1}, SCORE={2} -> Aprovado={3}")
    @CsvSource({
        "25,  5000.0, 750, 4000.0, true",
        "40, 10000.0, 650, 5000.0, true",
        "19,  2000.0, 400,  500.0, true",
        "75,  8000.0, 720, 5000.0, true"
    })
    void deveValidarMultiplosPerfisDeClientesComSucesso(int idade, double renda, int score, double solicitado, boolean esperado) {
        var cliente = new Cliente("Teste", idade, renda, score, false, 12, 0.1);
        var resultado = service.analisar(cliente, solicitado, "PESSOAL");
        assertEquals(esperado, resultado.aprovado());
    }

    @ParameterizedTest(name = "Modalidade {0} com Solicitação {1} -> Aprovado={2}")
    @CsvSource({
        "PESSOAL,     2000.0, true",
        "VEICULO,     4000.0, true",
        "IMOBILIARIO, 9000.0, true",
        "IMOBILIARIO, 15000.0, false"
    })
    void deveValidarLimitesPorModalidadeDeEmprestimo(String modalidade, double solicitado, boolean esperado) {
        var cliente = new Cliente("Ana", 30, 5000.0, 700, false, 24, 0.2);
        var resultado = service.analisar(cliente, solicitado, modalidade);
        assertEquals(esperado, resultado.aprovado());
    }
}