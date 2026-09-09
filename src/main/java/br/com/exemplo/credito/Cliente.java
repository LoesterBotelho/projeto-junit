package br.com.exemplo.credito;

public record Cliente(
    String nome,
    int idade,
    double rendaMensal,
    int scoreCredito,
    boolean historicoInadimplencia,
    int mesesEmprego,
    double comprometimentoAtualRenda
) {}