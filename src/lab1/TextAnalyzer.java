package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */

public class TextAnalyzer {
    public static void main(String[] args) {
        DynamicPriorityQueue<String> queue = new DynamicPriorityQueue<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src\\lab1\\sample.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    // Usuwanie interpunkcji i innych znaków niebędących literami z początku i końca słowa
                    String cleanedWord = word.replaceAll("^[^a-zA-Z']*", "").replaceAll("[^a-zA-Z']*$", "");
                    if (!cleanedWord.isEmpty()) {
                        queue.insert(cleanedWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Znajdowanie najkrótszego i najdłuższego słowa
        String shortestWord = queue.findShortestWord();
        String longestWord = queue.findLongestWord();

        System.out.println("Najkrótsze słowo: " + shortestWord);
        System.out.println("Najdłuższe słowo: " + longestWord);

        // Znajdowanie pierwszego i ostatniego słowa w porządku leksykograficznym
        String firstWord = queue.findFirstLexicographicWord();
        String lastWord = queue.findLastLexicographicWord();

        System.out.println("Pierwsze słowo: " + firstWord);
        System.out.println("Ostatnie słowo: " + lastWord);
        queue.removeShortestWord();
        queue.removeLongestWord();

        System.out.println("Usunięcie najkrótszego i najdłuższego słowa");
        // Znajdowanie najkrótszego i najdłuższego słowa
        String shortestWord1 = queue.findShortestWord();
        String longestWord1 = queue.findLongestWord();

        System.out.println("Najkrótsze słowo: " + shortestWord1);
        System.out.println("Najdłuższe słowo: " + longestWord1);

        // Znajdowanie pierwszego i ostatniego słowa w porządku leksykograficznym
        String firstWord1 = queue.findFirstLexicographicWord();
        String lastWord1 = queue.findLastLexicographicWord();

        System.out.println("Pierwsze słowo: " + firstWord1);
        System.out.println("Ostatnie słowo: " + lastWord1);


        System.out.println("Usunięcie pierwszego i ostatniego słowa w porządku leksykograficznym");
        queue.removeFirstLexicographicWord();
        queue.removeLastLexicographicWord();

        // Znajdowanie najkrótszego i najdłuższego słowa
        String shortestWord2 = queue.findShortestWord();
        String longestWord2 = queue.findLongestWord();

        System.out.println("Najkrótsze słowo: " + shortestWord2);
        System.out.println("Najdłuższe słowo: " + longestWord2);

        // Znajdowanie pierwszego i ostatniego słowa w porządku leksykograficznym
        String firstWord2 = queue.findFirstLexicographicWord();
        String lastWord2 = queue.findLastLexicographicWord();

        System.out.println("Pierwsze słowo: " + firstWord2);
        System.out.println("Ostatnie słowo: " + lastWord2);
    }

}
