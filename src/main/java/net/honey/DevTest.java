package net.honey;

import java.util.Arrays;

public class DevTest {
    public static void main(String[] args) {
        testReading();
    }

    private static void testReading(){
        CardDataReader cardDataReader = new CardDataReader("CardDef.json");
        cardDataReader.getFromId(0);
        cardDataReader.getFromId(1);
    }
}
