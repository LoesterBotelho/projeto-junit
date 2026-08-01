package br.com.exemplo.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConversorTemperaturaTest {

	private ConversorTemperatura conversor;

	@BeforeEach
	void iniciar() {

		conversor = new ConversorTemperatura();

	}

	@Test
	@DisplayName("Deve converter Celsius para Fahrenheit")
	void deveConverterCelsiusParaFahrenheit() {

		double resultado = conversor.celsiusParaFahrenheit(0);

		assertEquals(32, resultado, 0.01);

	}

	@Test
	@DisplayName("Deve converter Fahrenheit para Celsius")
	void deveConverterFahrenheitParaCelsius() {

		double resultado = conversor.fahrenheitParaCelsius(32);

		assertEquals(0, resultado, 0.01);

	}

	@Test
	@DisplayName("Deve converter Celsius para Kelvin")
	void deveConverterCelsiusParaKelvin() {

		double resultado = conversor.celsiusParaKelvin(0);

		assertEquals(273.15, resultado, 0.01);

	}

	@Test
	@DisplayName("Deve converter Kelvin para Celsius")
	void deveConverterKelvinParaCelsius() {

		double resultado = conversor.kelvinParaCelsius(273.15);

		assertEquals(0, resultado, 0.01);

	}

}