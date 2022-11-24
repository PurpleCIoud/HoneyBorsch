package net.honey;

public class GraveyardManager {
    private final Graveyard grave;

    public GraveyardManager(Graveyard graveyard) {
        this.grave = graveyard;
    }

    public int pickCard(int id) {
        int[] newIds = new int[grave.getSize()-1];
        int[] oldIds = grave.getIds();
        for (int i = 0; i < oldIds.length; i++) {
            if (oldIds[i] != id) {
                newIds[i] = oldIds[i];
            }
        }
        grave.setIds(newIds);
        return id;
    }

    public CardPOJO[] showCards() {
        CardJsonReader cjr = new CardJsonReader("CardDef.json");
        int[] ids = grave.getIds();
        CardPOJO[] cards = new CardPOJO[grave.getSize()];
        for (int i = 0; i <= ids.length; i++) {
            cards[i] = cjr.getFromId(ids[i]);
        }
        return cards;
    }
}
