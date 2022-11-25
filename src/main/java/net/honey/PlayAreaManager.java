package net.honey;

public class PlayAreaManager {
    private PlayArea playArea;

    public PlayAreaManager() {
        playArea = new PlayArea();
    }
    // returning 0 for player1, 1 for player2
    public byte determineTurn() {
        return (byte) (playArea.getTurn()%2);
    }
    // returns the state of card
    public boolean isDestroyed(int row, int col, byte player) {
        boolean destroyed;
        if (player == 0) {
            destroyed = 0>=playArea.getOpponentField().getPosition(row, col).getRunningHealth();
        } else {
            destroyed = 0>=playArea.getPlayerField().getPosition(row, col).getRunningHealth();
        }
        return destroyed;
    }

    public void destroyCard(int row, int col, byte player) {

    }
}
