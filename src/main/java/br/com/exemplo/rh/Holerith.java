package br.com.exemplo.rh;

public class Holerith {
	private Funcionario funcionario;
	private Provento provento;
	private Desconto desconto;
	private double custoValeTransporte;

	private final CalculadoraINSSService inssService = new CalculadoraINSSService();
	private final CalculadoraIRRFService irrfService = new CalculadoraIRRFService();
	private final CalculadoraValeTransporteService vtService = new CalculadoraValeTransporteService();

	public Holerith(Funcionario funcionario, Provento provento, Desconto desconto, double custoValeTransporte) {
		this.funcionario = funcionario;
		this.provento = provento;
		this.desconto = desconto;
		this.custoValeTransporte = custoValeTransporte;
	}

	public double getSalarioBruto() {
		return provento.calcularTotalProventos(funcionario.getSalarioBase());
	}

	public double getValorINSS() {
		return inssService.calcular(getSalarioBruto());
	}

	public double getValorIRRF() {
		return irrfService.calcular(getSalarioBruto(), getValorINSS(), funcionario.getDependentes());
	}

	public double getValorValeTransporte() {
		return vtService.calcular(funcionario.getSalarioBase(), custoValeTransporte);
	}

	public double getTotalDescontos() {
		return getValorINSS() + getValorIRRF() + getValorValeTransporte() + desconto.getTotalOutrosDescontos();
	}

	public double getSalarioLiquido() {
		return getSalarioBruto() - getTotalDescontos();
	}
}