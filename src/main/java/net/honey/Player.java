package net.honey;

public class Player {
    private String name;
    private boolean myTurn;
    private int health;
    private Hand hand;
    private Field field;

    public Player(String name) {
        this.name = name;
        this.myTurn = false;
        this.health = 100;
        this.hand = new Hand();
        this.field = new Field();
    }
    // Getters
    public String getName() {
        return name;
    }
    public boolean isMyTurn() {
        return myTurn;
    }
    public int getHealth() {
        return health;
    }
    public Hand getHand() {
        return hand;
    }
    public Field getField() {
        return field;
    }
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }
    public void setField(Field field) {
        this.field = field;
    }
}
