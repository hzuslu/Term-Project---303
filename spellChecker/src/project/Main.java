package project;

import project.checker.Checker;
import project.corrector.SentenceCorrector;
import project.scene.SceneManager;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SceneManager().setVisible(true);
            }
        });
    }
}
