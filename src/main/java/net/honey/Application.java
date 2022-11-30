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

    private static void gameLoop() {
        boolean run = true;
        while (run) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Current player is: "+ playArea.getPlayArea().getFirstPlayer());
            System.out.println("Chose action to do, you have:"+ playArea.getPlayArea().getActionsList() + " or View Field");
            switch (scanner.next().toLowerCase()) {
                case "attack": {
                    if (playArea.getPlayArea().getActionsList().contains(ActionType.ATTACK)) {
                        playArea.takeTurn(ActionType.ATTACK);
                        ArrayList<ActionType> current = new ArrayList<>(playArea.getPlayArea().getActionsList());
                        System.out.println(current);
                        current.remove(ActionType.ATTACK);
                        System.out.println(current);
                        playArea.getPlayArea().setActionsList(current);
                    }
                    break;
                }
                case "summon": {
                    playArea.takeTurn(ActionType.SUMMON);
                    break;
                }
                case "skip": {
                    playArea.takeTurn(ActionType.SKIP);
                    break;
                }
                case "pickup": {
                    playArea.takeTurn(ActionType.PICKUP);
                    break;
                }
                case "view": {
                    System.out.println(Arrays.toString(playArea.getPlayArea().getPlayer1().getHand().getIds()));
                    System.out.println(Arrays.toString(playArea.getPlayArea().getPlayer2().getHand().getIds()));
                }
            }
        }
    }
}
