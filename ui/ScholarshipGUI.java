package ui;

import javax.swing.*;

public class ScholarshipGUI extends JFrame {
    // Put buttons and shit and instantiate other shit here
    public ScholarshipGUI() {
        // Initializers and this.adds go in here
        initializer();
    }

    public void initializer() {
        this.setTitle("ScholarshipGUI");
        this.getContentPane().setLayout(null);
        this.setSize(900, 500);
    }

    // More methods

    public static void main(String[] args) {
        new ScholarshipGUI().setVisible(true);
    }
}
