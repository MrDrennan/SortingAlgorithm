package sortingAlgorithm;

public class GroupSort {

    public void sort(int[] sourceArray, int groupQty) {
        // Preconditions
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
        //int groupSize = (max - min + 1) / groupQty + 1;
        double groupSize = (double)(max - min + 1) / groupQty;



        //int groupsRemainder = (max - min + 1) % groupQty;

        Node[] groups = new Node[groupQty];

        // Add items to groups while sorting
        for (int currInt : sourceArray) {
            int groupIndex = (int)((currInt - min) / groupSize);
            //int remainderAdjustment = groupsRemainder - (groupQty - 1 - groupIndex);

            addItemToGroup(currInt, groupIndex, groups);
        }

        System.out.println("Group size: " + groupSize);

        System.out.print("Group thresholds: [" + (min + (int)groupSize));

        for (int i = 2; i <= groupQty; i++) {
            System.out.print(", " + (int)(i * groupSize + min));
        }
        System.out.println("]\n");

        int srcIndex = 0;

        // Place items back into source array
        //for (Node currGroup : groups) {
        for (int i = 0; i < groups.length; i++) {
            System.out.print(i + "[" + (int)((i) * groupSize + min) + ", " + (int)((i + 1) * groupSize + min) + "]: ");

            Node currNode = groups[i];

            if (currNode != null) {
                System.out.print(currNode.data);
                sourceArray[srcIndex] = currNode.data;
                srcIndex++;
                currNode = currNode.next;
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
