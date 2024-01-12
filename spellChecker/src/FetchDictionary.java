import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class FetchDictionary {



   public HashSet<String> buildHashSet(String filePath) {
        HashSet<String> set = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                set.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

   public boolean searchWord(HashSet<String> set, String word) {
        if (set.contains(word.toLowerCase())) {

            return true;
        } else {

            return false;
        }

    }
}




