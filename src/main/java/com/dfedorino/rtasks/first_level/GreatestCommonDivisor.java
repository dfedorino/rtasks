package com.dfedorino.rtasks.first_level;

public class GreatestCommonDivisor {
    public int find(int a, int b) {
        while (b != 0) {
            int aValue = a;
            a = b;
            b = aValue % b;
        }
        return a;
    }
}
