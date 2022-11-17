package net.honey;

import java.util.Arrays;

public class DevTest {
    public static void main(String[] args) {
        testReading();
    }
    private static void testField(){
        PlayerField field= new PlayerField();
        field.setField(new CardPOJO(0,0,AttackType.DIRECT,"Empty"),0,0);
        field.getField(0,0).devAll();
        System.out.println(Arrays.toString(field.getRow(0)));
        System.out.println(Arrays.toString(field.getCol(0)));
    }
    private static void testReading(){
        CardDataReader cardDataReader = new CardDataReader("CardDef.json");
        cardDataReader.getFromId(1);
        cardDataReader.getFromId(0);
    }
}
