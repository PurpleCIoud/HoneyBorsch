package net.honey;

import java.util.ArrayList;

public class PlayArea {
    private Player player1;
    private Player player2;
    private Deck deck1;
    private Deck deck2;
    private Graveyard graveyard;
    private int turn;
    private ArrayList<ActionType> actionsList;
    private byte firstPlayer;

    public PlayArea() {
        this.player1 = new Player(null);
        this.player2 = new Player(null);
        this.deck1 = new Deck(new int[0]);
        this.deck2 = new Deck(new int[0]);
        this.graveyard = new Graveyard();
        this.turn = 0;
        this.actionsList = new ArrayList<>(0);
        this.firstPlayer = 0;
    }

    // Getters
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Deck getDeck1() {
        return deck1;
    }
    public Deck getDeck2() {
        return deck2;
    }
    public Graveyard getGraveyard() {
        return graveyard;
    }
    public int getTurn() {
        return turn;
    }
    public ArrayList<ActionType> getActionsList() {
        return actionsList;
    }
    public int getFirstPlayer() {
        return firstPlayer;
    }

    // Setters
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public void setDeck1(Deck deck1) {
        this.deck1 = deck1;
    }
    public void setDeck2(Deck deck2) {
        this.deck2 = deck2;
    }
    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public void setActionsList(ArrayList<ActionType> actionsList) {
        this.actionsList = actionsList;
    }
    public void setFirstPlayer(byte firstPlayer) {
        this.firstPlayer = firstPlayer;
    }
}
