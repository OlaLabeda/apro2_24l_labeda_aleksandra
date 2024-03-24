package lab2;
import static lab2.SpellCheck.spellChecker;
import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Jan Kozaczuk, Aleksandra Łabęda
 */
class SpellCheckTest {
    @BeforeAll
    static void init() {
        spellChecker.loadDictionary("src//lab2//dictionary.txt");
    }

    @Test
    void checkCorrectlySpelledWords() {
        assertTrue(spellChecker.check("machine"));
        assertTrue(spellChecker.check("melody"));
        assertTrue(spellChecker.check("mother"));
        assertTrue(spellChecker.check("moon"));
    }

    @Test
    void checkIncorrectlySpelledWords() {
        assertFalse(spellChecker.check("macheen"));
        assertFalse(spellChecker.check("melodee"));
        assertFalse(spellChecker.check("mothyr"));
        assertFalse(spellChecker.check("mooon"));
    }

    @Test
    void findNearestWords() {
        assertEquals("machine", spellChecker.findNearest("machine"));
        assertEquals("machine", spellChecker.findNearest("macheen"));

        assertEquals("melody", spellChecker.findNearest("melody"));
        assertEquals("melody", spellChecker.findNearest("melodee"));

        assertEquals("mother", spellChecker.findNearest("mother"));
        assertEquals("mother", spellChecker.findNearest("mothyr"));

        assertEquals("moon", spellChecker.findNearest("moon"));
        assertEquals("moon", spellChecker.findNearest("mooon"));
    }
}
