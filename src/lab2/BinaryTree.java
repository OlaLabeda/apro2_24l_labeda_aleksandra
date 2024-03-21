package lab2;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author Aleksandra Łabęda, Jan Kozaczuk
 *
 * A class representing a binary tree
 * @param <T>
 */

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private TreeNode root;

    private class TreeNode {
        private T data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(T data) {
            this.data = data;
        }
    }

    /**
     * Adds a value to the binary tree
     * @param current
     * @param value
     * @return
     */
    private TreeNode addRecursive(TreeNode current, T value) {
        //if the current node is null, we have reached the end of the tree
        //base case
        if (current == null) {
            return new TreeNode(value);
        }
        //compare the value to the current node in order to decide where to go next
        // if the value is less than the current node, go left because it is smaller
        int compareResult = value.compareTo(current.data);
        if (compareResult < 0) {
            current.left = addRecursive(current.left, value);
        } else if (compareResult > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    /**
     * Adds a value to the binary tree
     * @param value
     */
    public void add(T value) {
        root = addRecursive(root, value);
    }

    /**
     * prints the tree in level order
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        //create a queue to store the nodes
        Queue<TreeNode> queue = new LinkedList<>();
        //add the root to the queue
        queue.offer(root);

        //while the queue is not empty, print the nodes and add their children to the queue
        while (!queue.isEmpty()){
            //remove the first node from the queue
            TreeNode temp = queue.poll();
            //print the node
            System.out.print(temp.data + " ");
            //add the children to the queue
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            //add the children to the queue
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    //inner class that implements the iterator
    private class TreeIterator implements Iterator<T> {
        private Queue<TreeNode> queue;

        public TreeIterator() {
            //create a queue to store the nodes
            queue = new LinkedList<>();
            //add the root to the queue
            if (root != null) {
                queue.offer(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements in the tree");
            }

            TreeNode current = queue.poll();

            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }

            return current.data;
        }
    }
}
