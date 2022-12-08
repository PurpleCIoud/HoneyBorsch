package net.honey;

import java.util.ArrayList;
import java.util.Random;

public class PlayAreaManager {
    private final PlayArea playArea;
    private final GraveyardManager gm;
    private PlayerManager pm;

    public PlayAreaManager(Player p1, Player p2) {
        playArea = new PlayArea();
        playArea.setPlayer1(p1);
        playArea.setPlayer2(p2);
        gm = new GraveyardManager(playArea.getGraveyard());
        genDecks();
        determineFirst();
        allocateFirstTurn();
        genFirstActions();
    }

    private void allocateFirstTurn() {
        if (playArea.getFirstPlayer() == 0) {
            playArea.getPlayer1().setMyTurn(true);
            playArea.getPlayer2().setMyTurn(false);
        } else {
            playArea.getPlayer2().setMyTurn(true);
            playArea.getPlayer1().setMyTurn(false);
        }
    }

    private void genDecks() {
        CardJsonReader cjr = new CardJsonReader("CardDef.json");
        ArrayShuffler shuffler = new ArrayShuffler();
        int[] full = cjr.getIds();
        full = shuffler.shuffle(full);
        int length = cjr.getLength();
        int firstHalf = length/2;
        int secondHalf = length - firstHalf;
        int[] half1 = new int[firstHalf];
        int[] half2 = new int[secondHalf];
        System.arraycopy(full, 0, half1, 0, firstHalf);
        System.arraycopy(full, firstHalf, half2, 0, secondHalf);
        playArea.getDeck1().setIds(half1);
        playArea.getDeck2().setIds(half2);
    }

    private void determineFirst() {
        Random r = new Random();
        playArea.setFirstPlayer((byte) (r.nextInt(10)%2));
    }

    public Player determineCurrentPlayer() {
        Player p;
        if ((playArea.getFirstPlayer() + playArea.getTurn())%2 == 0) {
            p = playArea.getPlayer1();
        } else {
            p = playArea.getPlayer2();
        }
        return p;
    }

    public Player determineCurrentOpponent() {
        Player p;
        if ((playArea.getFirstPlayer() + playArea.getTurn())%2 == 1) {
            p = playArea.getPlayer1();
        } else {
            p = playArea.getPlayer2();
        }
        return p;
    }

    public void takeTurn(ActionType action) {
        System.out.println(determineCurrentPlayer().getName());
        actionProcessor(action, determineCurrentPlayer(), determineCurrentOpponent());
    }
    public void finishTurn() {
        playArea.setTurn(playArea.getTurn()+1);
        genDefaultActions();
    }
    private void genFirstActions() {
        ArrayList<ActionType> actionTypes = new ArrayList<>(4);
        actionTypes.add(ActionType.PICKUP);
        actionTypes.add(ActionType.PICKUP);
        actionTypes.add(ActionType.SUMMON);
        actionTypes.add(ActionType.SUMMON);
        playArea.setActionsList(actionTypes);
    }

    private void genDefaultActions() {
        ArrayList<ActionType> actionTypes = new ArrayList<>(5);
        actionTypes.add(ActionType.PICKUP);
        actionTypes.add(ActionType.PICKUP);
        actionTypes.add(ActionType.SUMMON);
        actionTypes.add(ActionType.SUMMON);
        actionTypes.add(ActionType.ATTACK);
        playArea.setActionsList(actionTypes);
    }
    // Process actions.
    private void actionProcessor(ActionType action, Player player, Player opponent) {
        pm = new PlayerManager();
        pm.setPlayer(player);
        switch (action) {
            case SUMMON: {
                pm.actionSummon();
                break;
            }
            case PICKUP: {
                pm.actionPickup(playArea.getDeck1(), playArea.getDeck2());
                break;
            }
            case ATTACK: {
                int id;
                if ((id = pm.actionAttack(opponent)) != -1) {
                    gm.addCard(id);
                }
                break;
            }
            case SKIP: {
                playArea.setActionsList(pm.actionSkip(playArea.getActionsList()));
                break;
            }
        }
    }

    public PlayArea getPlayArea() {
        return playArea;
    }
}
