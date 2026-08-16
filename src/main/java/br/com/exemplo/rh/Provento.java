package br.com.exemplo.rh;

public class Provento {
	private double horasExtras;
	private double adicionalInsalubridade;

	public Provento(double horasExtras, double adicionalInsalubridade) {
		if (horasExtras < 0 || adicionalInsalubridade < 0) {
			throw new IllegalArgumentException("Valores de proventos não podem ser negativos.");
		}
		this.horasExtras = horasExtras;
		this.adicionalInsalubridade = adicionalInsalubridade;
	}

	public double calcularTotalProventos(double salarioBase) {
		return salarioBase + horasExtras + adicionalInsalubridade;
	}

	public double getHorasExtras() {
		return horasExtras;
	}

	public double getAdicionalInsalubridade() {
		return adicionalInsalubridade;
	}
}