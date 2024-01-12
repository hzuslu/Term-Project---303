
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Checker spellChecker = new Checker();
        String inputText = "Thes are some misspelled words like aplle, banan and oragne.";
        spellChecker.is_it_valid(inputText);
        List<String> misspelledWords = spellChecker.getMisspelledWords();

        for (String misspelledWord : misspelledWords) {
            List<String> suggestions = spellChecker.findSuggestions(misspelledWord);
            System.out.println("Suggestions for " + misspelledWord + ": " + suggestions);
        }
    }

     }
