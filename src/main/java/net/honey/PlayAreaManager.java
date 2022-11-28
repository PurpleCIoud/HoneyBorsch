package net.honey;

import java.util.Random;

public class PlayAreaManager {
    private final PlayArea playArea;
    private GraveyardManager gm;
    private DeckManager dm;
    private PlayerManager pm;

    public PlayAreaManager() {
        playArea = new PlayArea();
        genDecks();
        determineFirst();
    }

    public void allocateFirstTurn() {
        if (playArea.getFirstPlayer() == 0) {
            playArea.getPlayer1().setMyTurn(true);
        } else {
            playArea.getPlayer2().setMyTurn(true);
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
        System.arraycopy(full, firstHalf+1, half2, 0, secondHalf);
        playArea.getDeck1().setIds(half1);
        playArea.getDeck2().setIds(half2);
    }

    private void determineFirst() {
        Random r = new Random();
        playArea.setFirstPlayer((byte) r.nextInt(0,1));
    }

    private Player determineCurrentPlayer() {
        Player p;
        if ((playArea.getFirstPlayer() + playArea.getTurn())%2 == 0) {
            p = playArea.getPlayer1();
        } else {
            p = playArea.getPlayer2();
        }
        return p;
    }

    private Player determineCurrentOpponent() {
        Player p;
        if ((playArea.getFirstPlayer() + playArea.getTurn())%2 == 1) {
            p = playArea.getPlayer1();
        } else {
            p = playArea.getPlayer2();
        }
        return p;
    }

    // place a card from the hand onto the field
    private void actionProcessor(ActionType action, Player player, Player opponent) {
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
                pm.actionSkip();
                break;
            }
        }

    }

}
