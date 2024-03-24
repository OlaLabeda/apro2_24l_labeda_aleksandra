package lab2;

/**
 * Generyczna klasa reprezentująca pojedynczy węzeł drzewa binarnego.
 * @param <T> typ implementujący interfejs Comparable
 *
 * @author Jan Kozaczuk, Aleksandra Łabęda
 */
class Node<T extends Comparable<T>> {
    T value; // wartość węzła
    Node<T> leftChild, rightChild; // lewe i prawe dziecko

    public Node(T value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Metoda rekurencyjnie przechodzi przez kolejne węzły, aż znajdzie miejsce dla podanego elementu.
     * @param e umieszczany element
     */
    public void insert(T e) {
        // jeśli sprawdzany węzeł nie ma ustawionej wartości, to przyjmuje wartość podanego elementu
        if(this.value == null) {
            this.value = e;
            return;
        }

        // jeśli sprawdzany węzeł ma już ustawioną wartość, to na jej podstawie sprawdzane jest lewe lub prawe dziecko
        if(e.compareTo(this.value) < 0) {
            // jeśli lewe dziecko jeszcze nie istnieje, to zostaje stworzone i przyjmuje wartość podanego elementu
            // natomiast jeśli już istnieje, to rekurencyjnie zostają sprawdzone dzieci lewego dziecka
            if(leftChild == null) leftChild = new Node<>(e);
            else leftChild.insert(e);
        } else {
            // jeśli prawe dziecko jeszcze nie istnieje, to zostaje stworzone i przyjmuje wartość podanego elementu
            // natomiast jeśli już istnieje, to rekurencyjnie zostają sprawdzone dzieci prawego dziecka
            if(rightChild == null) rightChild = new Node<>(e);
            else rightChild.insert(e);
        }
    }
}
