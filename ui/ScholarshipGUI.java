package ui;

import javax.swing.*;
import java.awt.*;

public class ScholarshipGUI extends JFrame {
    // Put buttons and shit and instantiate other shit here
    private JLabel title = new JLabel();
    private JPanel tablePanel = new JPanel();
    public ScholarshipGUI() {
        // Initializers and this.adds go in here
        initializer();
        tablePanel();
        this.add(title);
        this.add(tablePanel);
    }

    public void initializer() {
        this.setTitle("ScholarshipGUI");
        this.getContentPane().setLayout(null);
        this.setSize(900, 500);
        title.setText("Scholarship GUI");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(375, 10, 300, 50);
    }

    public JPanel tablePanel() {

        return tablePanel;
    }

    // More methods

    public static void main(String[] args) {
        new ScholarshipGUI().setVisible(true);
    }
}
