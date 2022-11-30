package net.honey;


import java.util.Scanner;

public class PlayerManager {
    private Player player;

    public PlayerManager() {
        this.player = new Player(null);
    }

    // place a card onto field, or play a spell
    public void actionSummon() {
    }

    // get a new card from the deck , This implementation uses CLI.
    public void actionPickup(Deck one, Deck two) {
        Scanner scanner = new Scanner(System.in);
        DeckManager dm = new DeckManager(null);
        HandManager hm = new HandManager(player.getHand());
        boolean run = true;
        while (run) {
            System.out.println("What deck: 1, 2. any other to view size of each");
            switch (scanner.next()) {
                case "1": {
                    if (one.getSize() >= 1) {
                        dm.setDeck(one);
                        hm.addCard(dm.pickCard());
                        run = false;
                    } else {
                        System.out.println("Deck empty.");
                    }
                    break;
                }
                case "2": {
                    if (two.getSize() >= 1) {
                        dm.setDeck(two);
                        hm.addCard(dm.pickCard());
                        run = false;
                    } else {
                        System.out.println("Deck empty.");
                    }
                    break;
                }
                default: {
                    System.out.println("Deck 1 Size: " + one.getSize());
                    System.out.println("Deck 2 Size: " + two.getSize());
                    break;
                }
            }
        }
    }

    // attack a card, This is a CLI
    public int actionAttack(Player opponent) {
        boolean run = true;
        int deadCard = -1;
        Field ownField = player.getField();
        Field oppField = opponent.getField();
        FieldManager oppFM = new FieldManager(oppField);
        Scanner scanner = new Scanner(System.in);
        Field emptyField = new Field();
        while (run) {
            System.out.println("Select own card and opponents card to attack, if opponent has no cards select any");
            System.out.print("Own row");
            int mr = scanner.nextInt();
            System.out.println("Own col");
            int mc = scanner.nextInt();
            if (ownField.getPosition(mr, mc) != null) { // is the card you picked exist?
                if (oppFM.readField() != emptyField.getField()){ // is the opponent field empty?
                    System.out.println("Opp Row");
                    int or = scanner.nextInt();
                    System.out.println("Opp col");
                    int oc = scanner.nextInt();
                    if (oppField.getPosition(or, oc) != null) { // is card you selected to attack exist?
                        oppField.getPosition(or, oc).setRunningHealth(ownField.getPosition(mr, mc).getRunningAttack());
                        if (oppField.getPosition(or, oc).getRunningHealth() <=0) { // check if card is alive.
                            deadCard = oppFM.removeCard(or, oc);
                            System.out.println("Card destroyed");
                        } else {
                            System.out.println("Card damaged");
                        }
                        run = false;
                    } else {
                        System.out.println("No card in that position!, Try again");
                    }
                } else {
                    System.out.println("Attacking Directly:");
                    int damage;
                    opponent.setHealth(damage = ownField.getPosition(mr, mc).getRunningAttack());
                    if (opponent.getHealth()<=0) {
                        System.out.println("You hit the opponent for " + damage);
                    }
                }
            } else {
                System.out.println("No card on that position! Try again");
            }

        }
        return deadCard;
    }

    // skip your turn
    public void actionSkip() {
    }
    // Setter
    public void setPlayer(Player player) {
        this.player = player;
    }


}

