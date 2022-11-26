package net.honey;

import java.util.Random;

public class PlayArea {
    private Player player1;
    private Player player2;
    private Hand player1Hand;
    private Hand player2Hand;
    private Deck deck1;
    private Deck deck2;
    private Field player1Field;
    private Field player2Field;
    private Graveyard graveyard;
    private int turn;
    private int stageInTurn;
    private byte firstPlayer;

    public PlayArea() {
        this.player1 = new Player(null);
        this.player2 = new Player(null);
        this.player1Hand = new Hand();
        this.player2Hand = new Hand();
        this.deck1 = new Deck(new int[0]);
        this.deck2 = new Deck(new int[0]);
        this.player1Field = new Field();
        this.player2Field = new Field();
        this.graveyard = new Graveyard();
        this.turn = 0;
        this.stageInTurn = 0;
        this.firstPlayer = determineFirst();
        genDecks();
    }

    // Generates a shuffled deck from scratch, without this the game will not function
    protected void genDecks() {
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

    private byte determineFirst() {
        Random r = new Random();
        return (byte) r.nextInt(0,1);
    }

    // Getters
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Hand getPlayer1Hand() {
        return player1Hand;
    }
    public Hand getPlayer2Hand() {
        return player2Hand;
    }
    public Deck getDeck1() {
        return deck1;
    }
    public Deck getDeck2() {
        return deck2;
    }
    public Field getPlayer1Field() {
        return player1Field;
    }
    public Field getPlayer2Field() {
        return player2Field;
    }
    public Graveyard getGraveyard() {
        return graveyard;
    }
    public int getTurn() {
        return turn;
    }
    public int getStageInTurn() {
        return stageInTurn;
    }

    // Setters
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public void setPlayer1Hand(Hand player1Hand) {
        this.player1Hand = player1Hand;
    }
    public void setPlayer2Hand(Hand player2Hand) {
        this.player2Hand = player2Hand;
    }
    public void setDeck1(Deck deck1) {
        this.deck1 = deck1;
    }
    public void setDeck2(Deck deck2) {
        this.deck2 = deck2;
    }
    public void setPlayer1Field(Field player1Field) {
        this.player1Field = player1Field;
    }
    public void setPlayer2Field(Field player2Field) {
        this.player2Field = player2Field;
    }
    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public void setStageInTurn(int stageInTurn) {
        this.stageInTurn = stageInTurn;
    }
}
