package br.com.exemplo.rh;

public class CalculadoraValeTransporteService {
	public double calcular(double salarioBase, double custoTotalVT) {
		double limiteMaximoDesconto = salarioBase * 0.06;
		return Math.min(custoTotalVT, limiteMaximoDesconto);
	}
}