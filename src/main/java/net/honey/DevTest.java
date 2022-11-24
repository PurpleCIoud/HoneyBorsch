package net.honey;

import org.junit.Test;

import java.util.Arrays;

public class DevTest {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    @Test
    //Test that the Json reading works and make sure that there is no invalid types in json files
    public void testJson() {
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
    @Test
    // Test that there are no 2 same Ids
    public void testId() {
        boolean sameIdFound = false;
        CardJsonReader cardJsonReader = new CardJsonReader("CardDef.json");
        int length = cardJsonReader.getLength();
        CardPOJO card;
        int[] ints = new int[length];
        for (int itemId = 0; itemId < cardJsonReader.getLength(); itemId++) {
            card = cardJsonReader.getFromId(itemId);
            ints[itemId] = card.getId();
        }
        ints = Arrays.stream(ints).sorted().toArray();
        for (int i = 0; i < ints.length-1 ; i++) {
            if (ints[i] == ints[i + 1]) {
                sameIdFound = true;
                break;
            }
        }
        assert !sameIdFound;
    }
    @Test
    // Test that Decks are properly generated
    public void testDeckBuilding() {
        PlayArea pa = new PlayArea();
    }

    @Test
    // test shuffle
    public void testShuffle() {
        int[] ints = new int[] {0,1,2,3,4,5};
        System.out.println(Arrays.toString(ints));
        ArrayShuffle shuffle = new ArrayShuffle();
        shuffle.shuffle(ints);
    }
}
