package main;

import sortingAlgorithm.GroupSort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GroupSortTester {

    public static final int ARRAY_SIZE = 15;
    public static final int RAND_UPPER_BOUND = 30;

    public static void main(String[] args) {
        System.out.println("Group Sorter");
        System.out.println("************\n");
        System.out.println("How many groups would you like to sort with?");

        Scanner console = new Scanner(System.in);
        int groupQty = console.nextInt();

        Random rand = new Random();
        int[] array = new int[ARRAY_SIZE];

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(RAND_UPPER_BOUND) + 1;
        }

        System.out.println("Generated an array of size " + ARRAY_SIZE
                + " with a range of [1, " + RAND_UPPER_BOUND + "]");
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
