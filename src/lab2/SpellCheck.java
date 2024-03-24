package lab2;

/**
 * Sprawdzanie pisowni słów.
 * @author Jan Kozaczuk, Aleksandra Łabęda
 */
public class SpellCheck {
    static Trie spellChecker = new Trie();

    public static void main(String[] args) {
        spellChecker.loadDictionary("src//lab2//dictionary.txt");

        checkSpelling("machine");
        checkSpelling("macheen");

        checkSpelling("melody");
        checkSpelling("melodee");

        checkSpelling("mother");
        checkSpelling("mothyr");

        checkSpelling("moon");
        checkSpelling("mooon");
    }

    public static void checkSpelling(String word) {
        boolean spelling = spellChecker.check(word);
        System.out.println("Is \"" + word + "\" spelled correctly? " + (spelling ? "Yes." : "No."));
        if(!spelling) System.out.println(">> Nearest found word for \"" + word + "\": " + spellChecker.findNearest(word) + "\n");
    }
}

