package net.honey;

public class Hand implements CardHolder {
    private int[] ids;
    private int size;

    // Build hand from initial cards
    public Hand(int[] initialCardIds) {
        this.ids = initialCardIds;
        this.size = initialCardIds.length;
    }
    public Hand() {
        ids = new int[0];
        size = ids.length;
    }
    // Getters
    public int[] getIds() {
        return ids;
    }
    public int getSize() {
        return size;
    }

    // Setters
    public void setIds(int[] ids) {
        this.ids = ids;
        this.size = ids.length;
    }
}
