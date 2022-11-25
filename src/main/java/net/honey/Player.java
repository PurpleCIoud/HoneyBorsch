package net.honey;

public class Player {
    private String name;
    private boolean myTurn;
    private int health;

    public Player(String name) {
        this.name = name;
        this.myTurn = false;
        this.health = 100;
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

    // Setters

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
