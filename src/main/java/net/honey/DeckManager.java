package net.honey;

public class DeckManager {
    private final Deck deck;
    public DeckManager(Deck deck) {
        this.deck = deck;
    }

    public int pickCard() {
        int[] oldIds = deck.getIds();
        int[] newIds = new int[deck.getSize()-1];
        System.arraycopy(oldIds, 0, newIds, 0, oldIds.length - 1);
        deck.setIds(newIds);
        return oldIds[deck.getSize()-1];
    }
    // This will be dealt with later as I don't have enough cards to build a deck yet
    public void buildDeck() {
    }
}
