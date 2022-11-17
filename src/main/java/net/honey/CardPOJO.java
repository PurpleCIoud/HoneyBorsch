package net.honey;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class CardPOJO {
    // initialise base variables
    private final int id;
    private final String name;
    private final int baseHealth;
    private final int baseAttack;
    private final AttackType attackType;
    private final Image image;
    private int runningHealth;
    private int runningAttack;


    // Constructor
    public CardPOJO(int id, String name, int baseHealth, int baseAttack, AttackType attackType, Image image) {
        this.id = id;
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.attackType = attackType;
        this.image = image;


        this.runningHealth = baseHealth;
        this.runningAttack = baseAttack;
    }

    // Getters
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
    public AttackType getAttackType() {
        return attackType;
    }

    public Image getImage() {
        return image;
    }
    public int getRunningAttack() {
        return runningAttack;
    }

    public int getRunningHealth() {
        return runningHealth;
    }



    // Setters
    public void setRunningAttack(int runningAttack) {
        this.runningAttack = runningAttack;
    }

    public void setRunningHealth(int runningHealth) {
        this.runningHealth = runningHealth;
    }
}
