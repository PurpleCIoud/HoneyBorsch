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
            System.out.println("Current player is: " + playArea.getPlayArea().getFirstPlayer());
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

                    for (CardPOJO[] cardList :
                            playArea.getPlayArea().getPlayer1().getField().getField()) {
                        CardPOJO card;
                        for (CardPOJO cardPOJO : cardList) {
                            card = cardPOJO;
                            if (card != null) {
                                System.out.print("|" + card.getName() + " : " + card.getId() + "|");
                            } else {
                                System.out.print("| Nothing |");
                            }
                        }
                        System.out.println();
                    }
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
}
