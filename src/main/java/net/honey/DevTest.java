package net.honey;

import org.junit.Test;
public class DevTest {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    @Test
    //Test that the Json reading works and make sure that there is no invalid types in json files
    public void testJson(){
        CardJsonReader cardJsonReader = new CardJsonReader("CardDef.json");
        CardPOJO card;
        boolean errorEncountered = false;
        for (int itemId = 0; itemId < cardJsonReader.getLength(); itemId++) {
            card= cardJsonReader.getFromId(itemId);
            if (card.getId()== -1||
                    card.getBaseAttack()== -1 ||
                    card.getBaseHealth() == -1 ||
                    card.getAttackType().equals(AttackType.ERROR)||
                    card.getName() == null ||
                    card.getImageLink() == null ||
                    card.getDescription() == null) {
                errorEncountered = true;
            }
        }
        assert !errorEncountered;
    }
}
