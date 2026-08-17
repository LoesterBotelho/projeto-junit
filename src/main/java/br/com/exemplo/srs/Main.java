package br.com.exemplo.srs;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            
            List<Flashcard> cardsIniciais = DeckDataSource.carregarCardsIniciais();

            SRSManager manager = new SRSManager(cardsIniciais, scanner);

            manager.iniciarSessao();
            
        }
    }
}