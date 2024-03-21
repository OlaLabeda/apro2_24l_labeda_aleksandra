package lab2;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 * A SpellChecker class using Trie data structure to store words and check if a given word is in the dictionary
 * and suggest the closest match if the word is not in the dictionary
 */
public class SpellChecker {

    private TrieNode root;
    // initialize the root of the trie
    public SpellChecker() {
        this.root = new TrieNode();
    }

    /**
     * Adds a word to the trie
     * @param word
     */
    public void add(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            //create a new node if the path does not exist
            current.children.putIfAbsent(c, new TrieNode());
            //move to the next node
            current = current.children.get(c);
        }
        //mark the end of the word
        current.isEndOfWord = true;
    }

    /**
     * Checks if a word is in the trie
     * @param word
     * @return
     */
    public boolean check(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            //if the path does not exist, the word is not in the trie
            if (!current.children.containsKey(c)) {
                return false;
            }
            //move to the next node
            current = current.children.get(c);
        }
        //if the path exists, check if it is the end of the word
        return current.isEndOfWord;
        //if it's false, it means the word is a prefix of another word in the trie
    }


    /**
     * Suggests the closest match to a given word
     * @param word
     * @return
     */
    public String suggestClosestMatch(String word) {
        TrieNode current = root;
        StringBuilder closestMatch = new StringBuilder();
        for (char c : word.toCharArray()) {
            //search for the longest common prefix
            if (!current.children.containsKey(c)) {
                break;
            }
            //add the character to the closest match
            closestMatch.append(c);
            //move to the next node
            current = current.children.get(c);
        }
        //if the current node is not the end of the word, find the closest node
        //that is the end of the word in the trie and add it to the closest match
        if (!current.isEndOfWord) {
            while (!current.isEndOfWord && !current.children.isEmpty()) {
                //add the next character to the closest match
                char nextChar = current.children.keySet().iterator().next();
                closestMatch.append(nextChar);
                current = current.children.get(nextChar);
            }
        }
        return closestMatch.toString();
    }


}
