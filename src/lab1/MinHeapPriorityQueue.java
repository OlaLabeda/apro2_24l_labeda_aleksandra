package lab1;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Kolejka prioretytowa zaimplementowana na kopcu binarnym, gdzie pierwszy jest element minimalny.
 * @param <T> typ implementujący interfejs Comparable
 *
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
public class MinHeapPriorityQueue<T extends Comparable<T>> {
    protected ArrayList<T> queue;

    public MinHeapPriorityQueue() {
        queue = new ArrayList<>();
    }

    /**
     * Wsadza podany element do kopca i odpowiednio ten element przemieszcza.
     * @param e wsadzany element typu T
     */
    public void insert(T e) {
        // dodaje do kopca element, po czym idzie do góry, aż nowy element jest na odpowiedniej pozycji
        queue.add(e);
        moveUp(queue.size() - 1);
    }

    /**
     * Usuwa pierwszy element z kopca, po czym odpowiednio przekształca kopiec.
     */
    public void deleteRoot() {
        if (isEmpty()) throw new NoSuchElementException("Kolejka jest pusta");

        // ustawia jako pierwszy element ostatni, po czym idzie w dół zamieniając elementy kopca tak, by zachować jego odpowiednią strukturę
        queue.set(0, queue.get(size() - 1)); // index + wartosc
        queue.remove(size() - 1);
        if (!isEmpty()) moveDown(0);
    }

    /**
     * Zwraca pierwszy element na kopcu.
     * Ponieważ metoda nie szuka elementu, operacja jest wykonywana w stałym czasie.
     * @return Pierwszy element na kopcu
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Kolejka jest pusta");
        return queue.getFirst();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Sprawdza, czy element powinien być wyżej w kopcu i jeśli tak, to zmienia jego pozycję.
     * Metoda wykonuje się w czasie logarytmicznym, ponieważ w najgorszym przypadku, gdzie musi przejść przez całą wysokość kopca, to wysokość jest równa log N, gdzie N to liczba elementów
     * @param index pozycja elementu
     */
    protected void moveUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (queue.get(index).compareTo(queue.get(parentIndex)) < 0) {
                T temp = queue.get(index);
                queue.set(index, queue.get(parentIndex));
                queue.set(parentIndex, temp);

                index = parentIndex;
            } else break;
        }
    }

    /**
     * Sprawdza, czy element powinien być niżej w kopcu i jeśli tak, to zmienia jego pozycję.
     * Metoda wykonuje się w czasie logarytmicznym, ponieważ w najgorszym przypadku, gdzie musi przejść przez całą wysokość kopca, to wysokość jest równa log N, gdzie N to liczba elementów
     * @param index pozycja elementu
     */
    protected void moveDown(int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;
        int newIndex = index;

        if (leftIndex < size() && queue.get(leftIndex).compareTo(queue.get(newIndex)) < 0) newIndex = leftIndex;
        if (rightIndex < size() && queue.get(rightIndex).compareTo(queue.get(newIndex)) < 0) newIndex = rightIndex;

        if (newIndex != index) {
            T temp = queue.get(index);
            queue.set(index, queue.get(newIndex));
            queue.set(newIndex, temp);
            moveDown(newIndex);
        }
    }
}
