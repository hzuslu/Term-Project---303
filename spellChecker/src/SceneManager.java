import javax.swing.*;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class SceneManager extends JFrame {
    JPanel panelTop, panelMid, panelBottom;
    JButton checkButton;
    JTextField textField1;
    JTextArea finalSentence;
    JTextArea[] textArray;
    int length ;
    String inputText;
    JTextArea[] textAreas;
    JScrollPane[] scrollPanes;
    ArrayList<ArrayList<String>> suggestion = new ArrayList<>();

    SceneManager() {

        super("SPELL CHECKER");

        // TOP
        panelTop = new JPanel();
        panelTop.setBackground(Color.black);


        textField1 = new JTextField();
        textField1.setBackground(Color.LIGHT_GRAY);
        textField1.setFont(new Font("Arial", Font.BOLD, 18));
        textField1.setPreferredSize(new Dimension(400, 50));

        checkButton = new JButton("CHECK");
        checkButton.setBackground(Color.cyan);
        checkButton.setFont(new Font("Arial", Font.BOLD, 15));
        checkButton.setPreferredSize(new Dimension(120, 50));
        checkButton.setBorderPainted(false);
        checkButton.addActionListener(e -> {
            SpellChecker spellChecker = new SpellChecker();
            suggestion = new ArrayList<>();
            inputText = textField1.getText();
            String[] temp=inputText.trim().split("\\s+");
            length=inputText.trim().split("\\s+").length;

            for (String str : temp) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(str+"\n");
                suggestion.add(arrayList);
            }


            if (!Objects.equals(inputText, " ") || !Objects.equals(inputText, "")) {
                spellChecker.isWordInDictionary(inputText);    // Check if the input text contains valid words and identify misspelled words.
                List<String> misspelledWords = spellChecker.getMisspelledWord();      // Retrieve the list of misspelled words.
                Iterator var4 = ((java.util.List<?>) misspelledWords).iterator();
                // Iterate through the misspelled words.

                while (var4.hasNext()) {
                    String misspelledWord = (String) var4.next();
                    List<String> suggestions = spellChecker.findSuggestions(misspelledWord);    // Find suggestions for the misspelled word.
                    System.out.println("Suggestions for " + misspelledWord + ": " + String.valueOf(suggestions));
                    String[] tempSuggestion= suggestions.toArray(new String[0]);
                    int iter=0;
                    for (int i=0;i<length;i++) {
                        if (Objects.equals(misspelledWord, suggestion.get(iter).toString().substring(1,suggestion.get(iter).toString().length()-3)) ||
                                Objects.equals(misspelledWord, suggestion.get(iter).toString().substring(1,suggestion.get(iter).toString().length()-2))) {
                            for (String s : tempSuggestion) {
                                suggestion.get(iter).add(   "-> "+s + "\n");
                            }
                        }
                        iter++;
                    }
                }
            }
            setFinalSentence(inputText);
            addArray();
        });
        panelTop.add(textField1);
        panelTop.add(checkButton);



        //MID
        panelMid = new JPanel();
        panelMid.setBackground(Color.darkGray);
        panelMid.setLayout(new FlowLayout(FlowLayout.CENTER));


        // BOTTOM
        panelBottom = new JPanel();
        panelBottom.setBackground(Color.black);
        panelBottom.setPreferredSize(new Dimension(300, 200));
        panelBottom.setLayout(new FlowLayout(FlowLayout.LEFT));

        finalSentence = new JTextArea();
        finalSentence.setPreferredSize(new Dimension(1525,180));
        finalSentence.setBackground(Color.black);
        finalSentence.setForeground(Color.lightGray);
        finalSentence.setFont(new Font("Arial", Font.BOLD, 18));

        add(panelBottom);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        add(panelTop, BorderLayout.NORTH);
        add(panelMid, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);

    }

    void setFinalSentence(String inputText) {
        finalSentence.setText(inputText);
        finalSentence.setLineWrap(true);
        panelBottom.add(finalSentence);
        panelBottom.validate();
    }
    void addArray(){
        textAreas= new JTextArea[length];
        scrollPanes= new JScrollPane[length];
        panelMid.removeAll();

        for (int i=0;i<length;i++){
            textAreas[i]= new JTextArea(10,10);
            textAreas[i].setBackground(Color.lightGray);
            textAreas[i].setFont(new Font("Arial", Font.BOLD, 13));
            textAreas[i].setText(" * "+String.valueOf(suggestion.get(i)).substring(1,String.valueOf(suggestion.get(i)).length()-1));
            scrollPanes[i]= new JScrollPane(textAreas[i]);
            panelMid.add(scrollPanes[i]);
        }
        panelMid.repaint();
        panelMid.validate();
    }
}
