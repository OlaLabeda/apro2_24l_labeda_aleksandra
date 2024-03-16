package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */

public class DynamicPriorityQueue<T extends Comparable<T>> {
    private final List<T> heap;

    public DynamicPriorityQueue() {
        heap = new ArrayList<>();
    }

    /**
     * wstawia nowy element do kolejki i przeprowadza operację swim(), aby przywrócić własności kopca.
     *
     * @param item
     */
    public void insert(T item) {
        String trimmedItem = item.toString().trim();
        if (!trimmedItem.isEmpty() && !heap.contains(trimmedItem)) {
            heap.add((T) trimmedItem);
            swim(heap.size() - 1);
        }
    }

    public void removeShortestWord() {
        T shortestWord = findShortestWord();
        if (shortestWord != null) {
            heap.remove(shortestWord);
        }
    }

    public void removeLongestWord() {
        T longestWord = findLongestWord();
        if (longestWord != null) {
            heap.remove(longestWord);
        }
    }

    public void removeFirstLexicographicWord() {
        T firstLexicographicWord = findFirstLexicographicWord();
        if (firstLexicographicWord != null) {
            heap.remove(firstLexicographicWord);
        }
    }

    public void removeLastLexicographicWord() {
        T lastLexicographicWord = findLastLexicographicWord();
        if (lastLexicographicWord != null) {
            heap.remove(lastLexicographicWord);
        }
    }


    /**
     * zwraca true, jeśli kolejka jest pusta, w przeciwnym razie false
     *
     * @return
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Ta funkcja jest używana po dodaniu nowego elementu na końcu kopca.
     * Element porusza się "w górę" kopca, porównując się z rodzicem. Jeśli wartość elementu
     * jest mniejsza niż jego rodzic, zamieniane są miejscami. Proces ten powtarza się,
     * aż element osiągnie swoje właściwe miejsce w kopcu. Ta operacja zapewnia, że struktura kopca
     * jest zachowana i że nowo dodany element jest na właściwym miejscu.
     */
    private void swim(int k) {
        while (k > 0 && greater(k / 2, k)) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * Ta funkcja jest używana po usunięciu korzenia kopca (czyli elementu o najwyższej/lub najniższej wartości).
     * Gdy korzeń zostaje usunięty, ostatni element kopca zajmuje jego miejsce. Następnie ten ostatni element
     * "opuszcza się" w dół kopca, porównując się z dziećmi. Zamienia się miejscami z większym dzieckiem, jeśli
     * wartość dziecka jest większa od jego własnej wartości. Ten proces powtarza się, aż element osiągnie swoje
     * właściwe miejsce w kopcu. Ta operacja zapewnia, że struktura kopca jest zachowana po usunięciu korzenia.
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k + 1 < heap.size()) {
            int j = 2 * k + 1;
            if (j < heap.size() - 1 && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /**
     * zwraca true, jeśli element i jest większy od elementu j, w przeciwnym razie false
     *
     * @param i
     * @param j
     * @return
     */
    private boolean greater(int i, int j) {
        return heap.get(i).compareTo(heap.get(j)) > 0;
    }

    /**
     * zamienia miejscami elementy i i j w kopcu
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    public T findShortestWord() {
        T shortestWord = null;
        for (T item : heap) {
            if (shortestWord == null || item.toString().length() < shortestWord.toString().length()) {
                shortestWord = item;
            }
        }
        return shortestWord;
    }

    public T findLongestWord() {
        T longestWord = null;
        for (T item : heap) {
            if (longestWord == null || item.toString().length() > longestWord.toString().length()) {
                longestWord = item;
            }
        }
        return longestWord;
    }

    public T findFirstLexicographicWord() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        T firstLexicographicWord = heap.get(0);
        for (T item : heap) {
            if (item.compareTo(firstLexicographicWord) < 0) {
                firstLexicographicWord = item;
            }
        }
        return firstLexicographicWord;
    }

    public T findLastLexicographicWord() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        T lastLexicographicWord = heap.get(0);
        for (T item : heap) {
            if (item.compareTo(lastLexicographicWord) > 0) {
                lastLexicographicWord = item;
            }
        }
        return lastLexicographicWord;
    }

}
