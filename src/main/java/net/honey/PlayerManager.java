package net.honey;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlayerManager {
    private Player player;

    public PlayerManager() {
        this.player = new Player(null);
    }

    // place a card onto field, or play a spell
    public void actionSummon() {
        Scanner scanner = new Scanner(System.in);
        HandManager hm = new HandManager(player.getHand());
        FieldManager fm = new FieldManager(player.getField());
        boolean run = player.getHand().getSize() != 0;
        while (run) {
            System.out.println("What card do you wish to summon?");
            for (CardPOJO card : hm.readHand()) {
                System.out.println(card.getId() + " : "+ card.getName());
            }
            int id = scanner.nextInt();
            for (int ids : player.getHand().getIds()) {
                if (id == ids) {
                    System.out.println("Where to summon");
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();
                    CardPOJO[][] allCards = fm.readField();
                    if (allCards[row][col] != null && allCards[row][col].getId() == 0) {
                        System.out.println("Card already exists here");
                        break;
                    } else {
                        fm.placeCard(id, row, col);
                        hm.removeCard(id);
                        System.out.println("Card placed!");
                        run = false;
                    }
                    break;
                }
            }
        }
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
        while (run) {
            System.out.println("Select own card and opponents card to attack, if opponent has no cards select any");
            System.out.println("Own row: ");
            int mr = scanner.nextInt();
            System.out.println("Own col: ");
            int mc = scanner.nextInt();
            // is the card you picked exist?
            if (ownField.getPosition(mr, mc) == null) {
                System.out.println("No card on that position");
                continue;
            }
            // is the opponent field empty? If so attack Directly.
            if (oppFM.isEmpty()){
                System.out.println("Attacking Directly:");
                int damage;
                opponent.setHealth(opponent.getHealth()- (damage = ownField.getPosition(mr, mc).getRunningAttack()));
                if (opponent.getHealth() > 0) {
                    System.out.println("You hit the opponent for " + damage);
                    System.out.println("Their HP is now: " + opponent.getHealth());
                } else {
                    System.out.println("your Opponent is defeated!");
                }
                run = false;
                continue;
            }
            // ask for opponents card to attack
            System.out.println("Opp Row: ");
            int or = scanner.nextInt();
            System.out.println("Opp col: ");
            int oc = scanner.nextInt();
            // is card you selected to attack exist?
            if (oppField.getPosition(or, oc) == null) {
                System.out.println("No card in that position!, Try again");
                continue;
            }
            oppField.getPosition(or, oc).setRunningHealth(
                    oppField.getPosition(or, oc).getRunningHealth()-ownField.getPosition(mr, mc).getRunningAttack());
            if (oppField.getPosition(or, oc).getRunningHealth() <=0) { // check if card is alive.
                deadCard = oppFM.removeCard(or, oc);
                System.out.println("Card destroyed");
            } else {
                System.out.println("Card damaged for: " + ownField.getPosition(mr, mc).getRunningAttack());
            }
            run = false;
        }
        return deadCard;
    }

    // skip your turn
    public ArrayList<ActionType> actionSkip(ArrayList<ActionType> actions) {
        return new ArrayList<>(0);
    }
    // Setter
    public void setPlayer(Player player) {
        this.player = player;
    }


}

