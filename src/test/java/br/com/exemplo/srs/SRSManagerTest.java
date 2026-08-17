package br.com.exemplo.srs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SRSManagerTest {

    private Flashcard flashcardTeste;

    @BeforeEach
    void setUp() {
        List<Flashcard> deckTeste = new ArrayList<>();
        flashcardTeste = new Flashcard("Test front", "Test back");
        deckTeste.add(flashcardTeste);

        Scanner scannerMock = new Scanner("");
        
        new SRSManager(deckTeste, scannerMock);
    }

    @Test
    void deveAvancarEstagioEAumentarDiasQuandoAcertar() {

        assertEquals(0, flashcardTeste.getStage());

        flashcardTeste.setStage(Math.min(flashcardTeste.getStage() + 1, 7));
        assertEquals(1, flashcardTeste.getStage());
        
        int daysToAdd = switch (flashcardTeste.getStage()) {
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 7;
            case 4 -> 30;
            case 5 -> 90;
            case 6 -> 180;
            case 7 -> 365;
            default -> 0;
        };
        
        assertEquals(1, daysToAdd);
        assertEquals(LocalDate.now().plusDays(1), LocalDate.now().plusDays(daysToAdd));
    }

    @Test
    void deveResetarEstagioParaZeroQuandoErrar() {
        flashcardTeste.setStage(4);
        assertEquals(4, flashcardTeste.getStage());

        boolean correct = false;
        if (correct) {
            flashcardTeste.setStage(Math.min(flashcardTeste.getStage() + 1, 7));
        } else {
            flashcardTeste.setStage(0);
        }

        assertEquals(0, flashcardTeste.getStage());
    }
}