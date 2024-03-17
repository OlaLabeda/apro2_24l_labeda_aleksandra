package lab1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.text.Normalizer;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
public class TextAnalyzer {
    public static void main(String[] args) {
        // Kolejki na kopcu dla najkrótszego i najdłuższego słowa
        MinHeapPriorityQueue<WordLength> shortest = new MinHeapPriorityQueue<>();
        MaxHeapPriorityQueue<WordLength> longest = new MaxHeapPriorityQueue<>();

        // Kolejki na kopcu dla pierwszego i ostatniego słowa w porządku leksykograficznym
        MinHeapPriorityQueue<WordOrder> firstLexicographically = new MinHeapPriorityQueue<>();
        MaxHeapPriorityQueue<WordOrder> lastLexicographically = new MaxHeapPriorityQueue<>();

        // Kolejki na kopcu dla najczęstszego i najrzadszego słowa
        MinHeapPriorityQueue<WordFrequency> leastFrequent = new MinHeapPriorityQueue<>();
        MaxHeapPriorityQueue<WordFrequency> mostFrequent = new MaxHeapPriorityQueue<>();

        // Mapa słów wraz z liczbą ich wystąpień
        HashMap<String, Integer> words = new HashMap<>();

        // Wczytanie słów z pliku
        try {
            words = readWordsFromFile("src//lab1//sample.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Wsadzanie do kopców słów z mapy
        for(HashMap.Entry<String, Integer> entry : words.entrySet()) {
            shortest.insert(new WordLength(entry.getKey()));
            longest.insert(new WordLength(entry.getKey()));

            firstLexicographically.insert(new WordOrder(entry.getKey()));
            lastLexicographically.insert(new WordOrder(entry.getKey()));

            leastFrequent.insert(new WordFrequency(entry.getKey(), entry.getValue()));
            mostFrequent.insert(new WordFrequency(entry.getKey(), entry.getValue()));
        }

        // Rozwiązanie problemu 1: Najkrótsze i najdłuższe słowa
        System.out.println("Najkrótsze słowo: " + shortest.peek().word);
        System.out.println("Najdłuższe słowo: " + longest.peek().word + "\n");

        // Rozwiązanie problemu 2: Słowo pierwsze i ostatnie w porządku leksykograficznym
        System.out.println("Pierwsze słowo w porządku leksykograficznym: " + firstLexicographically.peek().word);
        System.out.println("Ostatnie słowo w porządku leksykograficznym: " + lastLexicographically.peek().word + "\n");

        // Rozwiązanie problemu 3: Najczęściej i najrzadziej występujące słowo
        System.out.println("Najczęściej występujące słowo: " + mostFrequent.peek().word);
        System.out.println("Najrzadziej występujące słowo: " + leastFrequent.peek().word + "\n");

        // Usuwanie elementów minimalnych i maksymalnych
        shortest.deleteRoot();
        longest.deleteRoot();
        firstLexicographically.deleteRoot();
        lastLexicographically.deleteRoot();
        mostFrequent.deleteRoot();
        leastFrequent.deleteRoot();
        System.out.println("Usunięcie minimalnych i maksymalnych elementów.\n");

        // Sprawdzenie nowych minimalnych i maksymalnych słów
        System.out.println("Nowe najkrótsze słowo: " + shortest.peek().word);
        System.out.println("Nowe najdłuższe słowo: " + longest.peek().word + "\n");

        System.out.println("Nowe pierwsze słowo w porządku leksykograficznym: " + firstLexicographically.peek().word);
        System.out.println("Nowe ostatnie słowo w porządku leksykograficznym: " + lastLexicographically.peek().word + "\n");

        System.out.println("Nowe najczęściej występujące słowo: " + mostFrequent.peek().word);
        System.out.println("Nowe najrzadziej występujące słowo: " + leastFrequent.peek().word + "\n");

    }

    /**
     * Wczytuje słowa z podanego pliku do mapy
     * @param filePath ścieżka do pliku tekstowego ze słowami
     * @return Mapę zawierającą wczytane z pliku słowa wraz z liczbą ich wystąpień
     */
    private static HashMap<String,Integer> readWordsFromFile(String filePath) throws IOException {
        HashMap<String, Integer> words = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // dzieli wczytaną linię tekstu w miejsach, gdzie znajdują się specjalne znaki lub spacja
                String[] lineWords = line.split("\\s+");

                for(String word : lineWords) {
                    // ze słowa zostają usunięte wszelkie akcenty oraz zostaje ono zapisane w całości z małej litery
                    word = stripAccents(word.toLowerCase());
                    word = word.replaceAll("^[^a-z']*", "").replaceAll("[^a-z']*$", "");
                    // słowo zostaje dodane do mapy słów
                    if(!word.isBlank()) words.put(word, words.getOrDefault(word, 0) + 1);
                }
            }
        }
        return words;
    }

    /**
     * Usuwa wszelkie akcentowane litery w słowie, podmieniając je na zwykłe litery
     * @param input słowo z akcentowanymi literami
     * @return Znormalizowane słowo
     */
    public static String stripAccents(String input) {
        if (input == null) return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}

/**
 * Klasa WordLength trzyma słowo w Stringu
 * Metoda compareTo tej klasy porównuje długość dwóch słów
 */
class WordLength implements Comparable<WordLength> {
    public String word;

    public WordLength(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(WordLength other) {
        return this.word.length() - other.word.length();
    }
}

/**
 * Klasa WordOrder trzyma słowo w Stringu
 * Metoda compareTo tej klasy porównuje, które ze słów jest pierwsze w porządku leksykograficznym
 */
class WordOrder implements Comparable<WordOrder> {
    public String word;

    public WordOrder(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(WordOrder other) {
        return this.word.compareToIgnoreCase(other.word);
    }
}

/**
 * Klasa WordFrequency trzyma słowo w Stringu, oraz liczbę jego wystąpień w tekście w postaci Integera
 * Metoda compareTo tej klasy porównuje, którego słowa liczba wystąpień jest większa
 */
class WordFrequency implements Comparable<WordFrequency> {
    public String word;
    public int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(WordFrequency other) {
        return this.frequency - other.frequency;
    }
}