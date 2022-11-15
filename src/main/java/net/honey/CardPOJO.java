package net.honey;

public class CardPOJO {
    // initialise base variables
    private final String name;
    private final int baseHealth;
    private final int baseAttack;
    private final AttackType attackType;
    private int runningHealth;
    private int runningAttack;


    // Constructor
    public CardPOJO(int baseHealth, int baseAttack, AttackType attackType, String name) {
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.attackType = attackType;
        this.name = name;

        this.runningHealth = baseHealth;
        this.runningAttack = baseAttack;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getRunningAttack() {
        return runningAttack;
    }

    public int getRunningHealth() {
        return runningHealth;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void devAll() {
        System.out.println(""+runningAttack + runningHealth + baseAttack + baseHealth + attackType + name);
    }
    // Setters
    public void setRunningAttack(int runningAttack) {
        this.runningAttack = runningAttack;
    }

    public void setRunningHealth(int runningHealth) {
        this.runningHealth = runningHealth;
    }
}
