package net.honey;

public class DeckManager {
    private Deck deck;
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

    // Shuffle the deck
    public void shuffleDeck() {
        ArrayShuffler shuffler = new ArrayShuffler();
        deck.setIds(shuffler.shuffle(deck.getIds()));
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Deck getDeck() {
        return deck;
    }
}
