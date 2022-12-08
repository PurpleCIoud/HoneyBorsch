package net.honey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private static PlayAreaManager playArea;

    public static void main(String[] args) {
        Player player1 = new Player("Ilja");
        Player player2 = new Player("Mike");
        playArea = new PlayAreaManager(player1,player2);
        gameLoop();
    }
    private static void doAction(ActionType action) {
        if (playArea.getPlayArea().getActionsList().contains(action)) {
            playArea.takeTurn(action);
            ArrayList<ActionType> current = new ArrayList<>(playArea.getPlayArea().getActionsList());
            current.remove(action);
            playArea.getPlayArea().setActionsList(current);
        }
    }

    private static void playTurn() {
        boolean running = true;
        while (running) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Current player is: " + playArea.determineCurrentPlayer().getName());
            System.out.println("Chose action to do, you have:" + playArea.getPlayArea().getActionsList() + " or View Field");
            switch (scanner.next().toLowerCase()) {
                case "attack": {
                    doAction(ActionType.ATTACK);
                    break;
                }
                case "summon": {
                    doAction(ActionType.SUMMON);
                    break;
                }
                case "skip": {
                    playArea.takeTurn(ActionType.SKIP);
                    break;
                }
                case "pickup": {
                    doAction(ActionType.PICKUP);
                    break;
                }
                case "view": {
                    System.out.println(Arrays.toString(playArea.getPlayArea().getPlayer1().getHand().getIds()));
                    System.out.println(Arrays.toString(playArea.getPlayArea().getPlayer2().getHand().getIds()));
                    cardPrinter(playArea.determineCurrentPlayer().getField().getField());

                }
            }
            running = !playArea.getPlayArea().getActionsList().isEmpty();
        }
    }

    private static void gameLoop() {
        boolean run = true;
        while (run) {
            playTurn();
            playArea.finishTurn();
        }
    }

    private static void cardPrinter(CardPOJO[][] cards) {
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
    }
}
