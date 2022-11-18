package net.honey;

import org.junit.Test;
public class DevTest {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    @Test
    //Test that the Json reading works and make sure that there is no invalid types in json files
    public void testJson(){
        CardDataReader cardDataReader = new CardDataReader("CardDef.json");
        CardPOJO card;
        for (int itemId= 0; itemId < cardDataReader.getLength();itemId++) {
            card= cardDataReader.getFromId(itemId);
        }
        cardDataReader.printPretty();
    }
}
