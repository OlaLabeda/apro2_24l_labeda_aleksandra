package lab2;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 * A class representing a binary tree
 */
public class BinaryTreeMain {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();

        // adding values to the binary tree
        binaryTree.add(5);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(2);
        binaryTree.add(4);
        binaryTree.add(6);
        binaryTree.add(8);

        // displaying the values of the binary tree in level order
        System.out.println("Wartości węzłów drzewa binarnego w kolejności poziomej:");
        binaryTree.levelOrder();

        // iterating through the binary tree
        System.out.println("\n\nIterowanie przez drzewo:");
        for (Integer value : binaryTree) {
            System.out.print(value + " ");
        }
    }
}
