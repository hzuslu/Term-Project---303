package project.corrector;

import project.checker.Checker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SentenceCorrector implements ISentenceCorrector {

    private char lastCharacter;

    public void processInputText(Checker spellChecker, String inputText) {
        spellChecker.isTextValid(inputText);
        lastCharacter = inputText.charAt(inputText.length() - 1);

        List<String> misspelledWords = spellChecker.getMisspelledWords();
        List<String> correctWords = getCorrectWords(spellChecker, misspelledWords);

        printCorrectSentences(inputText, misspelledWords, correctWords);
    }

     public List<String> getCorrectWords(Checker spellChecker, List<String> misspelledWords) {
        List<String> correctWords = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (String misspelledWord : misspelledWords) {
            List<String> suggestions = spellChecker.findSuggestions(misspelledWord);
            handleSuggestions(misspelledWord, suggestions, correctWords, scanner);
        }
        return correctWords;
    }

     public void handleSuggestions(String misspelledWord, List<String> suggestions,
                                   List<String> correctWords, Scanner scanner) {
        System.out.println("\n\nSuggestions for " + misspelledWord + ": " + suggestions);
        System.out.println("Select one of them");

        String correctWord = scanner.nextLine().toLowerCase();
        while (!suggestions.contains(correctWord)) {
            System.out.println("Error!! There is no such suggestion");
            System.out.println("Suggestions for " + misspelledWord + ": " + suggestions);
            correctWord = scanner.nextLine().toLowerCase();
        }
        correctWords.add(correctWord);
    }

     public void printCorrectSentences(String inputText, List<String> misspelledWords, List<String> correctWords) {
        List<String> correctSentences = generateCorrectSentences(inputText, misspelledWords, correctWords);

        System.out.println("\nCorrect sentence with your choices");
        String firstWord = correctSentences.get(0);
        correctSentences.set(0, capitalizeFirstLetter(firstWord));

        for (int j = 0; j < correctSentences.size(); j++) {
            if (j > 0) {
                System.out.print(" "); // Boşluk ekle
            }
            System.out.print(correctSentences.get(j));
        }
        System.out.print(lastCharacter);
    }

     public String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

     public List<String> generateCorrectSentences(String inputText, List<String> misspelledWords, List<String> correctWords) {
        List<String> correctSentences = new ArrayList<>();
        String[] inputWords = inputText.toLowerCase().split("[\\s.,;!?]+");

        int i = 0;
        for (String word : inputWords) {
            if (!misspelledWords.contains(word)) {
                correctSentences.add(word);
            } else {
                correctSentences.add(correctWords.get(i));
                i++;
            }
        }
        return correctSentences;
    }
}
