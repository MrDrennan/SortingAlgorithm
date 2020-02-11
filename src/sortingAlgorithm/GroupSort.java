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

        // TODO remove the +1
        int groupSize = (max - min + 1) / groupQty + 1;
        //int groupsRemainder = (max - min + 1) % groupQty;

        Node[] groups = new Node[groupQty];

        // Add items to groups while sorting
        for (int currInt : sourceArray) {
            int groupIndex = (currInt - min) / groupSize;
            //int remainderAdjustment = groupsRemainder - (groupQty - 1 - groupIndex);

            addItemToGroup(currInt, groupIndex, groups);
        }

        int srcIndex = 0;

        // Place items back into source array
        for (Node currGroup : groups) {
            Node currNode = currGroup;

            while (currNode != null) {
                sourceArray[srcIndex] = currNode.data;
                srcIndex++;
                currNode = currNode.next;
            }
        }
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

    private void addItemToGroup() {

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
