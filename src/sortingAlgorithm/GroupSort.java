package sortingAlgorithm;

public class GroupSort {

    // To make upper bounds inclusive and counter rounding errors
    public static final double THRESHOLD_ADJUSTMENT = .000001;

    public void sort(int[] sourceArray, int groupQty) {

        // Precondition
        if (sourceArray.length < 2) {
            return;
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

        Node[] groups = new Node[groupQty];

        // Add items to groups while sorting
        for (int currInt : sourceArray) {
            int groupIndex = (int)((currInt - min) / groupSize - THRESHOLD_ADJUSTMENT);
            addItemToGroup(currInt, groupIndex, groups);
        }

        // Output group data
        System.out.println("Group size: " + groupSize);
        System.out.print("Group thresholds: [" + (min + (int)groupSize));

        for (int i = 2; i <= groupQty; i++) {
            System.out.print(", " + (int)(i * groupSize + min));
        }
        System.out.println("]\n");

        int srcIndex = 0;
        int lowerBound = min;

        // Place items back into source array
        for (int i = 0; i < groups.length; i++) {
            int upperBound = (int)((i + 1) * groupSize + min);
            System.out.print(i + "[" + lowerBound + ", " + upperBound + "]: ");
            lowerBound = upperBound + 1;

            Node currNode = groups[i];

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

            while (currNode != null) {
                System.out.print(" -> " + currNode.data);

                sourceArray[srcIndex] = currNode.data;
                srcIndex++;
                currNode = currNode.next;
            }
            System.out.println();
        }
        System.out.println();
    }

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

    private class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this(data, null);
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
