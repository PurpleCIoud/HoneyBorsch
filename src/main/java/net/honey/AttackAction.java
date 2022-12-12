package net.honey;

public class AttackAction {
    private CardPOJO card;
    private int[] position;

    public AttackAction(CardPOJO card, int row, int col) {
        this.card = card;
        this.position = new int[] {row, col};
    }
    // Front can attack Front and Middle,
    // Middle can attack Front and Middle,
    // Cannot attack card behind another card in row, e.g. Front attacks mid when there is a card in front.

    // direct attack. Attack any card anywhere,
    public CardPOJO directAttack(CardPOJO[][] oppField, int row, int col) {
        CardPOJO oppCard = oppField[row][col];
        int myAttack = card.getRunningAttack();
        oppCard.setRunningHealth(oppCard.getRunningHealth()-myAttack);
        return oppCard;
    }

    public CardPOJO normalAttack(CardPOJO[][] oppField, int row, int col) {
        CardPOJO oppCard = oppField[row][col];
        int myAttack = card.getRunningAttack();
        return oppCard;
    }
}
