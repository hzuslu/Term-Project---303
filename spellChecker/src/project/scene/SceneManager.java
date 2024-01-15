package project.scene;
//Fırat bilgen 22050151020
// Mustafa yılmaz 20050111010
// Hasan uslu 19050111003
// Batuhan tuncer 20050111040

import project.checker.Checker;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

public class SceneManager extends JFrame {
    JPanel panelTop, panelMid, panelBottom;
    JButton checkButton, completeButton;
    JTextField textField1;
    JTextArea finalSentence;
    int length;
    String inputText;
    JTextArea[] textAreas;
    JScrollPane[] scrollPanes;
    JPanel[] panels;
    ArrayList<ArrayList<String>> suggestion;
    JCheckBox[][] checkboxes;
    String[] fınal;
    JLabel[] vocab;
    JScrollPane[] scrollPane;
    Checker spellChecker;

    public SceneManager() {

        super("SPELL CHECKER");
        spellChecker = new Checker();

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

            suggestion = new ArrayList<>();
            inputText = textField1.getText().toLowerCase();
            String[] temp = inputText.trim().split("\\s+");
            length = inputText.trim().split("\\s+").length;

            for (String str : temp) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(str + "\n");
                suggestion.add(arrayList);
            }

            if (!Objects.equals(inputText, " ") || !Objects.equals(inputText, "")) {
                spellChecker.isTextValid(inputText);    // Check if the input text contains valid words and identify misspelled words.
                List<String> misspelledWords = spellChecker.getMisspelledWords();      // Retrieve the list of misspelled words.
                Iterator var4 = ((java.util.List<?>) misspelledWords).iterator();
                // Iterate through the misspelled words.

                while (var4.hasNext()) {
                    String misspelledWord = (String) var4.next();
                    List<String> suggestions = spellChecker.findSuggestions(misspelledWord);    // Find suggestions for the misspelled word.
                    System.out.println("Suggestions for " + misspelledWord + ": " + String.valueOf(suggestions));
                    String[] tempSuggestion = suggestions.toArray(new String[0]);
                    int iter = 0;
                    for (int i = 0; i < length; i++) {
                        if (suggestion.get(iter).toString().length() - 3>0)
                        if (Objects.equals(misspelledWord, suggestion.get(iter).toString().substring(1, suggestion.get(iter).toString().length() - 3)) ||
                                Objects.equals(misspelledWord, suggestion.get(iter).toString().substring(1, suggestion.get(iter).toString().length() - 2))) {
                            for (String s : tempSuggestion) {
                                suggestion.get(iter).add(s);
                            }
                        }
                        iter++;
                    }
                }
            }
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
        finalSentence.setPreferredSize(new Dimension(1525, 180));
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

    void addArray() {
        textAreas = new JTextArea[length];
        panels = new JPanel[length];
        vocab = new JLabel[length];
        fınal = new String[length];
        scrollPane = new JScrollPane[length];
        checkboxes = new JCheckBox[length][15];
        scrollPanes = new JScrollPane[length];
        panelMid.removeAll();
        completeButton = new JButton("COMPLETE");
        completeButton.setBackground(Color.cyan);
        completeButton.setFont(new Font("Arial", Font.BOLD, 15));
        completeButton.setPreferredSize(new Dimension(150, 70));
        completeButton.setBorderPainted(false);

        completeButton.addActionListener(e -> {
            StringBuilder tempString = new StringBuilder();
            for (int i = 0; i < length; i++) {
                if (fınal[i] == null)
                    tempString.append("invalid" + " ");
                else
                    tempString.append(fınal[i] + " ");
            }
            setFinalSentence(tempString.toString());
        });

        for (int i = 0; i < length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS));
            vocab[i] = new JLabel(getVocab(i));
            vocab[i].setFont(new Font("Arial", Font.BOLD, 16));
            panels[i].add(vocab[i]);


            for (int j = 0; j < 15; j++) {
                checkboxes[i][j] = new JCheckBox(getSuggestion(i, j));
                if (suggestion.isEmpty())
                    checkboxes[i][j].setForeground(Color.red);
                if (getSuggestion(i, j+1)!=null && j==0)
                    checkboxes[i][j].setForeground(Color.red);
                else if (getSuggestion(i, j+1)==null && j==0) {
                    checkboxes[i][j].setForeground(Color.GREEN);
                    checkboxes[i][j].setSelected(true);
                    fınal[i]=(checkboxes[i][j].getText().toString());
                }


                panels[i].add(checkboxes[i][j]);
                int finalI = i;
                int finalJ = j;
                checkboxes[i][j].addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            for (JCheckBox checkBox : checkboxes[finalI]) {
                                if (checkBox != checkboxes[finalI][finalJ]) {
                                    checkBox.setSelected(false);
                                }
                            }
                            if (Objects.equals(checkboxes[finalI][finalJ].getForeground().toString(), "java.awt.Color[r=255,g=0,b=0]"))
                                fınal[finalI] = checkboxes[finalI][finalJ].getText() + "(invalid)";
                            else
                                fınal[finalI] = (checkboxes[finalI][finalJ].getText());
                        }
                    }
                });
            }
            panelMid.add(panels[i]);
        }
        panelMid.add(completeButton);
        panelMid.repaint();
        panelMid.validate();
    }

    String getSuggestion(int index, int j) {
        String[] temp = suggestion.get(index).toArray(new String[0]);
        if (j < temp.length && temp[j] != null) {
            if (j == 0)
                temp[j] = temp[j].substring(0, temp[j].length() - 1);

            return temp[j];
        } else
            return null;
    }

    String getVocab(int i) {
        return suggestion.get(i).get(0).toString();
    }
}