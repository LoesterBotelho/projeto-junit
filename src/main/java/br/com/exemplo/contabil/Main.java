package br.com.exemplo.contabil;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("ESCRITURACAO CONTABIL");

        ContaContabil contaBanco = new ContaContabil("1.1.1.01", "Banco Conta Movimento", TipoNaturezaConta.DEVEDORA, true);
        ContaContabil contaCapital = new ContaContabil("2.1.1.01", "Capital Social", TipoNaturezaConta.CREDORA, true);

        CentroCusto ccMatriz = new CentroCusto("001", "Matriz");

        LancamentoContabil lancamento = new LancamentoContabil("L001", LocalDate.now(), "Aporte de Capital Social");
        lancamento.adicionarItem(new ItemLancamento(contaBanco, ccMatriz, new BigDecimal("10000.00"), TipoLancamentoItem.DEBITO));
        lancamento.adicionarItem(new ItemLancamento(contaCapital, new BigDecimal("10000.00"), TipoLancamentoItem.CREDITO));

        ValidadorPartidasDobradas validadorPartidas = new ValidadorPartidasDobradas();
        ValidadorPlanoContas validadorPlano = new ValidadorPlanoContas();
        EscrituradorContabil escriturador = new EscrituradorContabil(validadorPartidas, validadorPlano);

        try {
            escriturador.escriturar(lancamento);
            System.out.println("Sucesso! Lançamento " + lancamento.getId() + " escriturado com sucesso.");
            System.out.println("Histórico: " + lancamento.getHistorico());
        } catch (Exception e) {
            System.err.println("Erro na escrituração: " + e.getMessage());
        }
    }
}