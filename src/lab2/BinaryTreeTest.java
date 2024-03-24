package lab2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jan Kozaczuk, Aleksandra Łabęda
 */
class BinaryTreeTest {
    @Test
    void iteratorOnRegularIntegerTree() {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        StringBuilder treePrintout = new StringBuilder();

        intTree.insert(3);  // Drzewo:    3
        intTree.insert(4);  //          2   4
        intTree.insert(7);  //         1 2 3 7
        intTree.insert(3);
        intTree.insert(2);
        intTree.insert(2);
        intTree.insert(1);

        // spodziewany wydruk: "3 2 4 1 2 3 7 "
        for(Integer value : intTree) {
            treePrintout.append(value).append(' ');
        }

        assertEquals("3 2 4 1 2 3 7 ", treePrintout.toString());
    }

    @Test
    void iteratorOnRegularStringTree() {
        BinaryTree<String> stringTree = new BinaryTree<>();
        StringBuilder treePrintout = new StringBuilder();

        stringTree.insert("bb");  // Drzewo:      bb
        stringTree.insert("cb");  //          ab      cb
        stringTree.insert("cc");  //        aa  ac  bc  cc
        stringTree.insert("bc");  //            ba  ca
        stringTree.insert("ab");
        stringTree.insert("ac");
        stringTree.insert("ca");
        stringTree.insert("ba");
        stringTree.insert("aa");

        // spodziewany wydruk: "bb ab cb aa ac bc cc ba ca "
        for(String str : stringTree) {
            treePrintout.append(str).append(' ');
        }

        assertEquals("bb ab cb aa ac bc cc ba ca ", treePrintout.toString());
    }

    @Test
    void iteratorOnDegenerateIntegerTree() {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        StringBuilder treePrintout = new StringBuilder();

        intTree.insert(1);  // Drzewo:    1
        intTree.insert(2);  //            2
        intTree.insert(2);  //            2
        intTree.insert(3);  //            3
        intTree.insert(3);  //            3
        intTree.insert(4);  //            4
        intTree.insert(7);  //            7

        // spodziewany wydruk: "1 2 2 3 3 4 7 "
        for(Integer value : intTree) {
            treePrintout.append(value).append(' ');
        }

        assertEquals("1 2 2 3 3 4 7 ", treePrintout.toString());
    }

    @Test
    void iteratorOnDegenerateStringTree() {
        BinaryTree<String> stringTree = new BinaryTree<>();
        StringBuilder treePrintout = new StringBuilder();

        stringTree.insert("aa");  // Drzewo:      aa
        stringTree.insert("ab");  //              ab
        stringTree.insert("ac");  //              ac
        stringTree.insert("ba");  //              ba
        stringTree.insert("bb");  //              bb
        stringTree.insert("bc");  //              bc
        stringTree.insert("ca");  //              ca
        stringTree.insert("cb");  //              cb
        stringTree.insert("cc");  //              cc

        // spodziewany wydruk: "aa ab ac ba bb bc ca cb cc "
        for(String str : stringTree) {
            treePrintout.append(str).append(' ');
        }

        assertEquals("aa ab ac ba bb bc ca cb cc ", treePrintout.toString());
    }
}

