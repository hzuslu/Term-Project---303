package project.checker;

import java.util.List;

/**
 * Interface for a spell checker that checks and suggests corrections for misspelled words.
 */
public interface IChecker {

    /**
     * Retrieves a list of misspelled words in the provided text.
     *
     * @return A list of misspelled words.
     */
    List<String> getMisspelledWords();

    /**
     * Checks if the provided text is valid and identifies misspelled words.
     *
     * @param text The text to be checked.
     */
    void isTextValid(String text);

    /**
     * Finds suggestions for a given misspelled word, considering both spelling and keyboard proximity.
     *
     * @param misspelled The misspelled word for which suggestions are needed.
     * @return A list of suggested corrections.
     */
    List<String> findSuggestions(String misspelled);

    /**
     * Calculates the minimum edit distance between two words.
     *
     * @param s1 The first word.
     * @param s2 The second word.
     * @return The minimum edit distance between the two words.
     */
    int calculateEditDistance(String s1, String s2);
}
