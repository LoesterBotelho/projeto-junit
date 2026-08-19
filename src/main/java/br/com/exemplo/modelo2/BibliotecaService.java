package br.com.exemplo.modelo2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BibliotecaService {

    private static final BigDecimal VALOR_MULTA_DIARIA = new BigDecimal("2.50");
    private static final int DIAS_MAXIMOS_EMPRESTIMO = 14;

    public LocalDate calcularDataDevolucaoPrevista(LocalDate dataEmpréstimo) {
        if (dataEmpréstimo == null) {
            throw new IllegalArgumentException("Data de empréstimo não pode ser nula.");
        }
        return dataEmpréstimo.plusDays(DIAS_MAXIMOS_EMPRESTIMO);
    }

    public BigDecimal calcularMultaAtraso(LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal) {
        if (dataDevolucaoPrevista == null || dataDevolucaoReal == null) {
            throw new IllegalArgumentException("As datas não podem ser nulas.");
        }

        if (!dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            return BigDecimal.ZERO;
        }

        long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
        return VALOR_MULTA_DIARIA.multiply(BigDecimal.valueOf(diasAtraso)).setScale(2, RoundingMode.HALF_EVEN);
    }

    public boolean validarElegibilidadeUsuario(int multasPendentes, boolean estaSuspenso, int livrosAtualmenteEmprestados) {
        if (estaSuspenso) {
            return false;
        }
        if (multasPendentes > 0) {
            return false;
        }
        return livrosAtualmenteEmprestados < 3; // Limite de 3 livros simultâneos
    }
}