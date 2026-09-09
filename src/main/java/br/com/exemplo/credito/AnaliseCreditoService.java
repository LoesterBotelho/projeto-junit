package br.com.exemplo.credito;

public class AnaliseCreditoService {

	public record ResultadoAnalise(boolean aprovado, double limiteAprovado, String motivo) {
	}

	public ResultadoAnalise analisar(Cliente cliente, double valorSolicitado, String tipoEmprestimo) {
		if (cliente.idade() < 18 || cliente.idade() > 80) {
			
			return new ResultadoAnalise(false, 0.0, "Idade fora da faixa permitida");
		}
		if (cliente.rendaMensal() <= 0) {
			return new ResultadoAnalise(false, 0.0, "Renda mensal inválida");
		}
		if (cliente.historicoInadimplencia()) {
			return new ResultadoAnalise(false, 0.0, "Histórico de inadimplência encontrado");
		}
		if (cliente.scoreCredito() < 300) {
			return new ResultadoAnalise(false, 0.0, "Score de crédito muito baixo");
		}
		if (cliente.mesesEmprego() < 6) {
			return new ResultadoAnalise(false, 0.0, "Tempo mínimo de emprego inferior a 6 meses");
		}
		if (cliente.comprometimentoAtualRenda() > 0.35) {
			return new ResultadoAnalise(false, 0.0, "Comprometimento de renda excede o limite de 35%");
		}

		double multiplicadorTipo = switch (tipoEmprestimo == null ? "PESSOAL" : tipoEmprestimo.toUpperCase()) {
		case "IMOBILIARIO" -> 3.0;
		case "VEICULO" -> 2.0;
		default -> 1.0;
		};

		double limiteBase = cliente.rendaMensal() * 0.4 * multiplicadorTipo;
		
		if (cliente.scoreCredito() >= 700) {
			limiteBase *= 2.0;
		} else if (cliente.scoreCredito() >= 500) {
			limiteBase *= 1.3;
		}

		if (valorSolicitado > limiteBase) {
			return new ResultadoAnalise(false, 0.0, "Valor solicitado acima do limite de crédito calculado");
		}

		return new ResultadoAnalise(true, limiteBase, "Crédito aprovado com sucesso");
	}
}