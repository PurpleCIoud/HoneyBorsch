package net.honey;

public class PlayerField {
    private boolean isPlayersGrid;
    private final CardPOJO[][] playGrid = new CardPOJO[3][3];    //Actual play grid, Stores cards
    private final CardPOJO[][] specialGrid = new CardPOJO[3][3]; //Stores "Hidden" cards
    private final boolean[][] isPlayersCard = new boolean[3][3]; //Tells if the card is controlled by the player

    // Setters
    public void setField(CardPOJO card, int row, int col) {
        playGrid[row][col] = card;
    }
    public void setSpecialField(CardPOJO card, int row, int col) {
        specialGrid[row][col] = card;
    }
    public void setIsPlayersCard(boolean bool, int row, int col) {
        isPlayersCard[row][col] = bool;
    }

    // Getters
    public CardPOJO getField(int row, int col) {
        return playGrid[row][col];
    }
    public CardPOJO getSpecialField(int row, int col) {
        return specialGrid[row][col];
    }
    public boolean getIsPlayersCard(int row, int col) {
        return isPlayersCard[row][col];
    }
}
