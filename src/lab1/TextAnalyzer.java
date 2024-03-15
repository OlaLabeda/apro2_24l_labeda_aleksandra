package lab1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
public class TextAnalyzer {
    public static void main(String[] args) {
        try {
            List<String> words = readWordsFromFile("src//lab1//sample.txt");

            // Rozwiązanie problemu 1: Najkrótsze i najdłuższe słowa
            System.out.println("Najkrótsze słowo: " + findShortestWord(words));
            System.out.println("Najdłuższe słowo: " + findLongestWord(words));

            // Rozwiązanie problemu 2: Słowo pierwsze i ostatnie w porządku leksykograficznym
            System.out.println("Pierwsze słowo w porządku leksykograficznym: " + findFirstWord(words));
            System.out.println("Ostatnie słowo w porządku leksykograficznym: " + findLastWord(words));

            // Rozwiązanie problemu 3: Najczęściej i najrzadziej występujące słowo
            System.out.println("Najczęściej występujące słowo: " + findMostFrequentWord(words));
            System.out.println("Najrzadziej występujące słowo: " + findLeastFrequentWord(words));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readWordsFromFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+");
                words.addAll(Arrays.asList(lineWords));
            }
        }
        return words;
    }

    private static String findShortestWord(List<String> words) {
        return Collections.min(words, Comparator.comparing(String::length));
    }

    private static String findLongestWord(List<String> words) {
        return Collections.max(words, Comparator.comparing(String::length));
    }

    private static String findFirstWord(List<String> words) {
        return Collections.min(words);
    }

    private static String findLastWord(List<String> words) {
        return Collections.max(words);
    }

    private static String findMostFrequentWord(List<String> words) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static String findLeastFrequentWord(List<String> words) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
        return Collections.min(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
