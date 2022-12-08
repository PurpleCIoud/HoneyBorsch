package net.honey;

// This Class is responsible for editing the hand
public class HandManager{
    private final Hand hand;

    public HandManager(Hand hand) {
        this.hand = hand;
    }

    public CardPOJO[] readHand() {
        if (hand.getSize() != 0) {
            CardJsonReader cjr = new CardJsonReader("CardDef.json");
            int[] ids = hand.getIds();
            CardPOJO[] cards = new CardPOJO[hand.getSize()];
            for (int i = 0; i < ids.length; i++) {
                cards[i] = cjr.getFromId(ids[i]);
            }
            return cards;
        } else return new CardPOJO[0];
    }

    public void addCard(int cardId) {
        // funky way of adding a card
        int[] newIds = new int[hand.getSize()+1];
        int[] oldIds = hand.getIds();
        System.arraycopy(oldIds, 0, newIds, 0, newIds.length - 1);
        newIds[hand.getSize()] = cardId;
        hand.setIds(newIds);
    }

    public void removeCard(int cardId) {
        // #NewAndImproved
        int[] oldHand = hand.getIds();
        int[] newHand = new int[hand.getSize()-1];
        int pointer = 0;
        for (int j : oldHand) {
            if (j == cardId) {
                continue;
            }
            newHand[pointer] = j;
            pointer++;
        }
        hand.setIds(newHand);
    }

}
