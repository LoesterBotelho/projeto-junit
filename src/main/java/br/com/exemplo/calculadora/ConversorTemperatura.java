package br.com.exemplo.calculadora;

public class ConversorTemperatura {

	public double celsiusParaFahrenheit(double celsius) {

		return (celsius * 9 / 5) + 32;
	}

	public double fahrenheitParaCelsius(double fahrenheit) {

		return (fahrenheit - 32) * 5 / 9;
	}

	public double celsiusParaKelvin(double celsius) {

		return celsius + 273.15;
	}

	public double kelvinParaCelsius(double kelvin) {

		return kelvin - 273.15;
	}

}