package lab2;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementacja drzewa "trie" w postaci klasy.
 * @author Jan Kozaczuk, Aleksandra Łabęda
 */
public class Trie {
    TrieNode root; // korzeń drzewa

    public Trie() {
        root = new TrieNode();
    }

    /**
     * Klasa reprezentująca węzeł drzewa "trie".
     */
    private class TrieNode {
        HashMap<Character, TrieNode> children; // zbiór dzieci węzła
        boolean isWord; // oznacza, czy znak kończy zdanie

        TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    /**
     * Umieszcza w strukturze "trie" podane słowo.
     * @param word dodawane słowo
     */
    public void add(String word) {
        // miejscem startowym podczas przechodzenia przez drzewo jest jego korzeń
        TrieNode current = root;

        // dodawany jest znak po znaku podanego słowa
        for(char c : word.toCharArray()) {
            // metoda 'computeIfAbsent' pozwala na stworzenie nowego węzła, jeśli bieżący nie posiada wśród swoich dzieci węzła odpowiadającego sprawdzanemu znakowi i go zwraca
            // natomiast w przypadku, gdy istnieje już taki węzeł, metoda go zwraca
            current = current.children.computeIfAbsent(c, character -> new TrieNode()); // przejście z węzła do jego dziecka, zwróconego przez metodę 'computeIfAbsent'
        }
        // ostatni znak podanego słowa zostaje oznaczony jako koniec słowa
        current.isWord = true;
    }

    /**
     * Sprawdza, czy podane słowo istnieje w strukturze "trie".
     * @param word sprawdzane słowo
     * @return true: jeśli istnieje; false: jeśli nie
     */
    public boolean check(String word) {
        // miejscem startowym podczas przechodzenia przez drzewo jest jego korzeń
        TrieNode current = root;

        // sprawdzany jest znak po znaku podanego słowa
        for(char c : word.toCharArray()) {
            // jeśli w którymkolwiek momencie nie występuje w strukturze sprawdzany znak, co oznacza, że nie ma w niej sprawdzanego słowa, to metoda zwraca false
            if(current.children.containsKey(c)) current = current.children.get(c); // przejście z węzła do jego dziecka, zawierającego sprawdzany znak
            else return false;
        }

        // zwraca 'isWord' ostatniego węzła, ponieważ metoda mogła znaleźć wszystkie kolejne znaki sprawdzanego słowa, które jednak nie zostało oznaczone w strukturze jako słowo
        return current.isWord;
    }

    /**
     * Znajduje najbliższe istniejące w strukturze "trie" słowo do podanego słowa.
     * @param word podane słowo
     * @return najbliższe słowo
     */
    public String findNearest(String word) {
        // miejscem startowym podczas przechodzenia przez drzewo jest jego korzeń
        TrieNode current = root;
        StringBuilder nearestWord = new StringBuilder(); // najbliższe słowo do podanego

        // sprawdzany jest znak po znaku podanego słowa
        for(char c : word.toCharArray()) {
            // jeśli w którymkolwiek momencie nie występuje w strukturze sprawdzany znak, co oznacza, że nie ma w niej sprawdzanego słowa, to metoda wychodzi z pętli for
            if(!current.children.containsKey(c)) break;

            // jeśli znak istnieje, to buduje najbliższe słowo
            nearestWord.append(c);
            current = current.children.get(c); // przejście z węzła do jego dziecka, zawierającego sprawdzany znak
        }

        // póki nie zostało znalezione słowo w strukturze, przechodzi po kolejnych węzłach aż znajdzie pierwsze najbliższe
        while(!current.isWord && !current.children.isEmpty()) {
            // bierze jako znak pierwsze dziecko bieżącego węzła
            char c = current.children.keySet().iterator().next();

            // znak ten buduje najbliższe słowo
            nearestWord.append(c);
            current = current.children.get(c); // przejście z węzła do jego dziecka, zawierającego sprawdzany znak
        }

        return nearestWord.toString();
    }

    /**
     * Wczytuje do struktury "trie" wszystkie słowa z podanego pliku tekstowego, który służy jako słownik.
     * @param filepath ścieżka do pliku ze słowami
     */
    public void loadDictionary(String filepath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String word;
            while((word = reader.readLine()) != null) this.add(word); // każde słowo w pliku znajduje się w osobnej linijce
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

