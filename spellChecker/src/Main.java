//Fırat bilgen 22050151020
// Mustafa yılmaz 20050111010
// Hasan uslu 19050111003
// Batuhan tuncer 20050111040

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

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
