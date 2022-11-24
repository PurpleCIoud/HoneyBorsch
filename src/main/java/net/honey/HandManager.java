package net.honey;

// This Class is responsible for editing the hand
public class HandManager{
    private final Hand hand;

    public HandManager(Hand hand) {
        this.hand = hand;
    }

    public CardPOJO[] readHand() {
        CardJsonReader cjr = new CardJsonReader("CardDef.json");
        int[] ids = hand.getIds();
        CardPOJO[] cards = new CardPOJO[hand.getSize()];
        for (int i = 0; i <= ids.length; i++) {
            cards[i] = cjr.getFromId(ids[i]);
        }
        return cards;
    }

    public void addCard(int cardId) {
        // funky way of adding a card
        int[] newIds = new int[hand.getSize()+1];
        int[] oldIds = hand.getIds();
        System.arraycopy(oldIds, 0, newIds, 0, newIds.length - 1);
        newIds[hand.getSize()+1] = cardId;
        hand.setIds(newIds);
    }

    public void removeCard(int cardId) {
        // going to remove card by ID
        int[] newIds = new int[hand.getSize()-1];
        int[] oldIds = hand.getIds();
        for (int i = 0; i < oldIds.length; i++) {
            if (oldIds[i] != cardId) {
                newIds[i] = oldIds[i];
            }
        }
        hand.setIds(newIds);
    }

}
