package net.honey;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class DevTest {
    public static void main(String[] args) {
        testReading();
    }
    //Test that the Json reading works and make sure that there is no invalid types in json files
    private static void testReading(){
        CardDataReader cardDataReader = new CardDataReader("CardDef.json");
        CardPOJO card;
        for (int itemId= 0; itemId < cardDataReader.getLength();itemId++) {
            card= cardDataReader.getFromId(itemId);
        }
        cardDataReader.printPretty();
    }
}
