package lab2;
import org.junit.jupiter.api.Test;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 *
 * Test class for SpellChecker
 */
public class SpellCheckerTest {
    @Test
    public void testCheck() {
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.add("apple");
        spellChecker.add("grape");
        spellChecker.add("banana");

        assert(spellChecker.check("apple") == true);
        assert(spellChecker.check("grape") == true);
        assert(spellChecker.check("banana") == true);
    }

    @Test
    public void suggestClosesMatch() {
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.add("apple");
        spellChecker.add("grape");
        assert (spellChecker.suggestClosestMatch("aple").equals("apple"));
        assert (spellChecker.suggestClosestMatch("grape").equals("grape"));
        assert (spellChecker.suggestClosestMatch("banan").equals("apple"));
    }
}
