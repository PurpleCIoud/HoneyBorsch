package net.honey;

public class PlayerField {
    private final CardPOJO[][] playGrid = new CardPOJO[3][3];

    public void setField(CardPOJO card, int row, int col) {
        playGrid[row][col] = card;
    }

    public CardPOJO getField(int row, int col) {
        return playGrid[row][col];
    }

    public CardPOJO[] getRow(int row) {
        return playGrid[row];
    }

    public CardPOJO[] getCol(int col) {
        CardPOJO[] colArr = new CardPOJO[3];
        for (int i = 0; i < 2; i++) {
            colArr[i] = playGrid[i][col];
        }
        return colArr;
    }
}
