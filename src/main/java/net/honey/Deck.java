package net.honey;
// This Class only makes a deck and has no logic of its own
@SuppressWarnings("unused")
public class Deck {
    private final int size;
    private int[] cardsIds;


    // Build an empty Deck given size
    public Deck(int size) {
        this.cardsIds = new int[size];
        this.size = size;
    }

    // Build a deck from given Cards
    public Deck(int[] cardsIds) {
        this.cardsIds = cardsIds;
        this.size = cardsIds.length;
    }

    // Getters
    public int getSize() {
        return size;
    }
    public int[] getCardsIds() {
        return cardsIds;
    }

    // Setters
    public void setCardsIds(int[] cardsIds) {
        this.cardsIds = cardsIds;
    }
}
