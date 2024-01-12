package project.checker;

import project.fetcher.FetchDictionary;
import project.keygraph.KeyboardGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Checker implements IChecker{
    private FetchDictionary myDictionary = new FetchDictionary();
    private ArrayList<String> misspelledWords = new ArrayList<>();
    private HashSet<String> dictionary = myDictionary.buildHashSet("spellChecker/common_words.txt");
    private KeyboardGraph turkishQKeyboardGraph = new KeyboardGraph();

    public Checker() {
        turkishQKeyboardGraph.buildGraph();
    }

    public List<String> getMisspelledWords() {
        return this.misspelledWords;
    }

    public void isTextValid(String text) {
        String[] words = text.split("[\\s.,;!?]+");
        for (String word : words) {
            if (!myDictionary.searchWord(dictionary, word)) {
                misspelledWords.add(word.toLowerCase());
            }
        }

        if (!misspelledWords.isEmpty()) {
            System.out.println("Misspelled words:");
            for (String misspelledWord : misspelledWords) {
                System.out.println(misspelledWord);
            }
        }
    }

    public List<String> findSuggestions(String misspelled) {
        List<String> suggestions = new ArrayList<>();
        List<String> filteredSuggestions = new ArrayList<>();

        for (String word : dictionary) {
            int distance = calculateEditDistance(misspelled, word);

            if (distance <= 2) {
                suggestions.add(word);
            }
        }

        for (String suggestion : suggestions) {
            int totalDistance = 0;
            for (int i = 0; i < Math.min(suggestion.length(), misspelled.length()); i++) {
                char startNode = misspelled.charAt(i);
                char endNode = suggestion.charAt(i);
                int distance = turkishQKeyboardGraph.calculateMinDistance(startNode, endNode);
                totalDistance += distance;
            }
            if (totalDistance <= 2) {
                filteredSuggestions.add(suggestion);
            }
        }

        return filteredSuggestions;
    }

    public int calculateEditDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int replace = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 2;

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + replace
                );
            }
        }

        return dp[m][n];
    }
}
