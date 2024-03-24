package lab2;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementacja drzewa binarnego w postaci generycznej klasy.
 * @param <T> typ implementujący interfejs Comparable
 *
 * @author Jan Kozaczuk, Aleksandra Łabęda
 */
public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {
    private final Node<T> root; // korzeń drzewa binarnego

    public BinaryTree() {
        this.root = new Node<>(null);
    }

    /**
     * Umieszcza podany element w drzewie binarnym.
     * @param e umieszczany element
     */
    public void insert(T e) {
        root.insert(e);
    }

    /**
     * Drukuje trzymane w drzewie wartości poziom po poziomie, od lewej do prawej.
     */
    public void print() {
        for(T value : this) {
            System.out.print(value + " ");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    /**
     * Zagnieżdżona klasa implementująca interfejs Iterator.
     * Iterator przechodzi po każdym węźle drzewa poziom po poziomie, od lewej do prawej.
     */
    private class TreeIterator implements Iterator<T> {
        // iterator przechodzi po drzewie w oparciu na kolejce węzłów
        private final LinkedList<Node<T>> queue;

        public TreeIterator() {
            queue = new LinkedList<>();
            if(root != null) queue.add(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();

            // jako bieżący węzeł wyciągany jest pierwszy węzeł z kolejki
            Node<T> current = queue.poll();
            if(current == null) throw new NullPointerException();

            // istniejące dzieci bieżącego węzła zostają dodane na koniec kolejki
            if(current.leftChild != null) queue.add(current.leftChild);
            if(current.rightChild != null) queue.add(current.rightChild);

            // zwraca wartość bieżącego węzła
            return current.value;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        BinaryTree<String> stringTree = new BinaryTree<>();

        intTree.insert(3);
        intTree.insert(4);
        intTree.insert(7);
        intTree.insert(3);
        intTree.insert(2);
        intTree.insert(2);
        intTree.insert(1);

        stringTree.insert("bb");
        stringTree.insert("cb");
        stringTree.insert("cc");
        stringTree.insert("bc");
        stringTree.insert("ab");
        stringTree.insert("ac");
        stringTree.insert("ca");
        stringTree.insert("ba");
        stringTree.insert("aa");

        System.out.println("Pierwsze drzewo:");
        intTree.print();
        System.out.println("\nDrugie drzewo:");
        stringTree.print();
    }
}


