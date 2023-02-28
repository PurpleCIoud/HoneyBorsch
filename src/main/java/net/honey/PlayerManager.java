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
    public boolean actionSummon() {
        boolean refund = false;
        ValidInt vi = new ValidInt();
        HandManager hm = new HandManager(player.getHand());
        FieldManager fm = new FieldManager(player.getField());
        boolean run = true;
        while (run) {
            // check if there are cards in the hand
            if (player.getHand().getSize() == 0) {
                System.out.println("You cant play a card from an empty hand!");
                refund = true;
                run = false;
                continue;
            }
            // Show player what cards they can play
            System.out.println("What card do you wish to summon?");
            for (CardPOJO card : hm.readHand()) {
                System.out.println(card.getId() + " : "+ card.getName());
            }
            int id = vi.nextInt();

            // validate input
            if (!vi.valid(id)) {
                continue;
            }
            // internal loop for summoning
            int ids;
            for (int i = 0; i < player.getHand().getSize(); i++) {
                ids = player.getHand().getIds()[i];
                if (ids != id) {
                    continue;
                }
                System.out.println("Where to summon");
                System.out.println("Row: ");
                int row = vi.nextInt(3);
                System.out.println("Column: ");
                int col = vi.nextInt(3);
                // validate Input
                if (!vi.valid(row, col)) {
                    System.out.println("Invalid row or column! Try again");
                    break;
                }
                // check if card exists on that location
                CardPOJO[][] allCards = fm.readField();
                if (allCards[row][col] != null && allCards[row][col].getId() != -1) {
                    System.out.println("Card already exists here");
                    refund = true;
                } else {
                    fm.placeCard(id, row, col);
                    hm.removeCard(id);
                    System.out.println("Card placed!");
                }
                run = false;
            }
        }
        return refund;
    }



    // get a new card from the deck , This implementation uses CLI.
    // @TODO Make selecting from decks more nicer
    public boolean actionPickup(Deck one, Deck two) {
        boolean refund = false;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            if (one.getSize() == 0 && two.getSize() == 0) {
                run = false;
                refund = true;
                continue;
            }
            System.out.println("What deck: 1, 2. any other to view size of each");
            switch (scanner.next()) {
                case "1": {
                    run = deckSizeValidator(one);
                    break;
                }
                case "2": {
                    run = deckSizeValidator(two);
                    break;
                }
                default: {
                    System.out.println("Deck 1 Size: " + one.getSize());
                    System.out.println("Deck 2 Size: " + two.getSize());
                    break;
                }
            }
        }
        return refund;
    }


    // attack a card, This is a CLI
    // @TODO Make so that cards can only attack from certain positions
    // @TODO Special attacks need to be sorted out
    // @TODO SPELLS need to be implemented!
    public int actionAttack(Player opponent) {
        boolean run = true;
        int deadCard = -1;
        Field ownField = player.getField();
        Field oppField = opponent.getField();
        FieldManager oppFM = new FieldManager(oppField);
        ValidInt validator = new ValidInt();
        while (run) {
            System.out.println("Select own card and opponents card to attack, if opponent has no cards select any");
            System.out.println("Own row: ");
            int mr = validator.nextInt(3);
            System.out.println("Own col: ");
            int mc = validator.nextInt(3);

            // validate input
            if (!validator.valid(mr, mc)) {
                System.out.println("Invalid Input!");
                continue;
            }
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
            int or = validator.nextInt(3);
            System.out.println("Opp col: ");
            int oc = validator.nextInt(3);

            // validate input
            if (!validator.valid(or, oc)) {
                System.out.println("Invalid Input! Start again.");
                continue;
            }
            // is card you selected to attack exist?
            if (oppField.getPosition(or, oc) == null) {
                System.out.println("No card in that position!, Try again");
                continue;
            }
            AttackAction attack = new AttackAction(ownField.getPosition(mr,mc), mr, mc);
            oppField.setPosition(attack.directAttack(oppField.getField(), or, oc), or, oc);
//            oppField.getPosition(or, oc).setRunningHealth(
//                    oppField.getPosition(or, oc).getRunningHealth()-ownField.getPosition(mr, mc).getRunningAttack());
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

    // view cards
    public void actionView(Player opponent) {
        System.out.println(Arrays.toString(player.getHand().getIds()));
        cardPrinterH(opponent);
        handPrinter();
    }
    // skip your turn
    public ArrayList<ActionType> actionSkip() {
        return new ArrayList<>(0);
    }
    // validates deck size duh.
    private boolean deckSizeValidator(Deck deck) {
        DeckManager dm = new DeckManager(null);
        HandManager hm = new HandManager(player.getHand());
        if (deck.getSize() >= 1) {
            dm.setDeck(deck);
            hm.addCard(dm.pickCard());
            return false;
        } else {
            System.out.println("Deck empty.");
            return true;
        }
    }
    // print cards
    private void cardPrinterH(Player opponent) {

        System.out.printf( "|Your field:                           |   |%-38s|%n", opponent.getName()+"'s Field:");
        CardPOJO card;

        String[] names = new String[6];
        int[] hlt = new int[6];
        int[] att = new int[6];
        for (int i = 0; i < 3; i++) {
            CardPOJO[] cardListA = player.getField().getField()[i];
            CardPOJO[] cardListB = opponent.getField().getField()[i];
            int pointer = 0;
            for (CardPOJO cardPOJO : cardListA) {
                if ((card = cardPOJO) != null) {
                    names[pointer] = card.getName();
                    hlt[pointer] = card.getRunningHealth();
                    att[pointer] = card.getRunningAttack();
                } else {
                    names[pointer] = "Nothing";
                    hlt[pointer] = 0;
                    att[pointer] = 0;
                }
                pointer++;
            }
            for (CardPOJO cardPOJO : cardListB) {
                if ((card = cardPOJO) != null) {
                    names[pointer] = card.getName();
                    hlt[pointer] = card.getRunningHealth();
                    att[pointer] = card.getRunningAttack();
                } else {
                    names[pointer] = "Nothing";
                    hlt[pointer] = 0;
                    att[pointer] = 0;
                }
                pointer++;
            }
            System.out.println("========================================   ========================================");
            System.out.printf("| %-10s | %-10s | %-10s |   | %-10s | %-10s | %-10s |%n",
                    names[0], names[1], names[2],names[3],names[4],names[5]);
            System.out.printf("| HP :%6d | HP :%6d | HP :%6d |   | HP :%6d | HP :%6d | HP :%6d |%n",
                    hlt[0], hlt[1], hlt[2], hlt[3], hlt[4], hlt[5]);
            System.out.printf("| ATK:%6d | ATK:%6d | ATK:%6d |   | ATK:%6d | ATK:%6d | ATK:%6d |%n",
                    att[0], att[1], att[2], att[3], att[4], att[5]);
        }
        System.out.println("========================================   ========================================");
    }
    @SuppressWarnings("unused")
    private void cardPrinterV(CardPOJO[][] cards) {
        for (CardPOJO[] cardList : cards) {
            CardPOJO card;
            System.out.println("========================================");
            int pointer = 0;
            String[] names = new String[3];
            int[] hlt = new int[3];
            int[] att = new int[3];
            for (CardPOJO cardPOJO : cardList) {
                if ((card = cardPOJO) != null) {
                    names[pointer] = card.getName();
                    hlt[pointer] = card.getRunningHealth();
                    att[pointer] = card.getRunningAttack();

                } else {
                    names[pointer] = "Nothing";
                    hlt[pointer] = 0;
                    att[pointer] = 0;
                }
                pointer++;
            }
            System.out.printf("| %-10s | %-10s | %-10s |%n", names[0], names[1], names[2]);
            System.out.printf("| HP :%6d | HP :%6d | HP :%6d |%n", hlt[0], hlt[1], hlt[2]);
            System.out.printf("| ATK:%6d | ATK:%6d | ATK:%6d |%n", att[0], att[1], att[2]);
        }
        System.out.println("========================================");
    }

    // first attempt at making a stable card printer
    private void handPrinter() {
        String border = "========================";
        String seperator = "------------------------";
        int size = player.getHand().getSize();

        String[] names = new String[size];
        int[] healths = new int[size];
        int[] attack = new int[size];
        String[] desc = new String[size];

        HandManager hm = new HandManager(player.getHand());
        int i = 0;
        for (CardPOJO card : hm.readHand()) {
            names[i] = card.getName();
            healths[i] = card.getBaseHealth();
            attack[i] = card.getBaseAttack();
            desc[i] = card.getDescription();
            i++;
        }

        System.out.printf("%n");
        for (int j = 0; j < size; j++) {
            System.out.printf(border + "   ");
        }
        System.out.println();
        for (String name : names) {
            System.out.printf("| %-20s |   ", name);
        }
        System.out.println();
        for (int hp : healths) {
            System.out.printf("| HP : %15d |   ", hp);
        }
        System.out.println();
        for (int atk : attack) {
            System.out.printf("| ATK: %15d |   ", atk);
        }
        System.out.println();
        for (int j = 0; j < size; j++) {
            System.out.printf(seperator + "   ");
        }
        System.out.println();
        for (String description : desc) {
            System.out.printf("| %-20s |   ", description);
        }
        System.out.println();
        for (int j = 0; j < size; j++) {
            System.out.printf(border + "   ");
        }
        System.out.println();


    }

    // Setter
    public void setPlayer(Player player) {
        this.player = player;
    }


}

