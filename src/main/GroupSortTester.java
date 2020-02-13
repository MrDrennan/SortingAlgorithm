package main;

import sortingAlgorithm.GroupSort;

import java.util.Arrays;
import java.util.Random;

public class GroupSortTester {
    public static void main(String[] args) {
        int arraySize = 15;
        int randUpperBound = 30;
        int groupQty = 5;

        Random rand = new Random();

        int[] array = new int[arraySize];

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(randUpperBound) + 1;
        }

        System.out.println("Generated an array of size " + arraySize + " with a range of [1, " + randUpperBound + "]");
        System.out.println("Original: " + Arrays.toString(array));
        System.out.println();
        System.out.println("Sorting with " + groupQty + " groups");

        GroupSort sorter = new GroupSort();
        sorter.sort(array, groupQty);

        System.out.println("Sorted: " + Arrays.toString(array));
        System.out.println("Detected inversions? " + hasInversions(array));
    }

    public static boolean hasInversions(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
