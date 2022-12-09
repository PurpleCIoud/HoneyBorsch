package net.honey;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private static PlayAreaManager playArea;

    public static void main(String[] args) {
        System.out.println("Player 1 Name: Ilja ");
        System.out.println("PLayer 2 Name: Mike ");
        Player player1 = new Player("Ilja");
        Player player2 = new Player("Mike");
        playArea = new PlayAreaManager(player1,player2);
        gameLoop();
    }

    private static void playTurn() {
        boolean running = true;
        while (running) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Current player is: " + playArea.determineCurrentPlayer().getName());
            System.out.println("Chose action to do, you have:" + playArea.getPlayArea().getActionsList());
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
                    doAction(ActionType.VIEW);
                    break;
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
            run = !playArea.getWinner();
        }
    }

    private static void doAction(ActionType action) {
        if (playArea.getPlayArea().getActionsList().contains(action)) {
            playArea.takeTurn(action);
            ArrayList<ActionType> current = new ArrayList<>(playArea.getPlayArea().getActionsList());
            current.remove(action);
            playArea.getPlayArea().setActionsList(current);
        }
    }
}
