package br.com.exemplo.roman;

import java.util.TreeMap;
import java.util.Map;

public class RomanNumeralConverter {

	private static final TreeMap<Integer, String> ROMAN_MAP = new TreeMap<>();

	static {
		ROMAN_MAP.put(1000, "M");
		ROMAN_MAP.put(900, "CM");
		ROMAN_MAP.put(500, "D");
		ROMAN_MAP.put(400, "CD");
		ROMAN_MAP.put(100, "C");
		ROMAN_MAP.put(90, "XC");
		ROMAN_MAP.put(50, "L");
		ROMAN_MAP.put(40, "XL");
		ROMAN_MAP.put(10, "X");
		ROMAN_MAP.put(9, "IX");
		ROMAN_MAP.put(5, "V");
		ROMAN_MAP.put(4, "IV");
		ROMAN_MAP.put(1, "I");
	}

	public static String toRoman(int number) {
		if (number < 1 || number > 3999) {
			throw new IllegalArgumentException("Número fora do intervalo permitido (1-3999)");
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, String> entry : ROMAN_MAP.descendingMap().entrySet()) {
			while (number >= entry.getKey()) {
				sb.append(entry.getValue());
				number -= entry.getKey();
			}
		}
		return sb.toString();
	}
}