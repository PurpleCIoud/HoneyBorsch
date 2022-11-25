package net.honey;

public class PlayArea {
    private Hand playerHand;
    private Hand opponentHand;
    private Deck deck1;
    private Deck deck2;
    private Field playerField;
    private Field opponentField;
    private Graveyard graveyard;

    public PlayArea() {
        this.playerHand = new Hand();
        this.opponentHand = new Hand();
        this.deck1 = new Deck(new int[0]);
        this.deck2 = new Deck(new int[0]);
        this.playerField = new Field();
        this.opponentField = new Field();
        this.graveyard = new Graveyard();
    }

    // Generates a shuffled deck from scratch
    public void genDecks() {
        CardJsonReader cjr = new CardJsonReader("CardDef.json");
        ArrayShuffler shuffler = new ArrayShuffler();
        int[] full = cjr.getIds();
        full = shuffler.shuffle(full);
        int length = cjr.getLength();
        int firstHalf = length/2;
        int secondHalf = length - firstHalf;
        int[] half1 = new int[firstHalf];
        int[] half2 = new int[secondHalf];
        System.arraycopy(full, 0, half1, 0, firstHalf);
        System.arraycopy(full, firstHalf+1, half2, 0, secondHalf);
        this.deck1.setIds(half1);
        this.deck2.setIds(half2);
    }
}
