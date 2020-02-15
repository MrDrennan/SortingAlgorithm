/*
 * Chad Drennan
 * 2/14/2020
 * GroupSort.java
 * This file contains the GroupSort class which uses the bucket sorting algorithm to sort an array of integers
 */

package sortingalgorithm;

/**
 * This class sorts an array of ints using the bucket sorting algorithm.
 * @author Chad Drennan
 * @version 1.0
 */
public class GroupSort {

    // To make upper bounds inclusive and counter rounding errors
    private static final double THRESHOLD_ADJUSTMENT = .000001;

    /**
     * Sorts an array of integers in ascending order using groups.
     * Must supply groupQty of 1 or greater
     * @param sourceArray the array to sort
     * @param groupQty amount of groups to sort with
     * @throws IllegalArgumentException if groupQty is less than 1
     */
    public void sort(int[] sourceArray, int groupQty) {

        // Preconditions
        if (sourceArray.length < 2) {
            return;
        }

        if (groupQty < 1) {
            throw new IllegalArgumentException("Group quantity must be greater than 1");
        }

        int min = sourceArray[0];
        int max = sourceArray[0];

        // Find max and min
        for (int currInt : sourceArray) {
            if (currInt < min) {
                min = currInt;
            }
            if (currInt > max) {
                max = currInt;
            }
        }
        System.out.println("Min/max: [" + min + ", " + max + "]");

        // Range of numbers divided by groupQty is group size
        double groupSize = (double)(max - min) / groupQty;

        // Group the data
        Node[] groups = createGroupsAndAddItems(sourceArray, groupQty, min, groupSize);
        displayGroupData(groupQty, min, groupSize);

        // Sort the source array
        putSortedItemsIntoSourceArray(sourceArray, min, groupSize, groups);
    }


    // Helper method to sort method
    private void putSortedItemsIntoSourceArray(int[] sourceArray, int min, double groupSize, Node[] groups) {
        int srcIndex = 0;
        int lowerBound = min;

        for (int i = 0; i < groups.length; i++) {
            lowerBound = displayGroupRange(min, groupSize, lowerBound, i);
            srcIndex = putGroupIntoSourceArray(sourceArray, groups[i], srcIndex);
        }
        System.out.println();
    }


    // Helper method to putSortedItemsIntoSourceArray method
    private int displayGroupRange(int min, double groupSize, int lowerBound, int index) {
        int upperBound = (int)((index + 1) * groupSize + min);
        System.out.print(index + "[" + lowerBound + ", " + upperBound + "]: ");
        lowerBound = upperBound + 1;
        return lowerBound;
    }


    // Helper method to putGroupIntoSourceArray method
    private int putGroupIntoSourceArray(int[] sourceArray, Node group, int srcIndex) {
        Node currNode = group;

        // Fencepost
        if (currNode != null) {
            System.out.print(currNode.data);

            sourceArray[srcIndex] = currNode.data;
            srcIndex++;
            currNode = currNode.next;
        }
        else {
            System.out.print("null");
        }

        // Add each node from the group to the array
        while (currNode != null) {
            System.out.print(" -> " + currNode.data);

            sourceArray[srcIndex] = currNode.data;
            srcIndex++;
            currNode = currNode.next;
        }
        System.out.println();
        return srcIndex;
    }


    // Helper method to sort method
    private Node[] createGroupsAndAddItems(int[] sourceArray, int groupQty, int min, double groupSize) {
        Node[] groups = new Node[groupQty];

        for (int currInt : sourceArray) {
            int groupIndex = (int)((currInt - min) / groupSize - THRESHOLD_ADJUSTMENT);
            addItemToGroup(currInt, groupIndex, groups);
        }
        return groups;
    }


    // Helper method to sort method
    private void displayGroupData(int groupQty, int min, double groupSize) {
        System.out.println("Group size: " + groupSize);

        // Fencepost
        System.out.print("Group thresholds: [" + (min + (int)groupSize));

        for (int i = 2; i <= groupQty; i++) {
            System.out.print(", " + (int)(i * groupSize + min));
        }
        System.out.println("]\n");
    }


    // Helper method to createGroupsAndAddItems method
    private void addItemToGroup(int currInt, int groupIndex, Node[] groups) {
        Node curr = groups[groupIndex];

        // Put node in front if first one or smallest
        if (curr == null || curr.data > currInt) {
            groups[groupIndex] = new Node(currInt, curr);
            return;
        }

        // Place item before first item with higher value or at the end
        while (curr.next != null && curr.next.data < currInt) {
            curr = curr.next;
        }
        curr.next = new Node(currInt, curr.next);
    }


    /**
     * Represents a singly linked node to other nodes and containing integer data
     * @author Chad Drennan
     * @version 1.0
     */
    private static class Node {
        // Holds integer values
        private final int data;

        // The node this node links to
        private Node next;


        /**
         * 2 args constructor
         * @param data integer data for this node
         * @param next node this node links to
         */
        private Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
}