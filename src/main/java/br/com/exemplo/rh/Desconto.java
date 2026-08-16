package br.com.exemplo.rh;

public class Desconto {
	private double faltas;
	private double adiantamentoSalarial;

	public Desconto(double faltas, double adiantamentoSalarial) {
		if (faltas < 0 || adiantamentoSalarial < 0) {
			throw new IllegalArgumentException("Valores de descontos não podem ser negativos.");
		}
		this.faltas = faltas;
		this.adiantamentoSalarial = adiantamentoSalarial;
	}

	public double getTotalOutrosDescontos() {
		return faltas + adiantamentoSalarial;
	}

	public double getFaltas() {
		return faltas;
	}

	public double getAdiantamentoSalarial() {
		return adiantamentoSalarial;
	}
}