package net.honey;

import java.util.Arrays;
import java.util.Random;

public class ArrayShuffle {
    private int seed;

    ArrayShuffle() {
    }

    public int[] shuffle(int[] idList) {
        Random r = new Random();
        int[] temp0 = idList;
        int[] temp1 = new int[idList.length-1];
        int[] result = new int[idList.length];
        for (int i = 0; i < result.length; i++) {
            int pointer = r.nextInt(temp0.length);
            System.out.println(pointer);
            result[i] = temp0[pointer];

        }
        return result;
    }
}
