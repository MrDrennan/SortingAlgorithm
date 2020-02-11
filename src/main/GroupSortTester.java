package main;

import sortingAlgorithm.GroupSort;

import java.util.Arrays;
import java.util.Random;

public class GroupSortTester {
    public static void main(String[] args) {
        Random rand = new Random();

        int[] array = new int[15];

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(30) + 1;
        }
        array = new int[] {25, 17, 17, 1, 3, 18, 8, 13, 10, 1, 26, 1, 12, 14, 9};
        System.out.println(Arrays.toString(array));

        GroupSort sorter = new GroupSort();

        sorter.sort(array, 5);

        System.out.println(Arrays.toString(array));
    }
}
