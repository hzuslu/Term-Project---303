package project.corrector;

import project.checker.Checker;

import java.util.List;
import java.util.Scanner;

public interface ISentenceCorrector {

    /**
     * Processes the input text using the provided spell checker and prints the corrected sentences.
     *
     * @param spellChecker The spell checker used to identify misspelled words.
     * @param inputText    The input text to be processed and corrected.
     */
    void processInputText(Checker spellChecker, String inputText);

    /**
     * Retrieves a lisst of correct words for the given mispelled words using the provided spell checker.
     *
     * @param spellChecker     The spell checker used to find suggestions for misspelled words.
     * @param misspelledWords  The list of misspelled words to find correct alternatives for.
     * @return                 A list of correct words corresponding to the provided misspelled words.
     */
    List<String> getCorrectWords(Checker spellChecker, List<String> misspelledWords);

    /**
     * Handles user input for selecting a correct word from a list of suggestions.
     *
     * @param misspelledWord  The misspelled word for which suggestions are provided.
     * @param suggestions     The list of suggested corrections for the misspelled word.
     * @param correctWords    The list to which the selected correct word will be added.
     * @param scanner         The Scanner object used to get user input.
     */
    void handleSuggestions(String misspelledWord, List<String> suggestions, List<String> correctWords, Scanner scanner);

    /**
     * Prints the corrected sentences based on the input text, misspelled words, and their correct alternatives.
     *
     * @param inputText       The original input text.
     * @param misspelledWords The list of misspelled words in the input text.
     * @param correctWords    The list of correct words corresponding to the misspelled words.
     */
    void printCorrectSentences(String inputText, List<String> misspelledWords, List<String> correctWords);

    /**
     * Capitalizes the first letter of a word.
     *
     * @param word The word to be capitalized.
     * @return     The word with the first letter capitalized.
     */
    String capitalizeFirstLetter(String word);

    /**
     * Generates a list of correct sentences based on the input text, misspelled words, and their correct alternatives.
     *
     * @param inputText       The original input text.
     * @param misspelledWords The list of misspelled words in the input text.
     * @param correctWords    The list of correct words corresponding to the misspelled words.
     * @return                A list of correct sentences.
     */
    List<String> generateCorrectSentences(String inputText, List<String> misspelledWords, List<String> correctWords);
}
