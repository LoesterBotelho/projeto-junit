package br.com.exemplo.srs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SRSManager {
    private List<Flashcard> deck;
    private List<String> history;
    private Scanner scanner; // Scanner injetado

    // Injeção de Dependência via Construtor
    public SRSManager(List<Flashcard> deckInicial, Scanner scanner) {
        this.deck = deckInicial;
        this.history = new ArrayList<>();
        this.scanner = scanner;
    }

    public void iniciarSessao() {
        System.out.println("ANKI DEC SRS");

        for (Flashcard fc : deck) {
            if (fc.getNextReview().isAfter(LocalDate.now())) {
                continue;
            }

            System.out.println("\n[Frente]: " + fc.getFront());
            System.out.println("Pressione Enter para ver o verso...");
            scanner.nextLine();
            
            System.out.println("[Verso]: " + fc.getBack());
            System.out.print("Você acertou? (s/n): ");
            String choice = scanner.nextLine();

            boolean correct = choice.equalsIgnoreCase("s");
            String result = correct ? "Acertou" : "Errou";
            
            updateCard(fc, correct);
            
            String log = "Card: [" + fc.getFront() + "] | Resultado: " + result + " | Novo Estágio: " + fc.getStage();
            history.add(log);
            System.out.println(log);
        }
        
        System.out.println("\nFIM DO ESTUDO");
        System.out.println("Total de execuções registradas no histórico: " + history.size());
    }

    private void updateCard(Flashcard fc, boolean correct) {
        if (correct) {
            fc.setStage(Math.min(fc.getStage() + 1, 7));
        } else {
            fc.setStage(0);
        }

        int daysToAdd = switch (fc.getStage()) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 7;
            case 4 -> 30;
            case 5 -> 90;
            case 6 -> 180;
            case 7 -> 365;
            default -> 1;
        };
        fc.setNextReview(LocalDate.now().plusDays(daysToAdd));
    }
}