package net.honey;

import java.util.Scanner;

public class ValidInt {

    // Construct for range in list
    public int nextInt() {
        Scanner s = new Scanner(System.in);
        if (s.hasNextInt()) {
            return s.nextInt();
        } else return -1;
    }

    public int nextInt(int range) {
        int out;
        if ((out = nextInt()) < range) {
            return out;
        } else return -1;
    }

    public boolean valid(int x, int y) {
        return x != -1 && y != -1;
    }

    public boolean valid(int x) {
        return x != -1;
    }
}
