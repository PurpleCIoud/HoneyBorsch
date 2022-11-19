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
        for (int itemId = 0; itemId < cardJsonReader.getLength(); itemId++) {
            card= cardJsonReader.getFromId(itemId);
        }
    }
}
