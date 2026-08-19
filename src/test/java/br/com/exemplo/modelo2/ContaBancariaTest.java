package br.com.exemplo.modelo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    private ContaBancaria conta;

    @BeforeEach
    void setUp() {
        conta = new ContaBancaria("12345-6", "Ana Souza", new BigDecimal("500.00"), new BigDecimal("200.00"));
    }

    @Test
    @DisplayName("Deve criar conta bancária com parâmetros válidos com sucesso")
    void deveCriarContaComSucesso() {
        assertAll("Verificando dados iniciais",
            () -> assertEquals("12345-6", conta.getNumeroConta()),
            () -> assertEquals("Ana Souza", conta.getTitular()),
            () -> assertEquals(0, new BigDecimal("500.00").compareTo(conta.getSaldo())),
            () -> assertEquals(0, new BigDecimal("200.00").compareTo(conta.getLimiteChequeEspecial())),
            () -> assertTrue(conta.isAtiva()),
            () -> assertFalse(conta.getHistoricoTransacoes().isEmpty())
        );
    }

    @ParameterizedTest(name = "Depósito de {0} deve resultar em saldo {1}")
    @CsvSource({
        "100.00, 600.00",
        "50.50, 550.50",
        "1000.00, 1500.00"
    })
    void deveDepositarValoresComSucesso(BigDecimal valorDeposito, BigDecimal saldoEsperado) {
        conta.depositar(valorDeposito);
        assertEquals(0, saldoEsperado.compareTo(conta.getSaldo()));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar depositar valores nulos ou negativos")
    void deveFalharDepositoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(new BigDecimal("-50.00")));
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(null));
    }

    @Test
    @DisplayName("Deve realizar saque utilizando apenas o saldo disponível")
    void deveSacarComSaldoDisponivel() {
        conta.sacar(new BigDecimal("200.00"));
        assertEquals(0, new BigDecimal("300.00").compareTo(conta.getSaldo()));
    }

    @Test
    @DisplayName("Deve realizar saque utilizando parte do cheque especial")
    void deveSacarUsandoChequeEspecial() {
        conta.sacar(new BigDecimal("600.00")); // Usa 500 do saldo + 100 do limite
        assertEquals(0, new BigDecimal("-100.00").compareTo(conta.getSaldo()));
    }

    @Test
    @DisplayName("Deve falhar ao tentar sacar valor superior ao saldo somado ao cheque especial")
    void deveFalharSaqueAcimaDoLimite() {
        assertThrows(IllegalStateException.class, () -> conta.sacar(new BigDecimal("800.00")));
    }

    @Test
    @DisplayName("Deve transferir valores sem taxa entre contas com sucesso")
    void deveTransferirSemTaxa() {
        ContaBancaria destino = new ContaBancaria("98765-4", "Carlos Silva", new BigDecimal("100.00"), BigDecimal.ZERO);
        
        conta.transferir(new BigDecimal("300.00"), destino);

        assertEquals(0, new BigDecimal("200.00").compareTo(conta.getSaldo()));
        assertEquals(0, new BigDecimal("400.00").compareTo(destino.getSaldo()));
    }

    @Test
    @DisplayName("Deve transferir com taxa de 1% para valores acima de R$ 1000.00")
    void deveTransferirComTaxa() {
        ContaBancaria contaRica = new ContaBancaria("11111-1", "Empresa X", new BigDecimal("5000.00"), BigDecimal.ZERO);
        ContaBancaria destino = new ContaBancaria("22222-2", "Fornecedor Y", new BigDecimal("0.00"), BigDecimal.ZERO);

        contaRica.transferir(new BigDecimal("2000.00"), destino);

        assertEquals(0, new BigDecimal("2980.00").compareTo(contaRica.getSaldo()));
        assertEquals(0, new BigDecimal("2000.00").compareTo(destino.getSaldo()));
    }

    @Test
    @DisplayName("Deve impedir transferência para a mesma conta ou conta inativa")
    void deveFalharTransferenciasInvalidas() {
        ContaBancaria contaInativa = new ContaBancaria("33333-3", "Inativo", BigDecimal.ZERO, BigDecimal.ZERO);
        contaInativa.desativarConta();

        assertThrows(IllegalArgumentException.class, () -> conta.transferir(new BigDecimal("100.00"), conta));
        assertThrows(IllegalArgumentException.class, () -> conta.transferir(new BigDecimal("100.00"), contaInativa));
    }

    @Test
    @DisplayName("Deve permitir desativar a conta apenas quando o saldo for exatamente zero")
    void deveGerenciarEncerramentoDeConta() {
        conta.sacar(new BigDecimal("500.00")); 
        assertEquals(0, BigDecimal.ZERO.compareTo(conta.getSaldo()));

        assertDoesNotThrow(() -> conta.desativarConta());
        assertFalse(conta.isAtiva());

        assertThrows(IllegalStateException.class, () -> conta.depositar(new BigDecimal("50.00")));
    }
}