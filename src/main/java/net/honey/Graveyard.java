package net.honey;

public class Graveyard implements CardHolder{
    private int[] ids;
    private int size;

    Graveyard(){

    }
    public int[] getIds() {
        return ids;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void setIds(int[] ids) {
        this.ids = ids;
        this.size = ids.length;

    }
}
