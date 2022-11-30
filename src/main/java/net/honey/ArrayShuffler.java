package net.honey;

import java.util.Random;

public class ArrayShuffler {
    private int seed;

    ArrayShuffler() {
    }

    public int[] shuffle(int[] idList) {
        Random r = new Random();
        int[] tempArray = idList;
        int[] result = new int[idList.length];
        int pointer;
        for (int i = 0; i < result.length; i++) {
            pointer = r.nextInt(tempArray.length);
            result[i] = tempArray[pointer];
            tempArray = removeId(tempArray, pointer);
        }
        return result;
    }
    // Auxiliary method to "pop" an item
    private int[] removeId(int[] tempArray, int id) {
        int[] newIds = new int[tempArray.length-1];
        System.arraycopy(tempArray, 0, newIds, 0, id);
        System.arraycopy(tempArray, id+1, newIds,id,newIds.length-id);
        return newIds;
    }
}
