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
        playArea.takeTurn(action);
        ArrayList<ActionType> current = new ArrayList<>(playArea.getPlayArea().getActionsList());
        current.remove(action);
        playArea.getPlayArea().setActionsList(current);
    }

    private static void gameLoop() {
        boolean run = true;
        while (run) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Current player is: "+ playArea.getPlayArea().getFirstPlayer());
            System.out.println("Chose action to do, you have:"+ playArea.getPlayArea().getActionsList() + " or View Field");
            switch (scanner.next().toLowerCase()) {
                case "attack": {
                    if (playArea.getPlayArea().getActionsList().contains(ActionType.ATTACK)) {
                        doAction(ActionType.ATTACK);
                    }
                    break;
                }
                case "summon": {
                    if (playArea.getPlayArea().getActionsList().contains(ActionType.SUMMON)) {
                        doAction(ActionType.SUMMON);
                    }
                    break;
                }
                case "skip": {
                    playArea.takeTurn(ActionType.SKIP);
                    break;
                }
                case "pickup": {
                    if (playArea.getPlayArea().getActionsList().contains(ActionType.PICKUP)) {
                        doAction(ActionType.PICKUP);
                    }
                    break;
                }
                case "view": {
                    System.out.println(Arrays.toString(playArea.getPlayArea().getPlayer1().getHand().getIds()));
                    System.out.println(Arrays.toString(playArea.getPlayArea().getPlayer2().getHand().getIds()));
                }
            }
            if (playArea.getPlayArea().getActionsList().isEmpty()) {
                run = false;
            }
        }
    }
}
