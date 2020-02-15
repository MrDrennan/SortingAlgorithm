/*
 * Chad Drennan
 * 2/14/2020
 * GroupSortTester.java
 * This file contains the GroupSortTester class which tests the GroupSort class
 */

package main;

import sortingalgorithm.GroupSort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Tests the GroupSort class for bugs
 * @author Chad Drennan
 * @version 1.0
 */
class GroupSortTester {

    // Array size to create for testing
    private static final int ARRAY_SIZE = 20;

    // Inclusive upper boundary for random int range
    private static final int RAND_UPPER_BOUND = 100;


    /**
     * Starts the program
     * @param args not used
     */
    public static void main(String[] args) {

        // Create test array and display data to the user
        int groupQty = introAndGetGroupQty();
        int[] array = createRandomArray();
        displayDataBeforeSort(groupQty, array);

        // Sort the array
        GroupSort sorter = new GroupSort();
        sorter.sort(array, groupQty);

        // Display sorted data
        displayDataAfterSort(array);
    }


    // Creates random array for testing
    private static int[] createRandomArray() {
        Random rand = new Random();
        int[] array = new int[ARRAY_SIZE];

        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(RAND_UPPER_BOUND) + 1;
        }
        return array;
    }


    // Displays test array data after it has been sorted and checks for inversions
    private static void displayDataAfterSort(int[] array) {
        System.out.println("Sorted: " + Arrays.toString(array));
        System.out.println("Detected inversions? " + hasInversions(array));
    }


    // Displays test array data before it has been sorted
    private static void displayDataBeforeSort(int groupQty, int[] array) {
        System.out.println("Generated an array of size " + ARRAY_SIZE
                + " with a range of [1, " + RAND_UPPER_BOUND + "]");
        System.out.println("Original: " + Arrays.toString(array));
        System.out.println();
        System.out.println("Sorting with " + groupQty + " groups");
    }


    // Displays title of program and prompts user for group quantity
    private static int introAndGetGroupQty() {
        System.out.println("Group Sorter");
        System.out.println("************\n");
        System.out.println("How many groups would you like to sort with?");

        Scanner console = new Scanner(System.in);
        return console.nextInt();
    }


    // Determines if an array has at least one inversion.
    private static boolean hasInversions(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
