package br.com.exemplo.roman;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testando");

        for (int i = 1; i <= 3999; i++) {
            String romano = RomanNumeralConverter.toRoman(i);
            System.out.println(i + " = " + romano);
        }

        System.out.println("Fim do Programa");
    }
}