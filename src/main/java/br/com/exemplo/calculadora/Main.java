package br.com.exemplo.calculadora;

public class Main {

	public static void main(String[] args) {

		System.out.println("\nTESTANDO AS CLASSES DA CALCULADORA ");

		
		Bhaskara bhaskara = new Bhaskara();
		System.out.println("\n--- Bhaskara ---");
		System.out.println("Delta (1, -5, 6): " + bhaskara.calcularDelta(1, -5, 6));
		System.out.println("X1 (1, -5, 6): " + bhaskara.calcularX1(1, -5, 6));
		System.out.println("X2 (1, -5, 6): " + bhaskara.calcularX2(1, -5, 6));

		
		Calculadora calc = new Calculadora();
		System.out.println("\n--- Calculadora Básica ---");
		System.out.println("Soma (10 + 5): " + calc.somar(10, 5));
		System.out.println("Subtração (10 - 5): " + calc.subtrair(10, 5));
		System.out.println("Multiplicação (10 * 5): " + calc.multiplicar(10, 5));
		System.out.println("Divisão (10 / 2): " + calc.dividir(10, 2));

		
		CalculadoraDesconto calcDesconto = new CalculadoraDesconto();
		System.out.println("\n--- Calculadora de Desconto ---");
		System.out.println("Desconto de 20% sobre 200: " + calcDesconto.calcularDesconto(200, 20));
		System.out.println("Valor final com 20% de desconto sobre 200: " + calcDesconto.valorFinal(200, 20));

		
		CalculadoraIMC calcImc = new CalculadoraIMC();
		System.out.println("\n--- Calculadora IMC ---");
		double imcVal = calcImc.calcular(70, 1.75);
		System.out.println("IMC (Peso: 70, Altura: 1.75): " + imcVal);
		System.out.println("Classificação: " + calcImc.classificacao(imcVal));

		
		CalculadoraJuros calcJuros = new CalculadoraJuros();
		System.out.println("\n--- Calculadora de Juros ---");
		System.out.println("Juros Simples (C: 1000, Taxa: 0.05, Tempo: 2): " + calcJuros.jurosSimples(1000, 0.05, 2));
		System.out.println("Juros Compostos (C: 1000, Taxa: 0.05, Tempo: 2): " + calcJuros.jurosCompostos(1000, 0.05, 2));


		ConversorTemperatura conversor = new ConversorTemperatura();
		System.out.println("\n--- Conversor de Temperatura ---");
		System.out.println("25ºC para Fahrenheit: " + conversor.celsiusParaFahrenheit(25));
		System.out.println("77ºF para Celsius: " + conversor.fahrenheitParaCelsius(77));
		System.out.println("25ºC para Kelvin: " + conversor.celsiusParaKelvin(25));
		System.out.println("298.15K para Celsius: " + conversor.kelvinParaCelsius(298.15));

		
		Estatistica estatistica = new Estatistica();
		int[] dadosEstatistica = { 2, 4, 6, 8, 10 };
		System.out.println("\n--- Estatística ---");
		System.out.println("Média: " + estatistica.media(dadosEstatistica));
		System.out.println("Maior: " + estatistica.maior(dadosEstatistica));
		System.out.println("Menor: " + estatistica.menor(dadosEstatistica));
		System.out.println("Mediana: " + estatistica.mediana(dadosEstatistica));

		
		ValidadorCPF validadorCpf = new ValidadorCPF();
		System.out.println("\n--- Validador de CPF ---");
		String cpfTeste = "12345678909";
		System.out.println("O CPF " + cpfTeste + " é válido? " + validadorCpf.validar(cpfTeste));


		VerificadorNumero verificador = new VerificadorNumero();
		System.out.println("\n--- Verificador de Número (-4) ---");
		System.out.println("É positivo? " + verificador.positivo(-4));
		System.out.println("É negativo? " + verificador.negativo(-4));
		System.out.println("É zero? " + verificador.zero(-4));
		System.out.println("É par? " + verificador.par(-4));
		System.out.println("É ímpar? " + verificador.impar(-4));


		Vetores vetores = new Vetores();
		int[] meuVetor = { 10, 20, 30, 40, 50 };
		System.out.println("\n--- Vetores ---");
		System.out.println("Soma dos elementos: " + vetores.soma(meuVetor));
		System.out.println("Maior elemento: " + vetores.maior(meuVetor));
		System.out.println("Menor elemento: " + vetores.menor(meuVetor));
		System.out.println("Média dos elementos: " + vetores.media(meuVetor));
		System.out.println("Tamanho do vetor: " + vetores.tamanho(meuVetor));

	}
}