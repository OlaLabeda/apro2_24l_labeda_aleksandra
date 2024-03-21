package lab2;

import java.io.*;
//import java.util.ArrayList;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 *
 * Main class for the SpellChecker application
 */
public class SpellCheckerMain {
    public static void main (String[] args) {
        SpellChecker spellChecker = new SpellChecker();
        // ArrayList<String> words = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/lab2/dictionary.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                spellChecker.add(line);
             //   words.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occured while reading from file " + e.getMessage());
            e.printStackTrace();
            return;
        }
        /*  sorting a given list and overwriting the file, so it's in lexicographic order (it was not required in the task,
        but the file used for testing purposes was unordered, so for the sake of simplicity, the data was sorted and saved to the file)
        words.sort(String::compareToIgnoreCase);
        for (String word : words) {
            System.out.println(word);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/lab2/dictionary.txt"))) {
            for (String word : words) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occured while writing to file " + e.getMessage());
            e.printStackTrace();
        }
        */

        //testing the application in terminal
        System.out.println("Is 'apple' spelled correctly? " + spellChecker.check("apple")); // Output: true
        System.out.println("Closest match to 'aple' is:  " + spellChecker.suggestClosestMatch("aple")); // Output: false
        System.out.println("Is 'grap' spelled correctly? " + spellChecker.check("grap")); // Output: false
        System.out.println("Closest match to a given word is:  " + spellChecker.suggestClosestMatch("grap"));
    }

}
