package project;

import project.checker.Checker;
import project.corrector.SentenceCorrector;

public class Main {

    public static void main(String[] args) {
        Checker checker = new Checker();
        SentenceCorrector sentenceCorrector = new SentenceCorrector();
        String inputText = "Thes are some misspelled words like aplle, banan and oragne.";
        sentenceCorrector.processInputText(checker,inputText);
    }
}
