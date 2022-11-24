package net.honey;

import java.util.Arrays;
import java.util.Random;

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

    public void genDecks() {
        CardJsonReader cjr = new CardJsonReader("CardDef.json");
        Random random = new Random();
        int[] allCards = new int[cjr.getLength()];
        for (int i = 0; i < allCards.length; i++) {
            allCards[i] = cjr.getFromId(i).getId();
        }


    }
}
