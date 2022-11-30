package net.honey;
// This Class only makes a deck and has no logic of its own
@SuppressWarnings("unused")
public class Deck implements CardHolder{
    private int size;
    private int[] ids;

    // Build a deck from given Cards
    public Deck(int[] cardsIds) {
        this.ids = cardsIds;
        this.size = cardsIds.length;
    }

    // Getters
    public int getSize() {
        return size;
    }

    @Override
    public void setIds(int[] cardsIds) {
        this.ids = cardsIds;
        this.size = cardsIds.length;
    }

    public int[] getIds() {
        return ids;
    }

    // Setters

}
