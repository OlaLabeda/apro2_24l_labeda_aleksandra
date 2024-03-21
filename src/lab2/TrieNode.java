package lab2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 *
 * A class respresenting a node of a trie tree
 * trie tree is a prefix tree where each node represents a letter of the alphabet
 * and a path from the root to a node represents a word
 */
public class TrieNode {
    //map storing children of a given node
    //keys are individual letters of the alphabet and values are corresponding trie nodes
    Map<Character, TrieNode> children;
    //marks if a given path in the tree represents a full word
    //true => path represents a full word
    boolean isEndOfWord;
    public TrieNode(){
        //initialization of the map using hashmap
        //creates an empty map ready to store children
        this.children = new HashMap<>();
        //in the beginning we are not at the end of any word
        this.isEndOfWord = false;
    }

}
