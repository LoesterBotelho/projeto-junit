package br.com.exemplo.srs;

import java.time.LocalDate;

public class Flashcard {
    private String front;
    private String back;
    private int stage; // DIAS SRS
    private LocalDate nextReview;

    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
        this.stage = 0;
        this.nextReview = LocalDate.now();
    }

    public String getFront() { return front; }
    public String getBack() { return back; }
    public int getStage() { return stage; }
    public void setStage(int stage) { this.stage = stage; }
    public LocalDate getNextReview() { return nextReview; }
    public void setNextReview(LocalDate nextReview) { this.nextReview = nextReview; }
}