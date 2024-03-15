package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.Properties;

/**
 * @author Aleksandra Łabęda, Jan Kozaczuk
 */
public class XMLValidator {
    private static final String PROPERTIES_FILE_PATH = "src\\lab1\\test1.txt";

    public static void main(String[] args) {
        try {
            //wczytaj wlasciwosci z pliku
            Properties properties = readPropertiesFile(PROPERTIES_FILE_PATH);
            if (properties == null) {
                System.out.println("Błąd wczytywania pliku właściwości.");
                return;
            }
            //sciezka do pliku XML
            String xmlFilePath = "src\\lab1\\test1.xml";
            System.out.print("Plik test1: ");
            //sprawdz walidacje pliku XML
            chechValidation(xmlFilePath, properties);

            xmlFilePath = "src\\lab1\\test1fail.xml";
            System.out.print("Plik test1fail: ");
            chechValidation(xmlFilePath, properties);
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas wczytywania pliku: " + e.getMessage());
        }
    }

    private static Properties readPropertiesFile(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            //wczytaj wlasciwosci z pliku tekstowego
            properties.load(bufferedReader);
        }
        return properties;
    }

    public static void chechValidation (String xmlFilePath, Properties properties) throws IOException {
        if (validateXML(xmlFilePath, properties)) {
            System.out.println("Plik XML jest poprawny.");
        } else {
            System.out.println("Plik XML jest niepoprawny.");
        }
    }

    private static boolean validateXML(String filePath, Properties properties) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Stack<String> stack = new Stack<>();
            String line;
            final int count = Integer.parseInt(properties.getProperty("count"));
            String[] tags = new String[count];
            for (int i = 0; i < count; i++) {
                final String propertyName = "e" + (i + 1);
                final String markUp = properties.getProperty(propertyName);
                tags[i]=markUp;
            }
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("[<>]");
                for(String word : tokens){
                    if (!word.startsWith("/")) {
                        // jesli tag nie jest zamkniety dodaj go do stosu
                        if(contains(tags, word)) {
                            stack.push(word);
                        }
                    } else {
                        // jesli tag jest zamkniety sprawdz poprawnosc i usun ze stosu
                        word = word.substring(1);
                        if(!contains(tags, word)) {
                            stack.push(word);
                        }
                        if (stack.isEmpty() || !stack.pop().equals(word)) {
                            return false;
                        }
                    }
                }
            }
            //sprawdz czy stos jest ousty po zakonczeniu przetwarzania
            return stack.isEmpty();
        }
    }

    public static boolean contains(String[] tags, String word){
        //sprawdz czy dany tag znajduje sie w tablicy tagow
        for(String tag:tags){
            if(tag.equals(word))
                return true;
        }
        return false;
    }
}
