package lab2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 * Tests for the BinaryTree class

 */
/*
    in a typical binary tree, nodes are organized so that values smaller
    than the root value are placed in the left subtree, and values greater than the root value
    are placed in the right subtree. This is because organizing nodes in this way allows for fast tree traversal
    and other operations on it
 */
public class BinaryTreeTest {

    @Test
    public void testLevelOrderTraversalInNormalBinaryTree() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(5);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(2);
        binaryTree.add(4);
        binaryTree.add(6);
        binaryTree.add(8);

        StringBuilder sb = new StringBuilder();
        for (Integer value : binaryTree) {
            sb.append(value).append(" ");
        }

        assertEquals("5 3 7 2 4 6 8 ", sb.toString());
    }

    @Test
    public void testLevelOrderTraversalInDegenerateBinaryTree() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(1);
        binaryTree.add(2);
        binaryTree.add(3);
        binaryTree.add(4);
        binaryTree.add(5);
        binaryTree.add(6);
        binaryTree.add(7);

        StringBuilder sb = new StringBuilder();
        for (Integer value : binaryTree) {
            sb.append(value).append(" ");
        }

        assertEquals("1 2 3 4 5 6 7 ", sb.toString());
    }
}
