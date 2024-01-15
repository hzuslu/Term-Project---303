package project;

import project.checker.Checker;
import project.corrector.SentenceCorrector;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Checker checker = new Checker();
        SentenceCorrector sentenceCorrector = new SentenceCorrector();
        System.out.println("*********WELCOME TO SPELLCHECK**********");
        System.out.println("Enter the sentence you want to correct");
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();



        sentenceCorrector.processInputText(checker,inputText);
    }
}
