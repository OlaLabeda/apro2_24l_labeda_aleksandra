package lab1;

import java.util.*;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */

public class DynamicPriorityQueue<T extends Comparable<T>> {
    private final List<T> heap;
    private final Map<T, Integer> wordCountMap;

    public DynamicPriorityQueue() {
        heap = new ArrayList<>();
        wordCountMap = new HashMap<>();
    }
    /**
     * Wstawia nowe słowo do kolejki i aktualizuje licznik jego wystąpień.
     * @param item Nowe słowo.
     */
    public void insert(T item) {
        String trimmedItem = item.toString().trim();
        if (!trimmedItem.isEmpty()) {
            heap.add((T) trimmedItem);
            updateWordCount((T) trimmedItem); // Aktualizacja licznika wystąpień
            swim(heap.size() - 1);
        }
    }


    /**
     * Zwraca mapę zliczającą wystąpienia każdego słowa.
     *
     * @return Mapa zliczająca wystąpienia słów.
     */
    public Map<T, Integer> getWordCountMap() {
        return wordCountMap;
    }

    /**
     * Aktualizuje licznik wystąpień słowa.
     * @param word Słowo, którego wystąpienie ma zostać zaktualizowane.
     */
    private void updateWordCount(T word) {
        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
    }

    public void removeAllMostFrequentWords() {
        int maxFrequency = Collections.max(wordCountMap.values());
        removeWordsByFrequency(maxFrequency);
    }

    public void removeAllLeastFrequentWords() {
        int minFrequency = Collections.min(wordCountMap.values());
        removeWordsByFrequency(minFrequency);
    }

    private void removeWordsByFrequency(int frequency) {
        List<T> wordsToRemove = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() == frequency) {
                wordsToRemove.add(entry.getKey());
            }
        }
        for (T word : wordsToRemove) {
            heap.remove(word);
            wordCountMap.remove(word);
        }
    }
    public List<T> findMostFrequentWords() {
        int maxCount = 0;
        List<T> mostFrequentWords = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : wordCountMap.entrySet()) {
            int count = entry.getValue();
            if (count > maxCount) {
                mostFrequentWords.clear();
                mostFrequentWords.add(entry.getKey());
                maxCount = count;
            } else if (count == maxCount) {
                mostFrequentWords.add(entry.getKey());
            }
        }
        return mostFrequentWords;
    }

    public List<T> findLeastFrequentWords() {
        int minCount = Integer.MAX_VALUE;
        List<T> leastFrequentWords = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : wordCountMap.entrySet()) {
            int count = entry.getValue();
            if (count < minCount) {
                leastFrequentWords.clear();
                leastFrequentWords.add(entry.getKey());
                minCount = count;
            } else if (count == minCount) {
                leastFrequentWords.add(entry.getKey());
            }
        }
        return leastFrequentWords;
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
