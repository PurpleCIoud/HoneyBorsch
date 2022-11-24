package net.honey;

public class FieldManager {
    private final Field field;

    public FieldManager(Field field) {
        this.field = field;
    }

    public void placeCard(int id, int row, int col) {
        CardJsonReader cjr = new CardJsonReader("CardDef.json");
        CardPOJO card;
        if (field.getPosition(row, col) == null) {
            card = cjr.getFromId(id);
            field.setPosition(card, row, col);
        }
    }

    public int removeCard(int row, int col) {
        int id =field.getPosition(row, col).getId();
        field.setPosition(null, row, col);
        return id;
    }

    public CardPOJO[][] readField() {
        return field.getField();
    }
}
