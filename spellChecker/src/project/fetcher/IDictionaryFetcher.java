package project.fetcher;

import java.util.HashSet;

/**
 * Interface for fetching and managing a dictionary of words.
 */
public interface IDictionaryFetcher {

    /**
     * Builds a HashSet of words from a file.
     *
     * @param filePath The path to the file containing the dictionary words.
     * @return A HashSet containing the words from the file.
     */
    HashSet<String> buildHashSet(String filePath);

    /**
     * Searches for a word in the given HashSet.
     *
     * @param set  The HashSet to search within.
     * @param word The word to search for.
     * @return True if the word is found in the HashSet, false otherwise.
     */
    boolean searchWord(HashSet<String> set, String word);
}
